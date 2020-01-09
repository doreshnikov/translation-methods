package translate.meta

import grammar.token.Token
import translate.codegen.info.VisitorInfo
import utils.Beautifier

object MetaVisitorInfo : VisitorInfo {

    private val grammarInfo = MetaGrammarInfo
    private val packageName = "translate.meta"
    private val className = "MetaBaseVisitor"

    override fun getDefinedTokens(): List<Token> {
        return grammarInfo.getDefinedTokens()
    }

    override fun getFullName(token: Token): String {
        return "${grammarInfo.getName()}.$token"
    }

    override fun getNodeType(token: Token): String {
        return when (token) {
            is Token.StateToken -> "ASTNode.InnerNode<${getFullName(token)}>"
            is Token.DataToken -> "ASTNode.TerminalNode<${getFullName(token)}>"
            is Token.VariantToken -> "ASTNode.TerminalNode<Token.VariantToken.VariantInstanceToken<${getFullName(token)}>>"
            else -> throw IllegalArgumentException("Unexpected token $token type")
        }
    }

    override fun getVisitMethods(token: Token): String {
        return when (token) {
            is Token.StateToken -> getStateVisitMethods(token)
            else -> getTerminalVisitMethods(token)
        }
    }

    override fun getAll(): String {
        return Beautifier.detabify(
            """${getPrefix()}

package $packageName

import structure.Visitor
import structure.ASTNode
import grammar.token.Token
import $packageName.${grammarInfo.getName()}

@Suppress("UNCHECKED_CAST")
interface $className<R> : Visitor<R> {

/*
${grammarInfo.getGrammar()}
*/

    override fun visit(node: ASTNode<out Token>): R {${getChoiceVisit()}
    }
    
    fun <T : Token> visitTerminal(token: T): R
    
${getVisitMethods()}

}"""
        )
    }

    private fun getStateVisitMethods(token: Token.StateToken): String {
        val expansions = grammarInfo.getGrammar().RULES[token].expansions
        return if (expansions.size == 1) {
            """
    /**
    $token -> ${expansions.first()}
    */
    fun visit_${token}(node: ${getNodeType(token)}): R
"""
        } else {
            """
    fun visit_${token}(node: ${getNodeType(token)}): R {
        return when (val id = node.getExpansion().getId()) {
${expansions.joinToString("\n") { "\t\t\t${it.getId()} -> visit_${token}_${it.getId()}(node)" }}
            else -> throw IllegalStateException("Unexpected expansion id ${"$"}id in expansion of ${token}")
        }
    }
${expansions.joinToString("\n") {
                """
    /**
    $token -> $it
    */
    fun visit_${token}_${it.getId()}(node: ${getNodeType(token)}): R"""
            }}"""
        }
    }

    private fun getTerminalVisitMethods(token: Token): String {
        return "\tfun visit_${token}(node: ${getNodeType(token)}): R " +
                "{\n\t\treturn visitTerminal(node.getToken())\n\t}"
    }

}