package grammar.token

import grammar.Expansion
import grammar.Grammar
import utils.Factory
import utils.RB
import utils.RBObject

sealed class Token {

    /**
     * [SpecialToken] is a general-purpose token that represents any grammar-defined special symbols
     *
     * [AlphaToken] is a lexer-specific token that holds a value of alphabet character
     * it is [RepresentedBy]<[RepresentationToken.AnyAlpha]>
     * [NumberToken] is a lexer-specific token that holds a value of an integer
     * it is [RepresentedBy]<[RepresentationToken.AnyNumber]>
     *
     * [RepresentationToken] is a grammar-specific token used for representation of lexer-specific tokens in grammar
     *
     * [END] is a lexer-specific token used as a signal of end of the line
     * is also used in [parse.Helper.FOLLOW]
     * [EPSILON] is a grammar-specific token allowing empty expansions
     * is also used in [parse.Helper.FIRST]
     *
     * [State] is a grammar-specific token representing non-terminal state of a grammar
     */

    @Suppress("LeakingThis")
    class SpecialToken private constructor(private val data: Char) : Token() {
        companion object : Factory<Char, SpecialToken>() {
            override fun create(id: Char): SpecialToken {
                return SpecialToken(id)
            }

            operator fun contains(key: Char): Boolean {
                return key in factory
            }

            operator fun get(key: Char): SpecialToken {
                return factory[key]!!
            }
        }

        override fun toString(): String {
            return "'$data'"
        }
    }

    class AlphaToken private constructor(val data: Char) : Token(),
        RB<RepresentationToken.AnyAlpha> by RBObject(RepresentationToken.AnyAlpha) {
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

    class NumberToken private constructor(private val data: Int) : Token(),
        RB<RepresentationToken.AnyNumber> by RBObject(RepresentationToken.AnyNumber) {
        companion object : Factory<Int, NumberToken>() {
            override fun create(id: Int): NumberToken {
                return NumberToken(id)
            }
        }

        override fun toString(): String {
            return data.toString()
        }
    }

    sealed class RepresentationToken : Token() {
        object AnyAlpha : RepresentationToken() {
            override fun toString(): String {
                return "<alpha>"
            }
        }

        object AnyNumber : RepresentationToken() {
            override fun toString(): String {
                return "<number>"
            }
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
     * [State] is a grammar-specific class which represents a non-terminal state
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
