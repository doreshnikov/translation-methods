package translate.codegen

import grammar.token.Token
import structure.Description
import java.io.File

class VisitorBuilder(
    private val description: Description,
    private val name: String,
    private val packageName: String,
    private val className: String
) {

    val tokens = Token.REGISTERED.all().filter { it !is Token.UniqueToken }

    fun buildVisitor(output: File) {
        output.bufferedWriter().use { out ->
            out.write(
                """/**
This code is generated by [translate.codegen.VisitorBuilder] by provided [structure.Description]
*/

package $packageName

import structure.Visitor
import structure.ASTNode
import grammar.token.Token
import $packageName.$name

@Suppress("UNCHECKED_CAST")
abstract class $className<R> : Visitor<R> {

/*
${description.getGrammar()}
*/

    override fun visit(node: ASTNode<out Token>): R {${collectChoiceVisit()}
    }
    
    abstract fun <T : Token> visitTerminal(token: T): R
    
${collectVisitMethods()}
}
"""
            )
        }
    }

    private fun nodeType(token: Token): String {
        return if (token is Token.StateToken) {
            "ASTNode.InnerNode"
        } else {
            "ASTNode.TerminalNode"
        }
    }

    private fun fullName(token: Token): String {
        return "$name.${token.getName()}"
    }

    private fun collectChoiceVisit(): String {
        return """
        return when(node.getToken()) {
${tokens.joinToString("\n") { token ->
            "\t\t\t${fullName(token)} -> visit_${token.getName()}(node as ${nodeType(token)}<${fullName(token)}>)"
        }}
            else -> throw IllegalStateException("Unknown token ${"$"}{node.getToken()} met")
        }"""
    }

    private fun visitMethod(token: Token): String {
        return if (token is Token.StateToken) {
            "\tabstract fun visit_${token.getName()}(node: ${nodeType(token)}<${fullName(token)}>): R\n"
        } else {
            "\topen fun visit_${token.getName()}(node: ${nodeType(token)}<${fullName(token)}>): R " +
                    "{\n\t\treturn visitTerminal(node.getToken())\n\t}\n"
        }
    }

    private fun collectVisitMethods(): String {
        return tokens.joinToString("") { token -> visitMethod(token) }
    }

}