package grammar

import grammar.token.Restricted
import grammar.token.Token

class Expansion(vararg lexemes: Token) : ArrayList<Token>(),
    Restricted by Restricted.Terminal + Restricted.State {

    companion object ExpansionStorage {
        data class Storage(val factory: MutableMap<String, Int>, val storage: MutableMap<String, Int>)

        private val storages = mutableMapOf<String, Storage>()
        private var grammarName: String? = null

        val current: Storage?
            get() {
                return storages[grammarName ?: return null]
            }

        fun switchTo(name: String) {
            grammarName = name.also {
                if (it !in storages) storages[it] = Storage(mutableMapOf(), mutableMapOf())
            }
        }

        operator fun invoke(state: Token.StateToken, expansion: Expansion): Expansion {
            expansion.id = current!!.storage.getOrPut("$state: $expansion") {
                current!!.factory.putIfAbsent(state.toString(), 0)
                current!!.factory[state.toString()]!!.also {
                    current!!.factory[state.toString()] = current!!.factory[state.getString()]!! + 1
                }
            }
            return expansion
        }
    }

    private var id = 0

    fun getId(): Int {
        return id
    }

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
