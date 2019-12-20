package grammar

import utils.Factory

sealed class Token {

    /**
     * These are lexer oriented classes:
     *
     * [PredefinedToken] is restricted to grammar-specific special purpose symbols
     * [AlphaToken] specifies any letter of alphabet
     * [END] is used in [regex.parse.Lexer] to signal that the end of the line is reached
     * [EPSILON] is used as an empty-string terminal in [regex.grammar.Grammar] and [regex.parse.Helper]
     */

    @Suppress("LeakingThis")
    class PredefinedToken private constructor(private val data: Char) : Token() {
        companion object : Factory<Char, PredefinedToken>() {
            override fun create(id: Char): PredefinedToken {
                return PredefinedToken(id)
            }

            operator fun contains(key: Char): Boolean {
                return key in factory
            }

            operator fun get(key: Char): PredefinedToken {
                return factory[key]!!
            }
        }

        override fun toString(): String {
            return "'$data'"
        }
    }

    class AlphaToken private constructor(val data: Char) : Token() {
        companion object : Factory<Char, AlphaToken>() {
            private val alphabet = hashSetOf<Char>()

            fun allow(charRange: CharRange): AlphaToken.Companion {
                alphabet.addAll(charRange)
                return this
            }

            fun allow(char: Char): AlphaToken.Companion {
                alphabet.add(char)
                return this
            }

            override fun create(id: Char): AlphaToken {
                if (id !in alphabet) {
                    throw IllegalArgumentException("Alphabet does not contain character '$id'")
                }
                return AlphaToken(id)
            }
        }

        override fun toString(): String {
            return "'$data'"
        }
    }

    object END : Token() {
        override fun toString(): String {
            return "$"
        }
    }

    object EPSILON : Token() {
        override fun toString(): String {
            return "eps"
        }
    }

    /**
     * [State] is a grammar-specific class which specifies a non-terminal state
     */

    class State private constructor(private val id: String) : Token() {
        companion object : Factory<String, State>() {
            private const val pattern = "[A-Z]\\d*`*"

            override fun create(id: String): State {
                if (!id.matches(pattern.toRegex())) {
                    throw IllegalArgumentException("State id should match '$pattern")
                }
                return State(id)
            }
        }

        fun derived(): State {
            var newId = id
            while (newId in State.factory.keys) {
                newId += '`'
            }
            return Companion(newId)
        }

        infix fun into(expansion: Expansion): Grammar.Rule {
            return Grammar.Rule(this, listOf(expansion))
        }

        override fun toString(): String {
            return id
        }
    }

}
