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

    companion object TokenStorage {
        private val factory = hashMapOf<String, Token>()
        private val all = mutableListOf<Token>()

        val REGISTERED: GetViewer<String, Token>
            get() = object : GetViewer<String, Token> {
                override fun all(): List<Token> {
                    return all.toList()
                }

                override fun get(key: String): Token {
                    return factory[key]!!
                }
            }

        operator fun invoke(name: String, token: Token): Token {
            if (name in factory) {
                throw IllegalArgumentException("Token '$name' already exists")
            }
            return token.also {
                factory[name] = it
                all.add(it)
            }
        }

        fun isAcceptable(lexerToken: Token, grammarToken: Token): Boolean {
            return lexerToken == grammarToken ||
                    lexerToken is VariantToken.VariantInstanceToken && lexerToken.origin == grammarToken
        }

        fun isAcceptable(token: Token, tokenSet: Set<Token>): Boolean {
            return tokenSet.any { isAcceptable(token, it) }
        }
    }

    @Suppress("LeakingThis")
    sealed class UniqueToken(private val name: String) : Token {
        init {
            TokenStorage(name, this)
        }

        object EOF : UniqueToken("EOF") {
            override fun consume(data: String, offset: Int): String? {
                return if (offset == data.length) "" else null
            }

            override fun toString(): String {
                return "<eof>"
            }

            override fun getText(): String {
                return "$"
            }
        }

        object EPSILON : UniqueToken("EPSILON") {
            override fun toString(): String {
                return "<eps>"
            }

            override fun getText(): String {
                return ""
            }
        }
    }

    interface DataToken : Token {

        abstract class NamedDataToken private constructor(val data: String) : DataToken {
            companion object {
                operator fun invoke(name: String, data: String): NamedDataToken {
                    return object : NamedDataToken(data) {
                        override fun toString(): String {
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
        }

    }

    interface VariantToken : Token {

        abstract class Instantiable : VariantToken {
            fun instantiate(value: String): VariantInstanceToken {
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
                        override fun toString(): String {
                            return name
                        }

                        override fun consume(data: String, offset: Int): String? {
                            return this.descriptor.consume(data, offset)
                        }
                    }
                }
            }
        }

        class VariantInstanceToken(val origin: VariantToken, val value: String) : Token {
            override fun toString(): String {
                return "'$value'"
            }

            override fun getText(): String {
                return value
            }

            override fun equals(other: Any?): Boolean {
                return other is VariantInstanceToken && other.origin == origin && other.value == value
            }

            override fun hashCode(): Int {
                var result = origin.hashCode()
                result = 31 * result + value.hashCode()
                return result
            }
        }

    }

    open class StringToken(name: String, data: String) : DataToken by DataToken.NamedDataToken(name, data) {
        init {
            TokenStorage(name, this)
        }
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

        override fun toString(): String {
            return name
        }

        fun derived(): StateToken {
            var newId = name
            while (newId in factory.keys) {
                newId += "Plus"
            }
            return StateToken(newId)
        }

        infix fun into(expansion: Expansion): Grammar.Rule {
            return Grammar.Rule(this, listOf(Expansion(this, expansion)))
        }
    }

}
