package grammar

import grammar.token.Restricted
import grammar.token.Token

class Expansion(vararg lexemes: Token) : ArrayList<Token>(), Restricted by Restricted.Terminal + Restricted.State {

    init {
        addAll(lexemes.map { pass(it) })
    }

    operator fun plus(arg: Token): Expansion {
        return copy().also { it.add(pass(arg)) }
    }

    private fun epsilonChecked(expansion: Expansion): Expansion {
        return expansion.also { if (expansion.isEmpty()) expansion.add(pass(Token.UniqueToken.EPSILON)) }
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
