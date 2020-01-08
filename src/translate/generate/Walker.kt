package translate.generate

import grammar.token.Token
import structure.Tree
import java.io.File
import java.lang.StringBuilder

class Walker {

    fun walk(tree: Tree) {
        File("src/translate/gen/Me.kt").bufferedWriter().use {
            it.write(
                """
package translate.gen

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

    private fun collect(tree: Tree): String {
        val sb = StringBuilder()
        val root = tree as Tree.InnerNode
        collectTokens(root.children.first { it.token == Token.REGISTERED["TOKENS"] }, sb)
        collectGrammar(root.children.first { it.token == Token.REGISTERED["GRAMMAR"] }, sb)
        return sb.toString()
    }

    private fun collectTokens(tree: Tree, sb: StringBuilder) {
        collectTplus((tree as Tree.InnerNode).children.first { it.token == Token.REGISTERED["T+"] }, sb)
        val comp = tree.children[2]
        val s = if ((comp as Tree.InnerNode).children.size == 1) {
            "emptySet()"
        } else {
            val skip = comp.children[2]
            if ((skip as Tree.InnerNode).children.size == 1) {
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

    private fun collectTarray(tree: Tree): List<String> {
        if ((tree as Tree.InnerNode).children.size == 1) {
            return emptyList()
        }
        val s = tree.children[1].token.toString()
        return listOf(s.substring(1, s.length - 1)) + collectTarray(tree.children[2])
    }

    private fun collectTplus(tree: Tree, sb: StringBuilder) {
        if ((tree as Tree.InnerNode).children.size == 1) {
            return
        }
        collectTline(tree.children[0], sb)
        collectTplus(tree.children[1], sb)
    }

    private fun collectTline(tree: Tree, sb: StringBuilder) {
        val sname = ((tree as Tree.InnerNode).children[0].token as Token.VariantToken.VariantInstanceToken).value
        sb.append("\tval $sname = ")
        val repr = tree.children[2] as Tree.InnerNode
        sb.append(
            when (repr.children[0].token) {
                is Token.VariantToken.VariantInstanceToken -> {
                    val t = repr.children[0].token as Token.VariantToken.VariantInstanceToken
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
                    "Token.CharRangeToken(\"<${sname.toLowerCase()}>\", ${strip(repr.children[1].token)}..${strip(repr.children[3].token)})\n"
                }
            }
        )
    }

    private fun collectGrammar(tree: Tree, sb: StringBuilder) {
        val st =
            (((tree as Tree.InnerNode).children[2] as Tree.InnerNode).children[5] as Tree.InnerNode).children[2].token.toString()
        sb.append(
            """
    private val grammar = Grammar(
        ${st.substring(1, st.length - 1).toUpperCase()},
        
${collectRules(tree.children[3], sb).joinToString(",\n")}  
    ).order()"""
        )
    }

    private fun collectRules(tree: Tree, sb: StringBuilder): List<String> {
        if ((tree as Tree.InnerNode).children.size == 1) {
            return emptyList()
        }
        return collectLine(tree.children[0], sb) + collectRules(tree.children[1], sb)
    }

    private fun collectLine(tree: Tree, sb: StringBuilder): List<String> {
        val s = ((tree as Tree.InnerNode).children[0].token as Token.VariantToken.VariantInstanceToken).value.toUpperCase()
        sb.append("\tval $s = Token.StateToken(\"$s\")\n")
        var tmp = tree.children[3]

        val res = mutableListOf<String>()
        while ((tmp as Tree.InnerNode).children.size > 1) {
            res.add("\t\t$s into Expansion(${collectSequence(
                (tmp.children.first { it.token == MetaDescription.RULE } as Tree.InnerNode).children[0]
            ).filter { !it.matches("\\s*".toRegex()) }.joinToString(", ")})"
            )
            tmp = tmp.children.first { it.token == MetaDescription.RULESPLUS }
        }
        return res
    }

    private fun collectSequence(tree: Tree): List<String> {
        if ((tree as Tree.InnerNode).children.size == 1) {
            return emptyList()
        }
        var s = ((tree.children[0] as Tree.InnerNode).children[0].token as Token.VariantToken.VariantInstanceToken).value
        if (s == "EPSILON") {
            s = "Token.UniqueToken.EPSILON"
        } else {
            s = s.toUpperCase()
        }
        return listOf(s) +
                collectSequence(tree.children[1])
    }

}