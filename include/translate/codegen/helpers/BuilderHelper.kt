package translate.codegen.helpers

import grammar.token.Token
import structure.ASTNode
import utils.Beautifier
import kotlin.reflect.KClass

interface BuilderHelper {

    fun className(clazz: KClass<*>) = Beautifier.className(clazz)

    fun getDefinedTokens(): List<Token>

    fun getFullName(token: Token): String

    fun getVisitTerminal(): String {
        return """
    fun <T : ${className(Token::class)}> visitTerminal(token: T): R"""
    }

    fun getNodeType(token: Token): String {
        return when (token) {
            is Token.StateToken -> "${className(ASTNode.InnerNode::class)}<${getFullName(token)}>"
            is Token.DataToken -> "${className(ASTNode.TerminalNode::class)}<${getFullName(token)}>"
            is Token.VariantToken -> className(ASTNode.TerminalNode::class) +
                    "<${className(Token.VariantToken.VariantInstanceToken::class)}<${getFullName(token)}>>"
            else -> throw IllegalArgumentException("Unexpected token $token type")
        }
    }

    fun getVisitMethods(token: Token): String

    fun getChoiceVisit(): String

    fun getVisitMethods(): String {
        return getDefinedTokens().joinToString("\n") { getVisitMethods(it) }
    }

    fun getAll(): String

}