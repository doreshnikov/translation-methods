package grammar

import grammar.token.Token
import utils.Beautifier
import utils.viewer.GetViewer

@Suppress("PropertyName")
class Grammar(private val startState: Token.StateToken, vararg rules: Rule) {

    class Rule(val state: Token.StateToken, val expansions: List<Expansion>) {
        override fun toString(): String {
            return "$state -> " +
                    expansions.joinToString(" | ", transform = { Beautifier.escape(it.toString()) })
        }
    }

    private var rules: MutableList<Rule> = rules.toMutableList()

    val RULES
        get() = object : GetViewer<Token.StateToken, Rule> {
            override fun all(): List<Rule> {
                return rules.toList()
            }

            override fun get(key: Token.StateToken): Rule {
                return rules.find { it.state == key }
                    ?: throw IllegalArgumentException("State $key is not a non-terminal of this grammar")
            }
        }

    val STATES
        get() = rules.map { it.state }.toList()

    fun add(rule: Rule) {
        rules.add(rule)
    }

    fun getStart(): Token.StateToken {
        return startState
    }

    fun order(): Grammar {
        return this.also {
            rules = rules.groupBy { rule -> rule.state }.map {
                Rule(it.key, it.value.map { rule -> rule.expansions }.toList().flatten())
            }.toMutableList()
        }
    }

    fun directLeftRecursionElimination(): Grammar {
        val result = Grammar(startState)
        for (rule in rules) {
            if (rule.expansions.any { it[0] == rule.state }) {
                val middle = rule.state.derived()
                rule.expansions.forEach {
                    if (it[0] == rule.state) {
                        val suffix = it.dropFirst(1)
                        result.add(middle into suffix)
                        result.add(middle into (suffix + middle))
                    } else {
                        result.add(rule.state into (it + middle))
                        result.add(rule.state into it)
                    }
                }
            } else {
                result.add(rule)
            }
        }
        return result.order()
    }

    fun rightBranchingElimination(): Grammar {
        val result = Grammar(startState)
        for (rule in rules) {
            val first = rule.expansions.groupBy { it[0] }
            for (group in first) {
                if (group.value.size == 1) {
                    result.add(rule.state into group.value[0])
                } else {
                    var common = 1
                    val minSize = group.value.map { it.size }.min()!!
                    while (common < minSize && group.value.all { it[common] == group.value[0][common] }) {
                        common++
                    }
                    val middle = rule.state.derived()
                    val prefix = group.value[0].takeFirst(common)
                    result.add(rule.state into (prefix + middle))
                    for (expansion in group.value) {
                        result.add(middle into expansion.dropFirst(common))
                    }
                }
            }
        }
        return if (result.globalSize() == globalSize()) {
            this
        } else {
            result.order().rightBranchingElimination()
        }
    }

    private fun globalSize(): Int {
        return rules.map { it.expansions.size }.sum()
    }

    override fun toString(): String {
        return "Start: $startState\n" + rules.joinToString("\n")
    }

}