package grammar

import grammar.token.Token
import utils.*

class Expansion(vararg lexemes: Token) : ArrayList<Token>(), TR by TRGeneral + TRState + TRRepresentation + TRFirst {

    init {
        addAll(lexemes.map { pass(it) })
    }

    operator fun plus(arg: Token): Expansion {
        return copy().also { it.add(pass(arg)) }
    }

    operator fun plus(arg: String): Expansion {
        return copy().also { it.add(pass(Token.State(arg))) }
    }

    operator fun plus(arg: Char): Expansion {
        return copy().also { it.add(pass(Token.AlphaToken(arg))) }
    }

    private fun epsilonChecked(expansion: Expansion): Expansion {
        return expansion.also { if (expansion.isEmpty()) expansion.add(pass(Token.EPSILON)) }
    }

    fun takeFirst(i: Int): Expansion {
        return epsilonChecked(Expansion().also {
            it.addAll(take(i))
        })
    }

    fun dropFirst(i: Int): Expansion {
        return epsilonChecked(Expansion().also {
            it.addAll(takeLast(size - i))
        })
    }

    private fun copy(): Expansion {
        return Expansion().also { it.addAll(this) }
    }

    override fun toString(): String {
        return joinToString(" ")
    }

}
