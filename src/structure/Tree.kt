package structure

import grammar.Token
import utils.*

sealed class Tree(val token: Token, private val id: Int) : TR by TRUniversal + TRFirst + TRState {

    companion object {
        private var lastId = 0

        fun resetAll() {
            lastId = 0
        }

        fun newNodeId(): Int {
            return lastId++
        }
    }

    abstract fun checkIsSame(other: Tree)

    abstract fun collectTo(sb: StringBuilder)

    class Leaf(state: Token) : Tree(state, newNodeId()) {
        override fun checkIsSame(other: Tree) {
            if (other !is Leaf) {
                throw IllegalStateException("Original contains leaf $this where given tree has inner node $other")
            }
        }

        override fun collectTo(sb: StringBuilder) {
            sb.append("\tnode [shape = circle, label = \"$token\"]; \"$this\";\n")
        }
    }

    class InnerNode(state: Token.State, vararg children: Tree) : Tree(state, newNodeId()) {
        val children = children.toMutableList()

        fun add(child: Tree) {
            children.add(child)
        }

        override fun checkIsSame(other: Tree) {
            when {
                other !is InnerNode ->
                    throw IllegalStateException("Original contains inner node $this where given tree has leaf $other")
                children.size != other.children.size ->
                    throw IllegalStateException("Original $this and given $other have different amount of children")
                token != other.token ->
                    throw IllegalStateException("Original $this and given $other have different corresponding tokens")
                else ->
                    children.forEachIndexed { index, tree -> tree.checkIsSame(other.children[index]) }
            }
        }

        override fun collectTo(sb: StringBuilder) {
            sb.append("\tnode [shape = doublecircle, label = \"$token\"]; \"$this\";\n")
            this.children.forEach {
                it.collectTo(sb)
                sb.append("\t\"$this\" -> \"$it\"\n")
            }
        }
    }

    override fun toString(): String {
        return "$token[$id]"
    }

    fun toGraphViz(): String {
        return "digraph tree {\n" +
                java.lang.StringBuilder().also { collectTo(it) }.toString() +
                "}"
    }

}