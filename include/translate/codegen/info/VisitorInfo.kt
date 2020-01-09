package translate.codegen.info

import grammar.token.Token

interface VisitorInfo {

    fun getDefinedTokens(): List<Token>

    fun getFullName(token: Token): String

    fun getNodeType(token: Token): String

    fun getVisitMethods(token: Token): String

    fun getChoiceVisit(): String {
        return """
        return when(node.getToken()) {
            ${getDefinedTokens()
            .joinToString("\n\t\t\t") { "${getFullName(it)} -> visit_${it}(node as ${getNodeType(it)})" }}
            else -> throw IllegalStateException("Unknown token ${"$"}{node.getToken()} met")
        }"""
    }

    fun getVisitMethods(): String {
        return getDefinedTokens().joinToString("\n") { getVisitMethods(it) }
    }

    fun getAll(): String

}