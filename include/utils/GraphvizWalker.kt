package utils

import grammar.token.Token
import structure.ASTNode
import structure.Walker
import java.lang.StringBuilder

class GraphvizWalker : Walker<String> {

    override fun <T : Token> collect(root: ASTNode<T>) : String {
        return "digraph tree {\n${visit(root)}}"
    }

    override fun visit(node: ASTNode<out Token>): String {
        return if (node is ASTNode.TerminalNodeBase<*>) {
            visitTerminal(node)
        } else {
            visitInner(node as ASTNode.InnerNodeBase<*>)
        }
    }

    private fun <T : Token> visitTerminal(node: ASTNode.TerminalNodeBase<T>): String {
        val label = Beautifier.escape(node.getToken().toString())
        return "\tnode [shape = circle, label = \"$label\"]; \"$node\"\n"
    }

    private fun <T : Token> visitInner(node: ASTNode.InnerNodeBase<T>) : String {
        val label = Beautifier.escape(node.getToken().toString())
        val sb = StringBuilder().append("\tnode [shape = doublecircle, label = \"$label\"]; \"$node\"\n")
        node.children.forEach {
            sb.append(visit(it)).append("\t\"$node\" -> \"$it\"\n")
        }
        return sb.toString()
    }

}