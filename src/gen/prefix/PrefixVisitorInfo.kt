/**
This code is generated by [translate.codegen.VisitorInfoBuilder]
deriving from base class [translate.meta.visitors.MetaVisitorBase] generated by [translate.meta.visitors.MetaVisitorInfo]
*/

package gen.prefix

import grammar.token.Token
import grammar.Grammar
import grammar.Expansion
import utils.Beautifier

import structure.ASTNode
import structure.Visitor

import translate.codegen.info.VisitorInfo
import translate.codegen.info.GrammarInfo

object PrefixVisitorInfo : VisitorInfo {

    val grammarInfo = PrefixGrammarInfo
    val packageName = "gen.prefix"
    val className = "PrefixVisitorInfoGenerated"
    
    override fun getDefinedTokens(): List<Token> {
        return grammarInfo.getDefinedTokens()
    }
    
    override fun getFullName(token: Token): String {
        return "${PrefixGrammarInfo.getName()}.$token"
    }
    
    override fun getNodeType(token: Token): String {
        return when (token) {
            is Token.StateToken -> 
                "PrefixVisitorInfoInnerNode<${getFullName(token)}>"
            is Token.DataToken -> 
                "PrefixVisitorInfoTerminalNode<${getFullName(token)}>"
            is Token.VariantToken -> 
                "PrefixVisitorInfoTerminalNode<Token.VariantToken.VariantInstanceToken<${getFullName(token)}>>"
            else -> throw IllegalArgumentException("Unexpected token $token type")
        }
    }
    
    override fun getVisitMethods(token: Token): String {
        return when (token) {
            is Token.StateToken -> getStateVisitMethods(token)
            else -> getTerminalVisitMethods(token)
        }
    }
    
    private fun getStateVisitMethods(token: Token.StateToken): String {
        val expansions = grammarInfo.getGrammar().RULES[token].expansions
        return if (expansions.size == 1) {
            """
    /**
    $token -> ${expansions.first()}
    */
    fun visit_${token}(node: ${getNodeType(token)}): R """
        } else {
            """
    fun visit_${token}(node: ${getNodeType(token)}): R {
        return when (val id = node.getExpansion().getId()) {
${expansions.joinToString("\n") { "\t\t\t${it.getId()} -> visit_${token}_${it.getId()}(node)" }}
            else -> throw IllegalStateException("Unexpected expansion id ${"$"}id in expansion of $token")
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
        return """
    fun visit_${token}(node: ${getNodeType(token)}): R {
        return visitTerminal(node.getToken())
    }"""
    }
    
    override fun getAll(): String {
        return Beautifier.detabify(
            """/**
This code is generated by [${PrefixVisitorInfo::class.qualifiedName}] derived from [${VisitorInfo::class.qualifiedName}]
based on grammar description [${PrefixGrammarInfo::class.qualifiedName}] derived from [${GrammarInfo::class.qualifiedName}]
*/

package $packageName

import ${Visitor::class.qualifiedName}
import ${ASTNode::class.qualifiedName}
import ${Token::class.qualifiedName}
import $packageName.${grammarInfo.getName()}

@Suppress("UNCHECKED_CAST")
interface $className<R> : ${Visitor::class.simpleName}<R> {

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
    

}
