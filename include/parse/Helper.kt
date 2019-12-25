package parse

import grammar.Expansion
import grammar.Grammar
import grammar.token.Token
import utils.*
import utils.viewer.ConsistentViewer
import utils.viewer.GetViewer

@Suppress("PropertyName")
class Helper(val grammar: Grammar) {

    class FirstRestrictedSet(vararg elements: Token) : HashSet<Token>(),
        TR by TRGeneral + TRRepresentation + TRFirst {
        init {
            addAll(elements)
        }

        override fun add(element: Token): Boolean {
            return super.add(pass(element))
        }
    }

    class FollowRestrictedSet(vararg elements: Token) : HashSet<Token>(),
        TR by TRGeneral + TRRepresentation + TRFollow {
        init {
            addAll(elements)
        }

        override fun add(element: Token): Boolean {
            return super.add(pass(element))
        }
    }

    private val first = HashMap<Token.State, FirstRestrictedSet>()
    private val follow = HashMap<Token.State, FollowRestrictedSet>()

    val FIRST: ConsistentViewer<Token.State, Expansion, Set<Token>>
        get() = object : ConsistentViewer<Token.State, Expansion, Set<Token>> {
            override operator fun get(key: Token.State): Set<Token> {
                return first[key]!!
            }

            override operator fun invoke(key: Expansion): Set<Token> {
                return globalFirst(key)
            }

            override fun toString(): String {
                return first.map { "${it.key} -> ${Beautifier.tokenFold(it.value)}" }.joinToString("\n")
            }
        }

    val FOLLOW: GetViewer<Token.State, Set<Token>>
        get() = object : GetViewer<Token.State, Set<Token>> {
            override fun get(key: Token.State): Set<Token> {
                return follow[key]!!
            }

            override fun toString(): String {
                return follow.map { "${it.key} -> ${Beautifier.tokenFold(it.value)}" }.joinToString("\n")
            }
        }

    init {
        buildFirst()
        buildFollow()
    }

    private fun globalFirst(expansion: Expansion): Set<Token> {
        assert(expansion.size > 0) { "Unexpected empty expansion '$expansion'" }
        return when (expansion[0]) {
            /*is Token.PredefinedToken -> hashSetOf(expansion[0] as Token.PredefinedToken)
            is Token.AlphaToken -> hashSetOf(expansion[0] as Token.AlphaToken)
            is Token.NumberToken -> hashSetOf(expansion[0] as Token.NumberToken)*/
            is Token.State -> first[expansion[0]]?.let {
                if (it.contains(Token.EPSILON)) {
                    it.union(globalFirst(expansion.dropFirst(1)))
                } else {
                    it
                }
            } ?: emptySet()
//            is Token.EPSILON -> hashSetOf(Token.EPSILON)
            else -> hashSetOf(expansion[0])
        }
    }

    private fun buildFirst() {
        var changed = true
        grammar.STATES.forEach { first[it] = FirstRestrictedSet() }

        val rules = grammar.RULES.all()
        while (changed) {
            changed = false
            for (rule in rules) {
                with(first[rule.state]!!) {
                    val oldSize = this.size
                    rule.expansions.forEach {
                        this.addAll(globalFirst(it))
                    }
                    if (this.size > oldSize) {
                        changed = true
                    }
                }
            }
        }
    }

    private fun buildFollow() {
        var changed = true

        fun collectInExpansion(state: Token.State, expansion: Expansion): Boolean {
            var localChanged = false
            for (i in 0 until expansion.size) {
                if (expansion[i] is Token.State) {
                    val firstOfAfter = globalFirst(expansion.dropFirst(i + 1))
                    with(follow[expansion[i]]!!) {
                        val oldSize = this.size
                        this.addAll(firstOfAfter.minus(Token.EPSILON))
                        if (Token.EPSILON in firstOfAfter) {
                            this.addAll(follow[state]!!)
                        }
                        if (this.size > oldSize) {
                            localChanged = true
                        }
                    }
                }
            }
            return localChanged
        }

        val rules = grammar.RULES.all()
        grammar.STATES.forEach { follow[it] = FollowRestrictedSet() }
        follow[grammar.getStart()]!!.add(Token.END)
        while (changed) {
            changed = false
            for (rule in rules) {
                rule.expansions.forEach { expansion ->
                    if (collectInExpansion(rule.state, expansion)) {
                        changed = true
                    }
                }
            }
        }
    }

}