package translate.codegen

import grammar.Expansion
import grammar.Grammar
import grammar.token.Token
import parse.Parser
import structure.ASTNode
import structure.Visitor
import translate.codegen.helpers.BuilderHelper
import translate.codegen.helpers.GrammarInfo
import translate.meta.MetaVisitorBase
import translate.meta.helpers.MetaGrammarInfo
import translate.meta.helpers.MetaVisitorBuilder
import utils.Beautifier

class VisitorBuilder(
    private val packageName: String,
    private val grammarInfoObject: GrammarInfo,
    private val metaRoot: ASTNode<*>
) : MetaVisitorBase<String, String?>, BuilderHelper {

    fun prepare(): VisitorBuilder {
        Token.switchTo(MetaGrammarInfo.getName())
        collect(metaRoot)
        Token.switchTo(grammarInfoObject.getName())
        return this
    }

    private val objectName: String = "${grammarInfoObject.getSimpleName()}Visitor"
    private val dataObjectName: String = "${grammarInfoObject.getSimpleName()}Data"

    data class Attribute(val name: String, val type: String) {
        override fun toString(): String {
            return "$name: $type?"
        }
    }

    data class ComputedAttribute(val name: String, val type: String, val init: String) {
        override fun toString(): String {
            return "val $name: $type?\n\t\tget() = $init"
        }
    }

    data class ValuedAttribute(val name: String, val value: String) {
        override fun toString(): String {
            return "$name = $value"
        }
    }

    private val synthesis = mutableListOf<Attribute>()
    private val inheritance = mutableListOf<Attribute>()
    private val compute = mutableListOf<ComputedAttribute>()

    private val defaults = mutableMapOf<String, String>()
    private val stateDefaults = mutableMapOf<String, List<ValuedAttribute>>()
    private val expansionDefaults = mutableMapOf<String, List<ValuedAttribute>>()
    private val expansionPasses = mutableMapOf<String, MutableMap<Int, List<ValuedAttribute>>>()

    private var macro = ""

    override fun <T : Token> visitTerminal(token: T): String {
        return token.getText()
    }

    private fun visitList(sep: String, vararg elements: String): String {
        return elements.filter { it.isNotBlank() }.joinToString(sep)
    }

    override fun <T : Token> collect(root: ASTNode<T>): String {
        return visit(root, null)
    }

    /**
    all -> m t g
     */
    override fun visit_all(node: ASTNode.InnerNode<MetaGrammarInfo.all>, value: String?): String {
        macro = visit_m(node.getChild(0), null)
        visit_g(node.getChild(2), null)
        return ""
    }

    /**
    m -> MACRO LPAREN kfPlus RPAREN
     */
    override fun visit_m_0(node: ASTNode.InnerNode<MetaGrammarInfo.m>, value: String?): String {
        return visit_kfPlus(node.getChild(2), null)
    }

    /**
    m -> <eps>
     */
    override fun visit_m_1(node: ASTNode.InnerNode<MetaGrammarInfo.m>, value: String?): String {
        return ""
    }

    /**
    kfPlus -> KOTLIN_FUNC kfPlus
     */
    override fun visit_kfPlus_0(node: ASTNode.InnerNode<MetaGrammarInfo.kfPlus>, value: String?): String {
        return visitList("\n", visit_KOTLIN_FUNC(node.getChild(0), null), visit_kfPlus(node.getChild(1), null))
    }

    /**
    kfPlus -> <eps>
     */
    override fun visit_kfPlus_1(node: ASTNode.InnerNode<MetaGrammarInfo.kfPlus>, value: String?): String {
        return ""
    }

    /**
    g -> GRAMMAR LPAREN gComp gPlus RPAREN
     */
    override fun visit_g(node: ASTNode.InnerNode<MetaGrammarInfo.g>, value: String?): String {
        return "".also {
            visit_gComp(node.getChild(2), null)
            visit_gPlus(node.getChild(3), null)
        }
    }

    /**
    gComp -> COMPANION LPAREN gSynth gInh gCompv gStart RPAREN
     */
    override fun visit_gComp(node: ASTNode.InnerNode<MetaGrammarInfo.gComp>, value: String?): String {
        return "".also {
            visit_gSynth(node.getChild(2), null)
            visit_gInh(node.getChild(3), null)
            visit_gCompv(node.getChild(4), null)
        }
    }

    /**
    gSynth -> SYNTHESIS LPAREN attribs RPAREN
     */
    override fun visit_gSynth_0(node: ASTNode.InnerNode<MetaGrammarInfo.gSynth>, value: String?): String {
        return visit_attribs(node.getChild(2), "s")
    }

    /**
    gSynth -> <eps>
     */
    override fun visit_gSynth_1(node: ASTNode.InnerNode<MetaGrammarInfo.gSynth>, value: String?): String {
        return ""
    }

    /**
    gInh -> INHERITANCE LPAREN attribs RPAREN
     */
    override fun visit_gInh_0(node: ASTNode.InnerNode<MetaGrammarInfo.gInh>, value: String?): String {
        return visit_attribs(node.getChild(2), "i")
    }

    /**
    gInh -> <eps>
     */
    override fun visit_gInh_1(node: ASTNode.InnerNode<MetaGrammarInfo.gInh>, value: String?): String {
        return ""
    }

    /**
    gCompv -> COMPUTE LPAREN attribs RPAREN
     */
    override fun visit_gCompv_0(node: ASTNode.InnerNode<MetaGrammarInfo.gCompv>, value: String?): String {
        return visit_attribs(node.getChild(2), "c")
    }

    /**
    gCompv -> <eps>
     */
    override fun visit_gCompv_1(node: ASTNode.InnerNode<MetaGrammarInfo.gCompv>, value: String?): String {
        return ""
    }

    /**
    gPlus -> gLine gPlus
     */
    override fun visit_gPlus_0(node: ASTNode.InnerNode<MetaGrammarInfo.gPlus>, value: String?): String {
        return "".also {
            visit_gLine(node.getChild(0), null)
            visit_gPlus(node.getChild(1), null)
        }
    }

    /**
    gPlus -> <eps>
     */
    override fun visit_gPlus_1(node: ASTNode.InnerNode<MetaGrammarInfo.gPlus>, value: String?): String {
        return ""
    }

    /**
    gLine -> CAMELNAME def DESCRIBE rules EOLN
     */
    override fun visit_gLine(node: ASTNode.InnerNode<MetaGrammarInfo.gLine>, value: String?): String {
        return "".also {
            val tokenName = visit_CAMELNAME(node.getChild(0), null)
            visit_def(node.getChild(1), tokenName)
            visit_rules(node.getChild(3), tokenName)
        }
    }

    /**
    attribs -> attrib attribsPlus
     */
    override fun visit_attribs(node: ASTNode.InnerNode<MetaGrammarInfo.attribs>, value: String?): String {
        return "".also {
            visit_attrib(node.getChild(0), value)
            visit_attribsPlus(node.getChild(1), value)
        }
    }

    /**
    attrib -> CAMELNAME DESCRIBE type setDef EOLN
     */
    override fun visit_attrib(node: ASTNode.InnerNode<MetaGrammarInfo.attrib>, value: String?): String {
        val name = visit_CAMELNAME(node.getChild(0), null)
        val type = visit_type(node.getChild(2), null)
        when (value) {
            "s" -> synthesis
            "i" -> inheritance
            else -> null.also { compute.add(ComputedAttribute(name, type, visit_setDef(node.getChild(3), null))) }
        }?.add(Attribute(name, type))
        return ""
    }

    /**
    rules -> rule rulesPlus
     */
    override fun visit_rules(node: ASTNode.InnerNode<MetaGrammarInfo.rules>, value: String?): String {
        return "".also {
            visit_rule(node.getChild(0), value)
            visit_rulesPlus(node.getChild(1), value)
        }
    }

    /**
    rule -> seq def
     */
    override fun visit_rule(node: ASTNode.InnerNode<MetaGrammarInfo.rule>, value: String?): String {
        return "".also {
            val expansionString = visit_seq(node.getChild(0), value)
            visit_def(node.getChild(1), "$value: $expansionString")
        }
    }

    /**
    rulesPlus -> CHOICE rule rulesPlus
     */
    override fun visit_rulesPlus_0(node: ASTNode.InnerNode<MetaGrammarInfo.rulesPlus>, value: String?): String {
        return "".also {
            visit_rule(node.getChild(1), value)
            visit_rulesPlus(node.getChild(2), value)
        }
    }

    /**
    rulesPlus -> <eps>
     */
    override fun visit_rulesPlus_1(node: ASTNode.InnerNode<MetaGrammarInfo.rulesPlus>, value: String?): String {
        return ""
    }

    /**
    def -> DEFINE LPAREN defBody RPAREN
     */
    override fun visit_def_0(node: ASTNode.InnerNode<MetaGrammarInfo.def>, value: String?): String {
        return "".also {
            visit_defBody(node.getChild(2), null).split(", ").map {
                val res = it.split(" = ".toPattern(), 2)
                ValuedAttribute(res[0], res[1])
            }.also {
                when {
                    value!!.contains(":") -> expansionDefaults[value] = it
                    else -> stateDefaults[value] = it
                }
            }
        }
    }

    /**
    def -> <eps>
     */
    override fun visit_def_1(node: ASTNode.InnerNode<MetaGrammarInfo.def>, value: String?): String {
        return ""
    }

    /**
    pass -> LPAREN defBody RPAREN
     */
    override fun visit_pass_0(node: ASTNode.InnerNode<MetaGrammarInfo.pass>, value: String?): String {
        return "".also {
            val data = value!!.split(":")
            expansionPasses.getOrPut(data[0]) { mutableMapOf() }[data[1].length] =
                visit_defBody(node.getChild(1), null).split(", ").map {
                    val res = it.split(" = ".toPattern(), 2)
                    ValuedAttribute(res[0], res[1])
                }
        }
    }

    /**
    pass -> <eps>
     */
    override fun visit_pass_1(node: ASTNode.InnerNode<MetaGrammarInfo.pass>, value: String?): String {
        return ""
    }

    /**
    defBody -> defAtom defPlus
     */
    override fun visit_defBody(node: ASTNode.InnerNode<MetaGrammarInfo.defBody>, value: String?): String {
        return "${visit_defAtom(node.getChild(0), null)}${visit_defPlus(node.getChild(1), null)}"
    }

    /**
    defPlus -> SEP defAtom defPlus
     */
    override fun visit_defPlus_0(node: ASTNode.InnerNode<MetaGrammarInfo.defPlus>, value: String?): String {
        return ", ${visit_defAtom(node.getChild(1), null)}${visit_defPlus(node.getChild(2), null)}"
    }

    /**
    defPlus -> <eps>
     */
    override fun visit_defPlus_1(node: ASTNode.InnerNode<MetaGrammarInfo.defPlus>, value: String?): String {
        return ""
    }

    /**
    defAtom -> CAMELNAME ASSIGN defValue
     */
    override fun visit_defAtom(node: ASTNode.InnerNode<MetaGrammarInfo.defAtom>, value: String?): String {
        return "${visit_CAMELNAME(node.getChild(0), null)} = ${visit_defValue(node.getChild(2), null)}"
    }

    /**
    seq -> atom seqPlus
     */
    override fun visit_seq(node: ASTNode.InnerNode<MetaGrammarInfo.seq>, value: String?): String {
        val expansionString = "${visit_atom(node.getChild(0), null)}${visit_seqPlus(node.getChild(1), null)}"
        return expansionString.also {
            visit_atom(node.getChild(0), "$it:")
            visit_seqPlus(node.getChild(1), "$it:+")
        }
    }

    /**
    atom -> CAPSNAME
     */
    override fun visit_atom_0(node: ASTNode.InnerNode<MetaGrammarInfo.atom>, value: String?): String {
        return visit_CAPSNAME(node.getChild(0), null)
    }

    /**
    atom -> CAMELNAME pass
     */
    override fun visit_atom_1(node: ASTNode.InnerNode<MetaGrammarInfo.atom>, value: String?): String {
        return visit_CAMELNAME(node.getChild(0), null).also {
            if (value != null) visit_pass(node.getChild(1), value)
        }
    }

    /**
    seqPlus -> atom seqPlus
     */
    override fun visit_seqPlus_0(node: ASTNode.InnerNode<MetaGrammarInfo.seqPlus>, value: String?): String {
        return " ${visit_atom(node.getChild(0), value)}${visit_seqPlus(
            node.getChild(1),
            if (value != null) "$value+" else null
        )}"
    }

    /**
    seqPlus -> <eps>
     */
    override fun visit_seqPlus_1(node: ASTNode.InnerNode<MetaGrammarInfo.seqPlus>, value: String?): String {
        return ""
    }

    /**
    attribsPlus -> attrib attribsPlus
     */
    override fun visit_attribsPlus_0(node: ASTNode.InnerNode<MetaGrammarInfo.attribsPlus>, value: String?): String {
        return "".also {
            visit_attrib(node.getChild(0), value)
            visit_attribsPlus(node.getChild(1), value)
        }
    }

    /**
    attribsPlus -> <eps>
     */
    override fun visit_attribsPlus_1(node: ASTNode.InnerNode<MetaGrammarInfo.attribsPlus>, value: String?): String {
        return ""
    }

    /**
    type -> INT_TYPE
     */
    override fun visit_type_0(node: ASTNode.InnerNode<MetaGrammarInfo.type>, value: String?): String {
        return "Int"
    }

    /**
    type -> DOUBLE_TYPE
     */
    override fun visit_type_1(node: ASTNode.InnerNode<MetaGrammarInfo.type>, value: String?): String {
        return "Double"
    }

    /**
    type -> STRING_TYPE
     */
    override fun visit_type_2(node: ASTNode.InnerNode<MetaGrammarInfo.type>, value: String?): String {
        return "String"
    }

    /**
    setDef -> DEFINE LPAREN DEFAULT ASSIGN defValue RPAREN
     */
    override fun visit_setDef_0(node: ASTNode.InnerNode<MetaGrammarInfo.setDef>, value: String?): String {
        return visit_defValue(node.getChild(4), null)
    }

    /**
    setDef -> <eps>
     */
    override fun visit_setDef_1(node: ASTNode.InnerNode<MetaGrammarInfo.setDef>, value: String?): String {
        return ""
    }

    /**
    defValue -> STRING
     */
    override fun visit_defValue_0(node: ASTNode.InnerNode<MetaGrammarInfo.defValue>, value: String?): String {
        return visit_STRING(node.getChild(0), null)
    }

    /**
    defValue -> defTerm defMod
     */
    override fun visit_defValue_1(node: ASTNode.InnerNode<MetaGrammarInfo.defValue>, value: String?): String {
        return visitList(" ", visit_defTerm(node.getChild(0), null), visit_defMod(node.getChild(1), null))
    }

    /**
    defValue -> SUB defTerm
     */
    override fun visit_defValue_2(node: ASTNode.InnerNode<MetaGrammarInfo.defValue>, value: String?): String {
        return "-${visit_defTerm(node.getChild(1), null)}"
    }

    /**
    defTerm -> atName
     */
    override fun visit_defTerm_0(node: ASTNode.InnerNode<MetaGrammarInfo.defTerm>, value: String?): String {
        return visit_atName(node.getChild(0), null)
    }

    /**
    defTerm -> INT
     */
    override fun visit_defTerm_1(node: ASTNode.InnerNode<MetaGrammarInfo.defTerm>, value: String?): String {
        return visit_INT(node.getChild(0), null)
    }

    /**
    defTerm -> DOUBLE
     */
    override fun visit_defTerm_2(node: ASTNode.InnerNode<MetaGrammarInfo.defTerm>, value: String?): String {
        return visit_DOUBLE(node.getChild(0), null)
    }

    /**
    defMod -> op defTerm
     */
    override fun visit_defMod_0(node: ASTNode.InnerNode<MetaGrammarInfo.defMod>, value: String?): String {
        return "${visit_op(node.getChild(0), null)} ${visit_defTerm(node.getChild(1), null)}"
    }

    /**
    defMod -> <eps>
     */
    override fun visit_defMod_1(node: ASTNode.InnerNode<MetaGrammarInfo.defMod>, value: String?): String {
        return ""
    }

    /**
    op -> ADD
     */
    override fun visit_op_0(node: ASTNode.InnerNode<MetaGrammarInfo.op>, value: String?): String {
        return "+"
    }

    /**
    op -> SUB
     */
    override fun visit_op_1(node: ASTNode.InnerNode<MetaGrammarInfo.op>, value: String?): String {
        return "-"
    }

    /**
    op -> MUL
     */
    override fun visit_op_2(node: ASTNode.InnerNode<MetaGrammarInfo.op>, value: String?): String {
        return "*"
    }

    /**
    op -> DIV
     */
    override fun visit_op_3(node: ASTNode.InnerNode<MetaGrammarInfo.op>, value: String?): String {
        return "/"
    }

    /**
    atName -> SPNAME
     */
    override fun visit_atName_0(node: ASTNode.InnerNode<MetaGrammarInfo.atName>, value: String?): String {
        var spname = visit_SPNAME(node.getChild(0), null)
        spname = spname.replace("@macro", "Macro")
        spname = spname.replace("@(\\d+)".toRegex(), "children[$1]")
        spname = spname.replace("@", "value")
        return spname
    }

    /**
    atName -> CAMELNAME
     */
    override fun visit_atName_1(node: ASTNode.InnerNode<MetaGrammarInfo.atName>, value: String?): String {
        return visit_CAMELNAME(node.getChild(0), null)
    }

    override fun getDefinedTokens(): List<Token> {
        return grammarInfoObject.getDefinedTokens()
    }

    override fun getFullName(token: Token): String {
        return "${grammarInfoObject.getName()}.$token"
    }

    override fun getVisitTerminal(): String {
        return """
    fun <T : ${className(Token::class)}> visitTerminal(token: T): $dataObjectName {
        return $dataObjectName()
    }"""
    }

    override fun getVisitMethods(token: Token): String {
        return when (token) {
            is Token.StateToken -> getStateVisitMethods(token)
            else -> getTerminalVisitMethods(token)
        }
    }

    private fun pack(token: Token): String {
        return token.toString()
    }

    private fun pack(token: Token, expansion: Expansion): String {
        return "$token: $expansion"
    }

    private fun getExpansionPass(token: Token, expansion: Expansion, index: Int): String {
        return (expansionPasses[pack(token, expansion)]?.get(index)?.joinToString(", ") ?: "")
    }

    private fun getExpansionDefaultsNames(token: Token, expansion: Expansion): List<String> {
        return expansionDefaults[pack(token, expansion)]?.map { it.name }?.toList() ?: emptyList()
    }

    private fun getStateVisitBody(token: Token.StateToken, expansion: Expansion): String {
        val expansionDefaultsNames = getExpansionDefaultsNames(token, expansion)
        return """
        val children = mutableListOf<${dataObjectName}>()
${expansion.filter { it !is Token.UniqueToken }.mapIndexed { i, child ->
            "\t\tchildren.add(visit_${child}(node.getChild($i), ${dataObjectName}(${getExpansionPass(
                token, expansion, i
            )})))"
        }.joinToString("\n")}
${visitList("\n",
            stateDefaults[pack(token)]?.filter { it.name !in expansionDefaultsNames }
                ?.joinToString("\n") { "\t\tvalue.$it" } ?: "",
            expansionDefaults[pack(token, expansion)]?.joinToString { "\t\tvalue.$it" } ?: ""
        )}
        return value"""
    }

    private fun getStateVisitMethods(token: Token.StateToken): String {
        val expansions = grammarInfoObject.getGrammar().RULES[token].expansions
        return if (expansions.size == 1) {
            """
    /**
    $token -> ${expansions.first()}
    */
    fun visit_${token}(node: ${getNodeType(token)}, value: $dataObjectName): $dataObjectName {${getStateVisitBody(
                token,
                expansions.first()
            )}
    }"""
        } else {
            """
    fun visit_${token}(node: ${getNodeType(token)}, value: $dataObjectName): $dataObjectName {
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
    fun visit_${token}_${it.getId()}(node: ${getNodeType(token)}, value: $dataObjectName): $dataObjectName {
${getStateVisitBody(token, it)}
    }"""
            }}"""
        }
    }

    private fun getTerminalVisitMethods(token: Token): String {
        return """
    fun visit_${token}(node: ${getNodeType(token)}, value: $dataObjectName): $dataObjectName {
        return visitTerminal(node.getToken())
    }"""
    }

    override fun getChoiceVisit(): String {
        return """
    override fun visit(node: ${className(ASTNode::class)}<out ${className(Token::class)}>, value: $dataObjectName): $dataObjectName {
        return when(node.getToken()) {
${getDefinedTokens().joinToString("\n") { "\t\t\t${getFullName(it)} -> visit_${it}(node as ${getNodeType(it)}, value)" }}
            else -> throw IllegalStateException("Unknown token ${"$"}{node.getToken()} met")
        }
    }"""
    }

    fun getMain(): String {
        return """/**
This code is generated by [${WalkerBuilder::class.qualifiedName}] deriving from base class [${MetaVisitorBase::class.qualifiedName}] 
generated by [${MetaVisitorBuilder::class.qualifiedName}]
basing on grammar description [${grammarInfoObject::class.qualifiedName}] derived from [${GrammarInfo::class.qualifiedName}]
*/

package $packageName

import java.io.File
import ${Parser::class.qualifiedName}
import ${grammarInfoObject::class.qualifiedName}

class $dataObjectName(${inheritance.joinToString(", ") { "val $it = null" }}) {

    companion object Macro {
${Beautifier.plusIndent(macro)}
    }
${visitList("\n",
            visitList(
                "",
                synthesis.joinToString("") { "\n\tvar $it = null" },
                compute.joinToString("") { "\n\t$it" }
            ), """
    override fun toString(): String {
        return "${(inheritance + synthesis).joinToString("\\n") { "${it.name}: ${"$"}{${it.name}}" }}"
    }"""
        )}
    
}

fun main() {
    val s = "" // put your input here
    println(${objectName}.collect(Parser(${grammarInfoObject.getName()}).parse(s)))
}
"""
    }

    override fun getAll(): String {
        return Beautifier.detabify(
            """/**
This code is generated by [${VisitorBuilder::class.qualifiedName}] derived from [${MetaVisitorBase::class.qualifiedName}] 
generated by [${MetaVisitorBuilder::class.qualifiedName}]
basing on grammar description [${grammarInfoObject::class.qualifiedName}] derived from [${GrammarInfo::class.qualifiedName}]
*/

package $packageName

import ${Token::class.qualifiedName}
import ${Grammar::class.qualifiedName}
import ${Expansion::class.qualifiedName}
import ${Beautifier::class.qualifiedName}

import ${ASTNode::class.qualifiedName}
import ${Visitor::class.qualifiedName}

import ${grammarInfoObject::class.qualifiedName}

@Suppress("UNCHECKED_CAST")
object $objectName : ${Visitor::class.simpleName}<$dataObjectName, $dataObjectName> {

/*
${grammarInfoObject.getGrammar()}
*/

    override fun <T : Token> collect(root: ASTNode<T>): $dataObjectName {
        return visit(root, $dataObjectName())
    }

${getVisitTerminal()}
${getChoiceVisit()}
${getVisitMethods()}

}"""
        )
    }

}