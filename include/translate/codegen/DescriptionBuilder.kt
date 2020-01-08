package translate.codegen

import grammar.token.Token
import structure.ASTNode
import translate.meta.MetaDescription
import java.io.File
import java.lang.StringBuilder

class DescriptionBuilder {

    fun walk(tree: ASTNode<*>) {
        File("src/translate/gen/Me.kt").bufferedWriter().use {
            it.write(
                """
package gen.gen

import grammar.Expansion
import grammar.Grammar
import grammar.token.Token
import structure.Description

object Me : Description {

${this.collect(tree)}

    override fun getGrammar(): Grammar {
        return grammar
    }

}
"""
            )
        }
    }

    private fun collect(tree: ASTNode<*>): String {
        val sb = StringBuilder()
        val root = tree as ASTNode.InnerNode<*>
        collectTokens(root.children.first { it.getToken() == Token.REGISTERED["TOKENS"] }, sb)
        collectGrammar(root.children.first { it.getToken() == Token.REGISTERED["GRAMMAR"] }, sb)
        return sb.toString()
    }

    private fun collectTokens(tree: ASTNode<*>, sb: StringBuilder) {
        collectTplus((tree as ASTNode.InnerNode<*>).children.first { it.getToken() == Token.REGISTERED["T+"] }, sb)
        val comp = tree.children[2]
        val s = if ((comp as ASTNode.InnerNode<*>).children.size == 1) {
            "emptySet()"
        } else {
            val skip = comp.children[2]
            if ((skip as ASTNode.InnerNode<*>).children.size == 1) {
                "emptySet()"
            } else {
                "setOf(${collectTarray(skip.children[2]).joinToString(", ")})"
            }
        }
        sb.append(
            """
    override fun getSkippedTokens(): Set<Token> {
        return $s
    }
    
"""
        )
    }

    private fun collectTarray(tree: ASTNode<*>): List<String> {
        if ((tree as ASTNode.InnerNode<*>).children.size == 1) {
            return emptyList()
        }
        val s = tree.children[1].getToken().toString()
        return listOf(s.substring(1, s.length - 1)) + collectTarray(tree.children[2])
    }

    private fun collectTplus(tree: ASTNode<*>, sb: StringBuilder) {
        if ((tree as ASTNode.InnerNode<*>).children.size == 1) {
            return
        }
        collectTline(tree.children[0], sb)
        collectTplus(tree.children[1], sb)
    }

    private fun collectTline(tree: ASTNode<*>, sb: StringBuilder) {
        val sname = ((tree as ASTNode.InnerNode<*>).children[0].getToken()
                as Token.VariantToken.VariantInstanceToken).value
        sb.append("\tval $sname = ")
        val repr = tree.children[2] as ASTNode.InnerNode<*>
        sb.append(
            when (repr.children[0].getToken()) {
                is Token.VariantToken.VariantInstanceToken -> {
                    val t = repr.children[0].getToken() as Token.VariantToken.VariantInstanceToken
                    if (t.origin == MetaDescription.STRING) {
                        "Token.StringToken(\"<${sname.toLowerCase()}>\", ${t.value})\n"
                    } else {
                        "Token.RegexToken(\"<${sname.toLowerCase()}>\", ${t.value.substring(1)}.toRegex())\n"
                    }

                }
                else -> {
                    fun strip(token: Token): String {
                        return (token as Token.VariantToken.VariantInstanceToken).value
                    }
                    "Token.CharRangeToken(\"<${sname.toLowerCase()}>\", ${strip(repr.children[1].getToken())}..${strip(
                        repr.children[3].getToken()
                    )})\n"
                }
            }
        )
    }

    private fun collectGrammar(tree: ASTNode<*>, sb: StringBuilder) {
        val st =
            (((tree as ASTNode.InnerNode<*>).children[2] as ASTNode.InnerNode).children[5] as ASTNode.InnerNode).children[2].getToken()
                .toString()
        sb.append(
            """
    private val grammar = Grammar(
        ${st.substring(1, st.length - 1).toUpperCase()},
        
${collectRules(tree.children[3], sb).joinToString(",\n")}  
    ).order()"""
        )
    }

    private fun collectRules(tree: ASTNode<*>, sb: StringBuilder): List<String> {
        if ((tree as ASTNode.InnerNode<*>).children.size == 1) {
            return emptyList()
        }
        return collectLine(tree.children[0], sb) + collectRules(tree.children[1], sb)
    }

    private fun collectLine(tree: ASTNode<*>, sb: StringBuilder): List<String> {
        val s =
            ((tree as ASTNode.InnerNode<*>).children[0].getToken() as Token.VariantToken.VariantInstanceToken).value.toUpperCase()
        sb.append("\tval $s = Token.StateToken(\"$s\")\n")
        var tmp = tree.children[3]

        val res = mutableListOf<String>()
        while ((tmp as ASTNode.InnerNode<*>).children.size > 1) {
            res.add("\t\t$s into Expansion(${collectSequence(
                (tmp.children.first { it.getToken() == MetaDescription.rule } as ASTNode.InnerNode).children[0]
            ).filter { !it.matches("\\s*".toRegex()) }.joinToString(", ")})"
            )
            tmp = tmp.children.first { it.getToken() == MetaDescription.rulesPlus }
        }
        return res
    }

    private fun collectSequence(tree: ASTNode<*>): List<String> {
        if ((tree as ASTNode.InnerNode<*>).children.size == 1) {
            return emptyList()
        }
        var s =
            ((tree.children[0] as ASTNode.InnerNode<*>).children[0].getToken() as Token.VariantToken.VariantInstanceToken).value
        s = if (s == "EPSILON") {
            "Token.UniqueToken.EPSILON"
        } else {
            s.toUpperCase()
        }
        return listOf(s) +
                collectSequence(tree.children[1])
    }

}