package translate.codegen

import grammar.Expansion
import grammar.Grammar
import grammar.token.Token
import structure.ASTNode
import translate.codegen.helpers.GrammarInfo
import translate.meta.helpers.MetaGrammarInfo
import translate.meta.MetaWalkerBase
import utils.Beautifier.detabify

class GrammarInfoBuilder(
    private val packageName: String,
    private val name: String
) : MetaWalkerBase<String> {

    private val skippedTokens = mutableListOf<String>()
    private val lexerTokens = mutableListOf<String>()
    private val stateTokens = mutableListOf<String>()

    private val objectName = "${name}GrammarInfo"

    override fun <T : Token> collect(root: ASTNode<T>): String {
        val collected = visit(root)
        return detabify(
            """/**
This code is generated by [${GrammarInfoBuilder::class.qualifiedName}] derived from [${MetaWalkerBase::class.qualifiedName}] 
generated by [${WalkerBaseBuilder::class.qualifiedName}] 
basing on grammar description [${MetaGrammarInfo::class.qualifiedName}] derived from [${GrammarInfo::class.qualifiedName}]
*/

package $packageName

import ${Token::class.qualifiedName}
import ${Grammar::class.qualifiedName}
import ${Expansion::class.qualifiedName}

import ${GrammarInfo::class.qualifiedName}

object $objectName : ${GrammarInfo::class.simpleName} {

    override fun getSkippedTokens(): Set<${Token::class.simpleName}> {
        return setOf(${skippedTokens.joinToString(", ")})
    }
    
    override fun getGrammar(): ${Grammar::class.simpleName} {
        return grammar
    }
    
    override fun getDefinedTokens(): List<${Token::class.simpleName}> {
        return listOf(
            ${lexerTokens.joinToString(", ")},
            ${stateTokens.joinToString(", ")}            
        )
    }
    
    override fun getSimpleName(): String {
        return "$name"
    }
    
    init {
        ${Token::class.simpleName}.switchTo(getName())
        check()
    }
    
${stateTokens.joinToString("\n")
            { "\tobject $it : ${Token::class.simpleName}.${Token.StateToken::class.simpleName}(\"$it\")" }}

$collected

}"""
        )
    }

    override fun <T : Token> visitTerminal(token: T): String {
        return token.getText()
    }

    override fun visit_CAPSNAME(node: ASTNode.TerminalNode<Token.VariantToken.VariantInstanceToken<MetaGrammarInfo.CAPSNAME>>): String {
        val tokenName = super.visit_CAPSNAME(node)
        return if (tokenName == Token.UniqueToken.EPSILON.getName())
            "${Token::class.simpleName}.${Token.UniqueToken::class.simpleName}.${Token.UniqueToken.EPSILON::class.simpleName}"
        else tokenName
    }

    override fun visit_RSTRING(node: ASTNode.TerminalNode<Token.VariantToken.VariantInstanceToken<MetaGrammarInfo.RSTRING>>): String {
        return super.visit_RSTRING(node).substring(1)
    }

    private fun visitList(sep: String, vararg elements: String): String {
        return elements.filter { it.isNotBlank() }.joinToString(sep)
    }

    /**
    all -> m t g
     */
    override fun visit_all(node: ASTNode.InnerNode<MetaGrammarInfo.all>): String {
        return visitList(
            "\n",
            visit_t(node.getChild(1)),
            visit_g(node.getChild(2))
        )
    }

    /**
    t -> TOKENS LPAREN tComp tFrag tPlus RPAREN
     */
    override fun visit_t(node: ASTNode.InnerNode<MetaGrammarInfo.t>): String {
        return visitList(
            "\n",
            visit_tComp(node.getChild(2)),
            visit_tFrag(node.getChild(3)),
            visit_tPlus(node.getChild(4))
        )
    }

    /**
    tComp -> COMPANION LPAREN tSkip RPAREN
     */
    override fun visit_tComp_0(node: ASTNode.InnerNode<MetaGrammarInfo.tComp>): String {
        return visit_tSkip(node.getChild(2))
    }

    /**
    tComp -> <eps>
     */
    override fun visit_tComp_1(node: ASTNode.InnerNode<MetaGrammarInfo.tComp>): String {
        return ""
    }

    /**
    tSkip -> SKIP DESCRIBE tArray EOLN
     */
    override fun visit_tSkip(node: ASTNode.InnerNode<MetaGrammarInfo.tSkip>): String {
        visit_tArray(node.getChild(2))
        return ""
    }

    /**
    tArray -> LARRAY CAPSNAME tArrayPlus RARRAY
     */
    override fun visit_tArray(node: ASTNode.InnerNode<MetaGrammarInfo.tArray>): String {
        skippedTokens.add(visit_CAPSNAME(node.getChild(1)))
        return visit_tArrayPlus(node.getChild(2))
    }

    /**
    tArrayPlus -> SEP CAPSNAME tArrayPlus
     */
    override fun visit_tArrayPlus_0(node: ASTNode.InnerNode<MetaGrammarInfo.tArrayPlus>): String {
        skippedTokens.add(visit_CAPSNAME(node.getChild(1)))
        return visit_tArrayPlus(node.getChild(2))
    }

    /**
    tArrayPlus -> <eps>
     */
    override fun visit_tArrayPlus_1(node: ASTNode.InnerNode<MetaGrammarInfo.tArrayPlus>): String {
        return ""
    }

    /**
    tFrag -> FRAGMENTS LPAREN tFragPlus RPAREN
     */
    override fun visit_tFrag_0(node: ASTNode.InnerNode<MetaGrammarInfo.tFrag>): String {
        return visit_tFragPlus(node.getChild(2))
    }

    /**
    tFrag -> <eps>
     */
    override fun visit_tFrag_1(node: ASTNode.InnerNode<MetaGrammarInfo.tFrag>): String {
        return ""
    }

    /**
    tFragLine -> CAPSNAME DESCRIBE STRING EOLN
     */
    override fun visit_tFragLine(node: ASTNode.InnerNode<MetaGrammarInfo.tFragLine>): String {
        return "\tval ${visit_CAPSNAME(node.getChild(0))} = ${visit_STRING(node.getChild(2))}"
    }

    /**
    tFragPlus -> tFragLine tFragPlus
     */
    override fun visit_tFragPlus_0(node: ASTNode.InnerNode<MetaGrammarInfo.tFragPlus>): String {
        return visitList("\n", visit_tFragLine(node.getChild(0)), visit_tFragPlus(node.getChild(1)))
    }

    /**
    tFragPlus -> <eps>
     */
    override fun visit_tFragPlus_1(node: ASTNode.InnerNode<MetaGrammarInfo.tFragPlus>): String {
        return ""
    }

    /**
    tLine -> CAPSNAME DESCRIBE tDef EOLN
     */
    override fun visit_tLine(node: ASTNode.InnerNode<MetaGrammarInfo.tLine>): String {
        val tokenName = visit_CAPSNAME(node.getChild(0))
        lexerTokens.add(tokenName)
        return "\tobject $tokenName : ${visit_tDef(node.getChild(2)).format(tokenName)}"
    }

    /**
    tDef -> STRING
     */
    override fun visit_tDef_0(node: ASTNode.InnerNode<MetaGrammarInfo.tDef>): String {
        return "${Token::class.simpleName}.${Token.StringToken::class.simpleName}(\"%s\", ${visit_STRING(node.getChild(0))})"
    }

    /**
    tDef -> RSTRING
     */
    override fun visit_tDef_1(node: ASTNode.InnerNode<MetaGrammarInfo.tDef>): String {
        return "${Token::class.simpleName}.${Token.RegexToken::class.simpleName}(\"%s\", ${visit_RSTRING(node.getChild(0))}.toRegex())"
    }

    /**
    tDef -> LTRIG CHAR CHARRANGE CHAR RTRIG
     */
    override fun visit_tDef_2(node: ASTNode.InnerNode<MetaGrammarInfo.tDef>): String {
        return "${Token::class.simpleName}.${Token.CharRangeToken::class.simpleName}" +
                "(\"%s\", ${visit_CHAR(node.getChild(1))}..${visit_CHAR(node.getChild(3))})"
    }

    /**
    tPlus -> tLine tPlus
     */
    override fun visit_tPlus_0(node: ASTNode.InnerNode<MetaGrammarInfo.tPlus>): String {
        return visitList("\n", visit_tLine(node.getChild(0)), visit_tPlus(node.getChild(1)))
    }

    /**
    tPlus -> <eps>
     */
    override fun visit_tPlus_1(node: ASTNode.InnerNode<MetaGrammarInfo.tPlus>): String {
        return ""
    }

    /**
    g -> GRAMMAR LPAREN gComp gPlus RPAREN
     */
    override fun visit_g(node: ASTNode.InnerNode<MetaGrammarInfo.g>): String {
        return """
    private val grammar = ${Grammar::class.simpleName}(
${visit_gComp(node.getChild(2))},

${visit_gPlus(node.getChild(3))}
    ).order()"""
    }

    /**
    gComp -> COMPANION LPAREN gSynth gInh gCompv gStart RPAREN
     */
    override fun visit_gComp(node: ASTNode.InnerNode<MetaGrammarInfo.gComp>): String {
        return "\t\t${visit_gStart(node.getChild(5))}"
    }

    /**
    gStart -> START DESCRIBE CAMELNAME EOLN
     */
    override fun visit_gStart(node: ASTNode.InnerNode<MetaGrammarInfo.gStart>): String {
        return visit_CAMELNAME(node.getChild(2))
    }

    /**
    gLine -> CAMELNAME def DESCRIBE rules EOLN
     */
    override fun visit_gLine(node: ASTNode.InnerNode<MetaGrammarInfo.gLine>): String {
        val tokenName = visit_CAMELNAME(node.getChild(0))
        stateTokens.add(tokenName)
        return visit_rules(node.getChild(3)).split("|").joinToString(",\n") { rule ->
            "\t\t$tokenName into $rule"
        }
    }

    /**
    gPlus -> gLine gPlus
     */
    override fun visit_gPlus_0(node: ASTNode.InnerNode<MetaGrammarInfo.gPlus>): String {
        return visitList(",\n", visit_gLine(node.getChild(0)), visit_gPlus(node.getChild(1)))
    }

    /**
    gPlus -> <eps>
     */
    override fun visit_gPlus_1(node: ASTNode.InnerNode<MetaGrammarInfo.gPlus>): String {
        return ""
    }

    /**
    rules -> rule rulesPlus
     */
    override fun visit_rules(node: ASTNode.InnerNode<MetaGrammarInfo.rules>): String {
        return visitList("|", visit_rule(node.getChild(0)), visit_rulesPlus(node.getChild(1)))
    }

    /**
    rule -> seq def
     */
    override fun visit_rule(node: ASTNode.InnerNode<MetaGrammarInfo.rule>): String {
        return "${Expansion::class.simpleName}(${visit_seq(node.getChild(0))})"
    }

    /**
    rulesPlus -> CHOICE rule rulesPlus
     */
    override fun visit_rulesPlus_0(node: ASTNode.InnerNode<MetaGrammarInfo.rulesPlus>): String {
        return visitList("|", visit_rule(node.getChild(1)), visit_rulesPlus(node.getChild(2)))
    }

    /**
    rulesPlus -> <eps>
     */
    override fun visit_rulesPlus_1(node: ASTNode.InnerNode<MetaGrammarInfo.rulesPlus>): String {
        return ""
    }

    /**
    seq -> atom seqPlus
     */
    override fun visit_seq(node: ASTNode.InnerNode<MetaGrammarInfo.seq>): String {
        return visitList(", ", visit_atom(node.getChild(0)), visit_seqPlus(node.getChild(1)))
    }

    /**
    atom -> CAPSNAME
     */
    override fun visit_atom_0(node: ASTNode.InnerNode<MetaGrammarInfo.atom>): String {
        return visit_CAPSNAME(node.getChild(0))
    }

    /**
    atom -> CAMELNAME pass
     */
    override fun visit_atom_1(node: ASTNode.InnerNode<MetaGrammarInfo.atom>): String {
        return visit_CAMELNAME(node.getChild(0))
    }

    /**
    seqPlus -> atom seqPlus
     */
    override fun visit_seqPlus_0(node: ASTNode.InnerNode<MetaGrammarInfo.seqPlus>): String {
        return visitList(", ", visit_atom(node.getChild(0)), visit_seqPlus(node.getChild(1)))
    }

    /**
    seqPlus -> <eps>
     */
    override fun visit_seqPlus_1(node: ASTNode.InnerNode<MetaGrammarInfo.seqPlus>): String {
        return ""
    }

}