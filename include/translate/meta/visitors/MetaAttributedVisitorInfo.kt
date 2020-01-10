package translate.meta.visitors

import grammar.token.Token
import structure.ASTNode
import structure.AttributedVisitor
import translate.codegen.info.AttributedVisitorInfo
import translate.codegen.info.GrammarInfo
import translate.codegen.info.VisitorInfo
import translate.meta.grammar.MetaGrammarInfo
import utils.Beautifier

object MetaAttributedVisitorInfo : AttributedVisitorInfo {

    private val packageName = "translate.meta.visitors"
    private val className = "MetaAttributedVisitorBase"
    private val grammarPackageName = "translate.meta.grammar"
    private val grammarInfo = MetaGrammarInfo

    override fun getDefinedTokens(): List<Token> {
        return grammarInfo.getDefinedTokens()
    }

    override fun getFullName(token: Token): String {
        return "${grammarInfo.getName()}.$token"
    }

    override fun getNodeType(token: Token) = MetaVisitorInfo.getNodeType(token)

    override fun getVisitMethods(token: Token): String {
        return when (token) {
            is Token.StateToken -> getStateVisitMethods(
                token
            )
            else -> getTerminalVisitMethods(token)
        }
    }

    override fun getAll(): String {
        return Beautifier.detabify(
            """/**
This code is generated by [${MetaAttributedVisitorInfo::class.qualifiedName}] derived from [${VisitorInfo::class.qualifiedName}]
based on grammar description [${MetaGrammarInfo::class.qualifiedName}] derived from [${GrammarInfo::class.qualifiedName}]
*/

package $packageName

import ${AttributedVisitor::class.qualifiedName}
import ${ASTNode::class.qualifiedName}
import ${Token::class.qualifiedName}
import $grammarPackageName.${grammarInfo.getName()}

@Suppress("UNCHECKED_CAST")
interface $className<R, A> : ${AttributedVisitor::class.simpleName}<R, A> {

/*
${grammarInfo.getGrammar()}
*/

    override fun visit(node: ${ASTNode::class.simpleName}<out ${Token::class.simpleName}>, value: A?): R {${getChoiceVisit()}
    }
    
    fun <T : ${Token::class.simpleName}> visitTerminal(token: T): R
    
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
    fun visit_${token}(node: ${getNodeType(token)}, value: A?): R {
        throw IllegalStateException("Unexpected expansion ${"$"}{node.getToken()} -> ${"$"}{node.getExpansion()} visited while visiting traversing tree")
    }"""
        } else {
            """
    fun visit_${token}(node: ${getNodeType(token)}, value: A?): R {
        return when (val id = node.getExpansion().getId()) {
${expansions.joinToString("\n") { "\t\t\t${it.getId()} -> visit_${token}_${it.getId()}(node, value)" }}
            else -> throw IllegalStateException("Unexpected expansion id ${"$"}id in expansion of $token")
        }
    }
${expansions.joinToString("\n") {
                """
    /**
    $token -> $it
    */
    fun visit_${token}_${it.getId()}(node: ${getNodeType(
                    token
                )}, value: A?): R {
        throw IllegalStateException("Unexpected expansion ${"$"}{node.getToken()} -> ${"$"}{node.getExpansion()} visited while visiting traversing tree") 
    }
    """
            }}"""
        }
    }

    private fun getTerminalVisitMethods(token: Token): String {
        return """
    fun visit_${token}(node: ${getNodeType(token)}, value: A?): R {
        return visitTerminal(node.getToken())
    }"""
    }

}