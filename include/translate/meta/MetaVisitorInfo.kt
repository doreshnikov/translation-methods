package translate.meta

import grammar.token.Token
import translate.codegen.info.VisitorInfo

object MetaVisitorInfo : VisitorInfo {

    private val grammarInfo = MetaGrammarInfo

    private fun nodeType(token: Token): String {
        return when (token) {
            is Token.StateToken -> "ASTNode.InnerNode<${fullName(token)}>"
            is Token.DataToken -> "ASTNode.TerminalNode<${fullName(token)}>"
            is Token.VariantToken -> "ASTNode.TerminalNode<Token.VariantToken.VariantInstanceToken<${fullName(token)}>>"
            else -> throw IllegalArgumentException("Unexpected token $token type")
        }
    }

    private fun fullName(token: Token): String {
        return "${grammarInfo.getName()}.$token"
    }

    val tokens = grammarInfo.getAll().filter { it !is Token.UniqueToken }

    private val choiceVisit = collectChoiceVisit()
    private val visitMethods = collectVisitMethods()

    override fun getChoiceVisit(): String {
        return choiceVisit
    }

    override fun getVisitMethods(): String {
        return visitMethods
    }

    private fun collectChoiceVisit(): String {
        return """
        return when(node.getToken()) {
${tokens.joinToString("\n") { token ->
            "\t\t\t${fullName(token)} -> visit_${token}(node as ${nodeType(token)})"
        }}
            else -> throw IllegalStateException("Unknown token ${"$"}{node.getToken()} met")
        }"""
    }

    private fun visitStateMethod(token: Token.StateToken): String {
        val expansions = grammarInfo.getGrammar().RULES[token].expansions
        return if (expansions.size == 1) {
            """
    /**
    $token -> ${expansions.first()}
    */
    fun visit_${token}(node: ${nodeType(token)}): R
"""
        } else {
            """
    fun visit_${token}(node: ${nodeType(token)}): R {
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
    fun visit_${token}_${it.getId()}(node: ${nodeType(token)}): R"""
            }}"""
        }
    }

    private fun visitTerminalMethod(token: Token): String {
        return "\tfun visit_${token}(node: ${nodeType(token)}): R " +
                "{\n\t\treturn visitTerminal(node.getToken())\n\t}"
    }

    private fun collectVisitMethods(): String {
        return tokens.joinToString("\n") { token ->
            when (token) {
                is Token.StateToken -> visitStateMethod(token)
                else -> visitTerminalMethod(token)
            }
        }
    }

}