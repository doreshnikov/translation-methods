package grammar.token

import grammar.Expansion
import grammar.Grammar
import utils.viewer.GetViewer
import kotlin.text.Regex

interface Token {

    fun consume(data: String, offset: Int): String? {
        return null
    }

    fun getText(): String {
        throw IllegalArgumentException("Token $this does not support text requests")
    }

    fun getString(): String

    companion object TokenStorage {
        data class Storage(val factory: MutableMap<String, Token>, val all: MutableList<Token>)

        private val grammars = mutableMapOf<String?, Storage>()
        private var grammarName: String? = null

        init {
            switchTo(null)
            UniqueToken.all.forEach { it.getString() }
        }

        val current: Storage?
            get() = grammars[grammarName]

        val common: Storage
            get() = grammars[null]!!

        fun switchTo(name: String?) {
            if (name != null) {
                Expansion.switchTo(name)
            }
            grammarName = name.also { if (it !in grammars) grammars[it] = Storage(mutableMapOf(), mutableListOf()) }
        }

        val REGISTERED: GetViewer<String, Token>
            get() = object : GetViewer<String, Token> {
                override fun all(): List<Token> {
                    return current!!.all.toList() + common.all.toList()
                }

                override fun get(key: String): Token {
                    return current!!.factory[key] ?: common.factory[key]!!
                }
            }

        operator fun invoke(name: String, token: Token) {
            if (name in current!!.factory || name in common.factory) {
                throw IllegalArgumentException("Token '$name' already exists")
            }
            current!!.factory[name] = token
            current!!.all.add(token)
        }

        fun registerUnique(name: String, token: UniqueToken) {
            common.factory[token.getString()]
            common.all.add(token)
        }

        fun isAcceptable(lexerToken: Token, grammarToken: Token): Boolean {
            return lexerToken == grammarToken ||
                    lexerToken is VariantToken.VariantInstanceToken<*> && lexerToken.origin == grammarToken
        }

        fun isAcceptable(token: Token, tokenSet: Set<Token>): Boolean {
            return tokenSet.any { isAcceptable(token, it) }
        }
    }

    @Suppress("LeakingThis")
    sealed class UniqueToken(private val name: String) : Token {
        companion object {
            val all = mutableListOf<UniqueToken>()
        }

        fun getName(): String {
            return name
        }

        init {
            TokenStorage.registerUnique(name, this)
            all.add(this)
        }

        object EOF : UniqueToken("EOF") {
            override fun consume(data: String, offset: Int): String? {
                return if (offset == data.length) "" else null
            }

            override fun getString(): String {
                return "<eof>"
            }

            override fun getText(): String {
                return "$"
            }
        }

        object EPSILON : UniqueToken("EPSILON") {
            override fun getString(): String {
                return "<eps>"
            }

            override fun getText(): String {
                return ""
            }
        }

        override fun toString() = getString()
    }

    interface DataToken : Token {

        abstract class NamedDataToken private constructor(val data: String) : DataToken {
            companion object {
                operator fun invoke(name: String, data: String): NamedDataToken {
                    return object : NamedDataToken(data) {
                        override fun getString(): String {
                            return name
                        }

                        override fun getText(): String {
                            return data
                        }

                        override fun consume(data: String, offset: Int): String? {
                            return if (data.startsWith(this.data, offset)) this.data else null
                        }
                    }
                }
            }

            override fun toString() = getString()
        }

    }

    interface VariantToken : Token {

        abstract class Instantiable : VariantToken {
            fun instantiate(value: String): VariantInstanceToken<*> {
                return VariantInstanceToken(this, value)
            }
        }

        abstract class Desriptor<T>(val data: T) {
            abstract fun consume(data: String, offset: Int): String?

            override fun toString(): String {
                return data.toString()
            }
        }

        abstract class NamedVariantToken<T : Desriptor<*>> private constructor(protected val descriptor: T) :
            VariantToken {
            companion object {
                operator fun <T : Desriptor<*>> invoke(name: String, desc: T): NamedVariantToken<T> {
                    return object : NamedVariantToken<T>(desc) {
                        override fun getString(): String {
                            return name
                        }

                        override fun consume(data: String, offset: Int): String? {
                            return this.descriptor.consume(data, offset)
                        }
                    }
                }
            }

            override fun toString() = getString()
        }

        class VariantInstanceToken<R : VariantToken>(val origin: R, val value: String) : Token {
            override fun getString(): String {
                return "'$value'"
            }

            override fun getText(): String {
                return value
            }

            override fun equals(other: Any?): Boolean {
                return other is VariantInstanceToken<*> && other.origin == origin && other.value == value
            }

            override fun hashCode(): Int {
                var result = origin.hashCode()
                result = 31 * result + value.hashCode()
                return result
            }

            override fun toString() = getString()
        }

    }

    open class StringToken(name: String, data: String) : DataToken by DataToken.NamedDataToken(name, data) {
        init {
            TokenStorage(name, this)
        }

        override fun toString() = getString()
    }

    open class RegexToken(name: String, data: Regex) : VariantToken.Instantiable(),
        VariantToken by VariantToken.NamedVariantToken(name,
            object : VariantToken.Desriptor<Regex>(data) {
                override fun consume(data: String, offset: Int): String? {
                    val match = this.data.find(data, offset) ?: return null
                    return if (match.range.first == offset) match.value else null
                }

                override fun toString(): String {
                    return "re($data)"
                }
            }
        ) {
        init {
            TokenStorage(name, this)
        }

        override fun toString() = getString()
    }

    open class CharRangeToken(name: String, data: CharRange) : VariantToken.Instantiable(),
        VariantToken by VariantToken.NamedVariantToken(name,
            object : VariantToken.Desriptor<CharRange>(data) {
                override fun consume(data: String, offset: Int): String? {
                    return if (offset < data.length && data[offset] in this.data) data[offset].toString() else null
                }

                override fun toString(): String {
                    return "cr($data)"
                }
            }
        ) {
        init {
            TokenStorage(name, this)
        }

        override fun toString() = getString()
    }

    open class StateToken(private val name: String) : Token {
        companion object {
            private const val pattern = "[a-z]+([A-Z][a-z]+)*"

            operator fun invoke(name: String, token: StateToken) {
                if (!name.matches(pattern.toRegex())) {
                    throw IllegalArgumentException("State id should match '$pattern', got '$name' instead")
                }
                TokenStorage(name, token)
            }
        }

        init {
            @Suppress("LeakingThis")
            Companion(name, this)
        }

        override fun getString(): String {
            return name
        }

        override fun toString() = getString()

        fun derived(): StateToken {
            var newId = name
            while (newId in current!!.factory.keys) {
                newId += "Plus"
            }
            return StateToken(newId)
        }

        infix fun into(expansion: Expansion): Grammar.Rule {
            return Grammar.Rule(this, listOf(Expansion(this, expansion)))
        }
    }

}
