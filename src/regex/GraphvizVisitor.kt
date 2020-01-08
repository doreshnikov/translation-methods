package regex

import grammar.token.Token
import structure.ASTNode
import structure.Visitor
import utils.Beautifier
import java.lang.StringBuilder

class GraphvizVisitor : Visitor<String> {

    override fun <T : Token> collect(root: ASTNode<T>) : String {
        return "digraph tree {\n${visit(root)}}"
    }

    override fun <T : Token> visit(node: ASTNode<T>): String {
        return if (node is ASTNode.TerminalNode<T>) {
            visitTerminal(node)
        } else {
            visitInner(node as ASTNode.InnerNode<T>)
        }
    }

    private fun <T : Token> visitTerminal(node: ASTNode.TerminalNode<T>): String {
        val label = Beautifier.escape(node.getToken().toString())
        return "\tnode [shape = circle, label = \"$label\"]; \"$node\"\n"
    }

    private fun <T : Token> visitInner(node: ASTNode.InnerNode<T>) : String {
        val label = Beautifier.escape(node.getToken().toString())
        val sb = StringBuilder().append("\tnode [shape = doublecircle, label = \"$label\"]; \"$node\"\n")
        node.children.forEach {
            sb.append(visit(it)).append("\t\"$node\" -> \"$it\"\n")
        }
        return sb.toString()
    }

}