package translate.codegen

import grammar.token.Token
import structure.ASTNode
import translate.meta.MetaBaseVisitor
import translate.meta.MetaDescription

class DescriptionBuilder(
    private val packageName: String,
    private val objectName: String
) : MetaBaseVisitor<String>() {

    private val skippedTokens = mutableListOf<String>()
    private val lexerTokens = mutableListOf<String>()
    private val stateTokens = mutableListOf<String>()

    override fun <T : Token> collect(root: ASTNode<T>): String {
        val collected = visit(root)
        return """/**
This code is generated by [translate.codegen.DescriptionBuilder]
deriving from base class [translate.meta.MetaBaseVisitor] generated by [translate.codegen.VisitorBuilder]
*/

package $packageName

import grammar.token.Token
import grammar.Grammar
import grammar.Expansion
import structure.Description

object ${objectName}Description : Description {

    override fun getSkippedTokens(): Set<Token> {
        return setOf(${skippedTokens.joinToString(", ")})
    }
    
    override fun getGrammar(): Grammar {
        return grammar
    }
    
    init {
        check(${lexerTokens.joinToString(", ")})
    }
    
${stateTokens.joinToString("\n") { token ->
            "\tobject $token : Token.StateToken(\"$token\")"
        }}

$collected

}
"""
    }

    override fun <T : Token> visitTerminal(token: T): String {
        return token.getText()
    }

    override fun visit_CAPSNAME(node: ASTNode.TerminalNode<Token.VariantToken.VariantInstanceToken<MetaDescription.CAPSNAME>>): String {
        val name = super.visit_CAPSNAME(node)
        return if (name == "EPSILON") "Token.UniqueToken.EPSILON" else name
    }

    override fun visit_RSTRING(node: ASTNode.TerminalNode<Token.VariantToken.VariantInstanceToken<MetaDescription.RSTRING>>): String {
        return super.visit_RSTRING(node).substring(1)
    }

    private fun visitList(sep: String, vararg elements: String): String {
        return elements.filter { it.isNotBlank() }.joinToString(sep)
    }

    override fun visit_all(node: ASTNode.InnerNode<MetaDescription.all>): String {
        return visitList(
            "\n",
            visit_m(node.getChild(0)),
            visit_t(node.getChild(1)),
            visit_g(node.getChild(2))
        )
    }

    override fun visit_m(node: ASTNode.InnerNode<MetaDescription.m>): String {
        return if (node.children.size == 1) "" else visit_kfPlus(node.getChild(2))
    }

    override fun visit_t(node: ASTNode.InnerNode<MetaDescription.t>): String {
        return visitList(
            "\n",
            visit_tComp(node.getChild(2)),
            visit_tFrag(node.getChild(3)),
            visit_tPlus(node.getChild(4))
        )
    }

    override fun visit_g(node: ASTNode.InnerNode<MetaDescription.g>): String {
        return """
    private val grammar = Grammar(
        ${visit_gComp(node.getChild(2))},
        
${visit_gPlus(node.getChild(3))}
    ).order()"""
    }

    override fun visit_kfPlus(node: ASTNode.InnerNode<MetaDescription.kfPlus>): String {
        return if (node.children.size == 1) "" else
            visit_KOTLIN_FUNC(node.getChild(0)) + "\n" + visit_kfPlus(node.getChild(1))
    }

    override fun visit_tComp(node: ASTNode.InnerNode<MetaDescription.tComp>): String {
        return if (node.children.size == 1) "" else visit_tSkip(node.getChild(2))
    }

    override fun visit_tFrag(node: ASTNode.InnerNode<MetaDescription.tFrag>): String {
        return if (node.children.size == 1) "" else visit_tFragPlus(node.getChild(2))
    }

    override fun visit_tFragPlus(node: ASTNode.InnerNode<MetaDescription.tFragPlus>): String {
        return if (node.children.size == 1) "" else
            visitList("\n", visit_tFragLine(node.getChild(0)), visit_tFragPlus(node.getChild(1)))
    }

    override fun visit_tFragLine(node: ASTNode.InnerNode<MetaDescription.tFragLine>): String {
        return "\tval ${visit_CAPSNAME(node.getChild(0))} = ${visit_STRING(node.getChild(2))}"
    }

    override fun visit_tPlus(node: ASTNode.InnerNode<MetaDescription.tPlus>): String {
        return if (node.children.size == 1) "" else
            visitList("\n", visit_tLine(node.getChild(0)), visit_tPlus(node.getChild(1)))
    }

    override fun visit_tSkip(node: ASTNode.InnerNode<MetaDescription.tSkip>): String {
        visit_tArray(node.getChild(2))
        return ""
    }

    override fun visit_tArray(node: ASTNode.InnerNode<MetaDescription.tArray>): String {
        skippedTokens.add(visit_CAPSNAME(node.getChild(1)))
        return visit_tArrayPlus(node.getChild(2))
    }

    override fun visit_tArrayPlus(node: ASTNode.InnerNode<MetaDescription.tArrayPlus>): String {
        return if (node.children.size == 1) "" else {
            skippedTokens.add(visit_CAPSNAME(node.getChild(1)))
            visit_tArrayPlus(node.getChild(2))
        }
    }

    override fun visit_tLine(node: ASTNode.InnerNode<MetaDescription.tLine>): String {
        val name = visit_CAPSNAME(node.getChild(0))
        lexerTokens.add(name)
        return "\tobject $name : ${visit_tDef(node.getChild(2)).format(name)}"
    }

    override fun visit_tDef(node: ASTNode.InnerNode<MetaDescription.tDef>): String {
        return when (val token = node.children[0].getToken()) {
            is Token.VariantToken.VariantInstanceToken<*> -> {
                when (token.origin) {
                    is MetaDescription.STRING -> "Token.StringToken(\"%s\", ${visit_STRING(node.getChild(0))})"
                    is MetaDescription.RSTRING -> "Token.RegexToken(\"%s\", ${visit_RSTRING(node.getChild(0))}.toRegex())"
                    else -> throw IllegalStateException("Invalid origin for instance $token in expansion of tDef")
                }
            }
            is MetaDescription.LTRIG -> "Token.CharRangeToken(\"%s\", " +
                    "${visit_CHAR(node.getChild(1))}..${visit_CHAR(node.getChild(3))})"
            else -> throw IllegalStateException("Invalid token $token in expansion of tDef")
        }
    }

    override fun visit_gComp(node: ASTNode.InnerNode<MetaDescription.gComp>): String {
        return visit_gStart(node.getChild(5)) // TODO
    }

    override fun visit_gPlus(node: ASTNode.InnerNode<MetaDescription.gPlus>): String {
        return if (node.children.size == 1) "" else
            visitList(",\n", visit_gLine(node.getChild(0)), visit_gPlus(node.getChild(1)))
    }

    override fun visit_gSynth(node: ASTNode.InnerNode<MetaDescription.gSynth>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit_gInh(node: ASTNode.InnerNode<MetaDescription.gInh>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit_gCompv(node: ASTNode.InnerNode<MetaDescription.gCompv>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit_gStart(node: ASTNode.InnerNode<MetaDescription.gStart>): String {
        return visit_CAMELNAME(node.getChild(2))
    }

    override fun visit_attribs(node: ASTNode.InnerNode<MetaDescription.attribs>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit_attrib(node: ASTNode.InnerNode<MetaDescription.attrib>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit_attribsPlus(node: ASTNode.InnerNode<MetaDescription.attribsPlus>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit_type(node: ASTNode.InnerNode<MetaDescription.type>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit_setDef(node: ASTNode.InnerNode<MetaDescription.setDef>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit_defValue(node: ASTNode.InnerNode<MetaDescription.defValue>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit_gLine(node: ASTNode.InnerNode<MetaDescription.gLine>): String {
        val name = visit_CAMELNAME(node.getChild(0))
        stateTokens.add(name)
        return visit_rules(node.getChild(3)).split("|").joinToString(",\n") { rule ->
            "\t\t$name into $rule"
        }
    }

    override fun visit_def(node: ASTNode.InnerNode<MetaDescription.def>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit_rules(node: ASTNode.InnerNode<MetaDescription.rules>): String {
        return visitList("|", visit_rule(node.getChild(0)), visit_rulesPlus(node.getChild(1)))
    }

    override fun visit_rule(node: ASTNode.InnerNode<MetaDescription.rule>): String {
        return "Expansion(${visit_seq(node.getChild(0))})" // TODO
    }

    override fun visit_rulesPlus(node: ASTNode.InnerNode<MetaDescription.rulesPlus>): String {
        return if (node.children.size == 1) "" else
            visitList("|", visit_rule(node.getChild(1)), visit_rulesPlus(node.getChild(2)))
    }

    override fun visit_seq(node: ASTNode.InnerNode<MetaDescription.seq>): String {
        return visitList(", ", visit_atom(node.getChild(0)), visit_seqPlus(node.getChild(1)))
    }

    override fun visit_atom(node: ASTNode.InnerNode<MetaDescription.atom>): String {
        return when (val token = node.children[0].getToken()) {
            is Token.VariantToken.VariantInstanceToken<*> -> {
                when (token.origin) {
                    is MetaDescription.CAPSNAME -> visit_CAPSNAME(node.getChild(0))
                    is MetaDescription.CAMELNAME -> visit_CAMELNAME(node.getChild(0))
                    else -> throw IllegalStateException("Invalid token $token met in expansion of atom")
                }
            }
            else -> throw IllegalStateException("Invalid token $token met in expansion of atom")
        } // TODO
    }

    override fun visit_seqPlus(node: ASTNode.InnerNode<MetaDescription.seqPlus>): String {
        return if (node.children.size == 1) "" else
            visitList(", ", visit_atom(node.getChild(0)), visit_seqPlus(node.getChild(1)))
    }

    override fun visit_pass(node: ASTNode.InnerNode<MetaDescription.pass>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit_defBody(node: ASTNode.InnerNode<MetaDescription.defBody>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit_defAtom(node: ASTNode.InnerNode<MetaDescription.defAtom>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit_defPlus(node: ASTNode.InnerNode<MetaDescription.defPlus>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit_defTerm(node: ASTNode.InnerNode<MetaDescription.defTerm>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit_defMod(node: ASTNode.InnerNode<MetaDescription.defMod>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit_atName(node: ASTNode.InnerNode<MetaDescription.atName>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit_op(node: ASTNode.InnerNode<MetaDescription.op>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}