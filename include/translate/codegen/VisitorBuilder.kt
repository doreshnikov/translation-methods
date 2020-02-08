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
import utils.Beautifier

class VisitorBuilder(
    private val packageName: String,
    private val grammarInfoObject: GrammarInfo,
    private val metaRoot: ASTNode<*>
) : MetaVisitorBase<String, VisitorBuilder.VisitorLocation>, BuilderHelper {

    sealed class VisitorLocation {

        object Nowhere : VisitorLocation()

        sealed class InCompanion : VisitorLocation() {
            object InSynthesis : InCompanion()
            object InInheritance : InCompanion()
            object InCompute : InCompanion()
        }

        class InState(val token: Token.StateToken) : VisitorLocation()

        class InExpansionById(val token: Token.StateToken, val id: Int) : VisitorLocation() {
            constructor(parent: InState, id: Int) : this(parent.token, id)

            fun next(): InExpansionById {
                return InExpansionById(token, id + 1)
            }

            fun get(grammarInfo: GrammarInfo): InExpansion {
                return InExpansion(token, grammarInfo.getGrammar().RULES[token].expansions[id])
            }
        }

        class InExpansion(val token: Token.StateToken, val expansion: Expansion) : VisitorLocation() {
            constructor(parent: InState, expansion: Expansion) : this(parent.token, expansion)

            fun getKey(): Pair<Token, Expansion> {
                return token to expansion
            }
        }

        class InExpansionElement(val token: Token.StateToken, val expansion: Expansion, val index: Int) :
            VisitorLocation() {
            constructor(parent: InExpansion, index: Int) : this(parent.token, parent.expansion, index)

            fun next(): InExpansionElement {
                return InExpansionElement(token, expansion, index + 1)
            }

            fun getKey(): Pair<Token.StateToken, Expansion> {
                return token to expansion
            }
        }

    }

    fun prepare(): VisitorBuilder {
        Token.switchTo(grammarInfoObject.getName())
        collect(metaRoot)
        Token.switchTo(MetaGrammarInfo.getName())
        return this
    }

    private val objectName: String = "${grammarInfoObject.getSimpleName()}Visitor"
    private val dataObjectName: String = "${grammarInfoObject.getSimpleName()}Data"

    data class Attribute(val name: String, val type: String) {
        override fun toString(): String {
            return "$name: $type"
        }
    }

    data class ComputedAttribute(val name: String, val type: String, val value: String) {
        override fun toString(): String {
            return "val $name: $type\n\t\tget() = $value"
        }
    }

    private fun getTypeInit(type: String): String {
        return when (type) {
            "Int" -> "0"
            "Double" -> "0.0"
            "String" -> "\"\""
            else -> "$type()"
        }
    }

    private val synthesis = mutableListOf<Attribute>()
    private val synthesisDefaults = mutableMapOf<String, String>()
    private val inheritance = mutableListOf<Attribute>()
    private val inheritanceDefaults = mutableMapOf<String, String>()
    private val compute = mutableListOf<ComputedAttribute>()

    private val stateSynthesisDefaults = mutableMapOf<Token.StateToken, MutableMap<String, String>>()
    private val expansionSynthesis = mutableMapOf<Pair<Token, Expansion>, MutableMap<String, String>>()
    private val expansionInheritance = mutableMapOf<Pair<Token, Expansion>, MutableList<MutableMap<String, String>>>()

    private var macro = ""

    override fun <T : Token> visitTerminal(token: T): String {
        return token.getText()
    }

    private fun visitList(sep: String, vararg elements: String): String {
        return elements.filter { it.isNotBlank() }.joinToString(sep)
    }

    override fun <T : Token> collect(root: ASTNode<T>): String {
        return visit(root, VisitorLocation.Nowhere)
    }

    override fun visit_STRING(
        node: ASTNode.TerminalNode<Token.VariantToken.VariantInstanceToken<MetaGrammarInfo.STRING>>,
        value: VisitorLocation
    ): String {
        return fixAtNames(super.visit_STRING(node, value))
    }

    /**
    all -> m t g
     */
    override fun visit_all(node: ASTNode.InnerNode<MetaGrammarInfo.all>, value: VisitorLocation): String {
        macro = visit_m(node.getChild(0), value)
        visit_g(node.getChild(2), value)
        return ""
    }

    /**
    m -> MACRO LPAREN kfPlus RPAREN
     */
    override fun visit_m_0(node: ASTNode.InnerNode<MetaGrammarInfo.m>, value: VisitorLocation): String {
        return visit_kfPlus(node.getChild(2), value)
    }

    /**
    m -> <eps>
     */
    override fun visit_m_1(node: ASTNode.InnerNode<MetaGrammarInfo.m>, value: VisitorLocation): String {
        return ""
    }

    /**
    kfPlus -> KOTLIN_FUNC kfPlus
     */
    override fun visit_kfPlus_0(node: ASTNode.InnerNode<MetaGrammarInfo.kfPlus>, value: VisitorLocation): String {
        return visitList(
            "\n",
            visit_KOTLIN_FUNC(node.getChild(0), value),
            visit_kfPlus(node.getChild(1), value)
        )
    }

    /**
    kfPlus -> <eps>
     */
    override fun visit_kfPlus_1(node: ASTNode.InnerNode<MetaGrammarInfo.kfPlus>, value: VisitorLocation): String {
        return ""
    }

    /**
    g -> GRAMMAR LPAREN gComp gPlus RPAREN
     */
    override fun visit_g(node: ASTNode.InnerNode<MetaGrammarInfo.g>, value: VisitorLocation): String {
        return "".also {
            visit_gComp(node.getChild(2), value)
            visit_gPlus(node.getChild(3), value)
        }
    }

    /**
    gComp -> COMPANION LPAREN gSynth gInh gCompv gStart RPAREN
     */
    override fun visit_gComp(node: ASTNode.InnerNode<MetaGrammarInfo.gComp>, value: VisitorLocation): String {
        return "".also {
            visit_gSynth(node.getChild(2), value)
            visit_gInh(node.getChild(3), value)
            visit_gCompv(node.getChild(4), value)
        }
    }

    /**
    gSynth -> SYNTHESIS LPAREN attribs RPAREN
     */
    override fun visit_gSynth_0(node: ASTNode.InnerNode<MetaGrammarInfo.gSynth>, value: VisitorLocation): String {
        return visit_attribs(node.getChild(2), VisitorLocation.InCompanion.InSynthesis)
    }

    /**
    gSynth -> <eps>
     */
    override fun visit_gSynth_1(node: ASTNode.InnerNode<MetaGrammarInfo.gSynth>, value: VisitorLocation): String {
        return ""
    }

    /**
    gInh -> INHERITANCE LPAREN attribs RPAREN
     */
    override fun visit_gInh_0(node: ASTNode.InnerNode<MetaGrammarInfo.gInh>, value: VisitorLocation): String {
        return visit_attribs(node.getChild(2), VisitorLocation.InCompanion.InInheritance)
    }

    /**
    gInh -> <eps>
     */
    override fun visit_gInh_1(node: ASTNode.InnerNode<MetaGrammarInfo.gInh>, value: VisitorLocation): String {
        return ""
    }

    /**
    gCompv -> COMPUTE LPAREN attribs RPAREN
     */
    override fun visit_gCompv_0(node: ASTNode.InnerNode<MetaGrammarInfo.gCompv>, value: VisitorLocation): String {
        return visit_attribs(node.getChild(2), VisitorLocation.InCompanion.InCompute)
    }

    /**
    gCompv -> <eps>
     */
    override fun visit_gCompv_1(node: ASTNode.InnerNode<MetaGrammarInfo.gCompv>, value: VisitorLocation): String {
        return ""
    }

    /**
    gPlus -> gLine gPlus
     */
    override fun visit_gPlus_0(node: ASTNode.InnerNode<MetaGrammarInfo.gPlus>, value: VisitorLocation): String {
        return "".also {
            visit_gLine(node.getChild(0), value)
            visit_gPlus(node.getChild(1), value)
        }
    }

    /**
    gPlus -> <eps>
     */
    override fun visit_gPlus_1(node: ASTNode.InnerNode<MetaGrammarInfo.gPlus>, value: VisitorLocation): String {
        return ""
    }

    /**
    gLine -> CAMELNAME def DESCRIBE rules EOLN
     */
    override fun visit_gLine(node: ASTNode.InnerNode<MetaGrammarInfo.gLine>, value: VisitorLocation): String {
        return "".also {
            val loc = VisitorLocation.InState(
                Token.REGISTERED[visit_CAMELNAME(node.getChild(0), value)] as Token.StateToken
            )
            visit_def(node.getChild(1), loc)
            visit_rules(node.getChild(3), loc)
        }
    }

    /**
    attribs -> attrib attribsPlus
     */
    override fun visit_attribs(node: ASTNode.InnerNode<MetaGrammarInfo.attribs>, value: VisitorLocation): String {
        return "".also {
            visit_attrib(node.getChild(0), value)
            visit_attribsPlus(node.getChild(1), value)
        }
    }

    /**
    attrib -> CAMELNAME DESCRIBE type setDef EOLN
     */
    override fun visit_attrib(node: ASTNode.InnerNode<MetaGrammarInfo.attrib>, value: VisitorLocation): String {
        val name = visit_CAMELNAME(node.getChild(0), value)
        val type = visit_type(node.getChild(2), value)
        val def = visit_setDef(node.getChild(3), value)
        when (value) {
            VisitorLocation.InCompanion.InSynthesis -> synthesis.also {
                if (def.isNotBlank()) synthesisDefaults[name] = def
            }
            VisitorLocation.InCompanion.InInheritance -> inheritance.also {
                if (def.isNotBlank()) inheritanceDefaults[name] = def
            }
            else -> null.also {
                compute.add(ComputedAttribute(name, type, def))
            }
        }?.add(Attribute(name, type))
        return ""
    }

    /**
    rules -> rule rulesPlus
     */
    override fun visit_rules(node: ASTNode.InnerNode<MetaGrammarInfo.rules>, value: VisitorLocation): String {
        return "".also {
            visit_rule(node.getChild(0), VisitorLocation.InExpansionById(value as VisitorLocation.InState, 0))
            visit_rulesPlus(node.getChild(1), VisitorLocation.InExpansionById(value, 1))
        }
    }

    /**
    rule -> seq def
     */
    override fun visit_rule(node: ASTNode.InnerNode<MetaGrammarInfo.rule>, value: VisitorLocation): String {
        val loc = (value as VisitorLocation.InExpansionById).get(grammarInfoObject)
        visit_seq(node.getChild(0), loc)
        visit_def(node.getChild(1), loc)
        return ""
    }

    /**
    rulesPlus -> CHOICE rule rulesPlus
     */
    override fun visit_rulesPlus_0(node: ASTNode.InnerNode<MetaGrammarInfo.rulesPlus>, value: VisitorLocation): String {
        return "".also {
            visit_rule(node.getChild(1), value)
            visit_rulesPlus(node.getChild(2), (value as VisitorLocation.InExpansionById).next())
        }
    }

    /**
    rulesPlus -> <eps>
     */
    override fun visit_rulesPlus_1(node: ASTNode.InnerNode<MetaGrammarInfo.rulesPlus>, value: VisitorLocation): String {
        return ""
    }

    /**
    def -> DEFINE LPAREN defBody RPAREN
     */
    override fun visit_def_0(node: ASTNode.InnerNode<MetaGrammarInfo.def>, value: VisitorLocation): String {
        return visit_defBody(node.getChild(2), value)
    }

    /**
    def -> <eps>
     */
    override fun visit_def_1(node: ASTNode.InnerNode<MetaGrammarInfo.def>, value: VisitorLocation): String {
        return ""
    }

    /**
    pass -> LPAREN defBody RPAREN
     */
    override fun visit_pass_0(node: ASTNode.InnerNode<MetaGrammarInfo.pass>, value: VisitorLocation): String {
        return visit_defBody(node.getChild(1), value)
    }

    /**
    pass -> <eps>
     */
    override fun visit_pass_1(node: ASTNode.InnerNode<MetaGrammarInfo.pass>, value: VisitorLocation): String {
        return ""
    }

    /**
    defBody -> defAtom defPlus
     */
    override fun visit_defBody(node: ASTNode.InnerNode<MetaGrammarInfo.defBody>, value: VisitorLocation): String {
        visit_defAtom(node.getChild(0), value)
        visit_defPlus(node.getChild(1), value)
        return ""
    }

    /**
    defPlus -> SEP defAtom defPlus
     */
    override fun visit_defPlus_0(node: ASTNode.InnerNode<MetaGrammarInfo.defPlus>, value: VisitorLocation): String {
        visit_defAtom(node.getChild(0), value)
        visit_defPlus(node.getChild(1), value)
        return ""
    }

    /**
    defPlus -> <eps>
     */
    override fun visit_defPlus_1(node: ASTNode.InnerNode<MetaGrammarInfo.defPlus>, value: VisitorLocation): String {
        return ""
    }

    /**
    defAtom -> CAMELNAME ASSIGN defValue
     */
    override fun visit_defAtom(node: ASTNode.InnerNode<MetaGrammarInfo.defAtom>, value: VisitorLocation): String {
        val name = visit_CAMELNAME(node.getChild(0), value)
        val def = visit_defValue(node.getChild(2), value)
        when (value) {
            is VisitorLocation.InState -> stateSynthesisDefaults.getOrPut(value.token) { mutableMapOf() }[name] = def
            is VisitorLocation.InExpansion -> expansionSynthesis.getOrPut(value.getKey()) { mutableMapOf() }[name] = def
            is VisitorLocation.InExpansionElement -> expansionInheritance.getOrPut(value.getKey()) {
                mutableListOf()
            }.also {
                while (it.size <= value.index) {
                    it.add(mutableMapOf())
                }
                it[value.index][name] = def
            }
        }
        return ""
    }

    /**
    seq -> atom seqPlus
     */
    override fun visit_seq(node: ASTNode.InnerNode<MetaGrammarInfo.seq>, value: VisitorLocation): String {
        val loc = VisitorLocation.InExpansionElement(value as VisitorLocation.InExpansion, 0)
        visit_atom(node.getChild(0), loc)
        visit_seqPlus(node.getChild(1), loc.next())
        return ""
    }

    /**
    atom -> CAPSNAME
     */
    override fun visit_atom_0(node: ASTNode.InnerNode<MetaGrammarInfo.atom>, value: VisitorLocation): String {
        return ""
    }

    /**
    atom -> CAMELNAME pass
     */
    override fun visit_atom_1(node: ASTNode.InnerNode<MetaGrammarInfo.atom>, value: VisitorLocation): String {
        return visit_pass(node.getChild(1), value)
    }

    /**
    seqPlus -> atom seqPlus
     */
    override fun visit_seqPlus_0(node: ASTNode.InnerNode<MetaGrammarInfo.seqPlus>, value: VisitorLocation): String {
        visit_atom(node.getChild(0), value)
        visit_seqPlus(node.getChild(1), (value as VisitorLocation.InExpansionElement).next())
        return ""
    }

    /**
    seqPlus -> <eps>
     */
    override fun visit_seqPlus_1(node: ASTNode.InnerNode<MetaGrammarInfo.seqPlus>, value: VisitorLocation): String {
        return ""
    }

    /**
    attribsPlus -> attrib attribsPlus
     */
    override fun visit_attribsPlus_0(
        node: ASTNode.InnerNode<MetaGrammarInfo.attribsPlus>,
        value: VisitorLocation
    ): String {
        visit_attrib(node.getChild(0), value)
        visit_attribsPlus(node.getChild(1), value)
        return ""
    }

    /**
    attribsPlus -> <eps>
     */
    override fun visit_attribsPlus_1(
        node: ASTNode.InnerNode<MetaGrammarInfo.attribsPlus>,
        value: VisitorLocation
    ): String {
        return ""
    }

    /**
    type -> INT_TYPE
     */
    override fun visit_type_0(node: ASTNode.InnerNode<MetaGrammarInfo.type>, value: VisitorLocation): String {
        return "Int"
    }

    /**
    type -> DOUBLE_TYPE
     */
    override fun visit_type_1(node: ASTNode.InnerNode<MetaGrammarInfo.type>, value: VisitorLocation): String {
        return "Double"
    }

    /**
    type -> STRING_TYPE
     */
    override fun visit_type_2(node: ASTNode.InnerNode<MetaGrammarInfo.type>, value: VisitorLocation): String {
        return "String"
    }

    /**
    setDef -> DEFINE LPAREN DEFAULT ASSIGN defValue RPAREN
     */
    override fun visit_setDef_0(node: ASTNode.InnerNode<MetaGrammarInfo.setDef>, value: VisitorLocation): String {
        return visit_defValue(node.getChild(4), value)
    }

    /**
    setDef -> <eps>
     */
    override fun visit_setDef_1(node: ASTNode.InnerNode<MetaGrammarInfo.setDef>, value: VisitorLocation): String {
        return ""
    }

    /**
    defValue -> STRING
     */
    override fun visit_defValue_0(node: ASTNode.InnerNode<MetaGrammarInfo.defValue>, value: VisitorLocation): String {
        return visit_STRING(node.getChild(0), value)
    }

    /**
    defValue -> defTerm defMod
     */
    override fun visit_defValue_1(node: ASTNode.InnerNode<MetaGrammarInfo.defValue>, value: VisitorLocation): String {
        return visitList(
            " ",
            visit_defTerm(node.getChild(0), value),
            visit_defMod(node.getChild(1), value)
        )
    }

    /**
    defValue -> SUB defTerm
     */
    override fun visit_defValue_2(node: ASTNode.InnerNode<MetaGrammarInfo.defValue>, value: VisitorLocation): String {
        return "-${visit_defTerm(node.getChild(1), value)}"
    }

    /**
    defTerm -> atName
     */
    override fun visit_defTerm_0(node: ASTNode.InnerNode<MetaGrammarInfo.defTerm>, value: VisitorLocation): String {
        return fixAtNames(visit_atName(node.getChild(0), value))
    }

    /**
    defTerm -> INT
     */
    override fun visit_defTerm_1(node: ASTNode.InnerNode<MetaGrammarInfo.defTerm>, value: VisitorLocation): String {
        return visit_INT(node.getChild(0), value)
    }

    /**
    defTerm -> DOUBLE
     */
    override fun visit_defTerm_2(node: ASTNode.InnerNode<MetaGrammarInfo.defTerm>, value: VisitorLocation): String {
        return visit_DOUBLE(node.getChild(0), value)
    }

    /**
    defMod -> op defTerm
     */
    override fun visit_defMod_0(node: ASTNode.InnerNode<MetaGrammarInfo.defMod>, value: VisitorLocation): String {
        return "${visit_op(node.getChild(0), value)} ${visit_defTerm(node.getChild(1), value)}"
    }

    /**
    defMod -> <eps>
     */
    override fun visit_defMod_1(node: ASTNode.InnerNode<MetaGrammarInfo.defMod>, value: VisitorLocation): String {
        return ""
    }

    /**
    op -> ADD
     */
    override fun visit_op_0(node: ASTNode.InnerNode<MetaGrammarInfo.op>, value: VisitorLocation): String {
        return "+"
    }

    /**
    op -> SUB
     */
    override fun visit_op_1(node: ASTNode.InnerNode<MetaGrammarInfo.op>, value: VisitorLocation): String {
        return "-"
    }

    /**
    op -> MUL
     */
    override fun visit_op_2(node: ASTNode.InnerNode<MetaGrammarInfo.op>, value: VisitorLocation): String {
        return "*"
    }

    /**
    op -> DIV
     */
    override fun visit_op_3(node: ASTNode.InnerNode<MetaGrammarInfo.op>, value: VisitorLocation): String {
        return "/"
    }

    private fun fixAtNames(value: String): String {
        var string = value
        string = string.replace("@macro", "${dataObjectName}.Macro")
        string = string.replace("@(\\d+)".toRegex(), "children[$1]")
        string = string.replace("@", "value")
        return string
    }

    /**
    atName -> SPNAME
     */
    override fun visit_atName_0(node: ASTNode.InnerNode<MetaGrammarInfo.atName>, value: VisitorLocation): String {
        return visit_SPNAME(node.getChild(0), value)
    }

    /**
    atName -> CAMELNAME
     */
    override fun visit_atName_1(node: ASTNode.InnerNode<MetaGrammarInfo.atName>, value: VisitorLocation): String {
        return visit_CAMELNAME(node.getChild(0), value)
    }

    /**
    atName -> MACROREF CAMELNAME LBRACKET macroBody RBRACKET
     */
    override fun visit_atName_2(node: ASTNode.InnerNode<MetaGrammarInfo.atName>, value: VisitorLocation): String {
        return "${visit_MACROREF(node.getChild(0), value)}${visit_CAMELNAME(node.getChild(1), value)}" +
                "(${visit_macroBody(node.getChild(3), value)})"
    }

    /**
    macroBody -> defValue macroPlus
     */
    override fun visit_macroBody(node: ASTNode.InnerNode<MetaGrammarInfo.macroBody>, value: VisitorLocation): String {
        return "${visit_defValue(node.getChild(0), value)}${visit_macroPlus(node.getChild(1), value)}"
    }

    /**
    macroPlus -> SEP defValue macroPlus
     */
    override fun visit_macroPlus_0(node: ASTNode.InnerNode<MetaGrammarInfo.macroPlus>, value: VisitorLocation): String {
        return ", ${visit_defValue(node.getChild(1), value)}${visit_macroPlus(node.getChild(2), value)}"
    }

    /**
    macroPlus -> <eps>
     */
    override fun visit_macroPlus_1(node: ASTNode.InnerNode<MetaGrammarInfo.macroPlus>, value: VisitorLocation): String {
        return ""
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

    private fun getInheritance(token: Token, expansion: Expansion, index: Int): String {
        return mutableMapOf<String, String>().also { map ->
            map.putAll(inheritanceDefaults)
            map.putAll(expansionInheritance[token to expansion]?.getOrNull(index) ?: emptyMap())
        }.entries.joinToString(", ") { "${it.key} = ${it.value}" }
    }

    private fun getSynthesis(token: Token, expansion: Expansion): String {
        return mutableMapOf<String, String>().also { map ->
            map.putAll(synthesisDefaults)
            map.putAll(stateSynthesisDefaults[token] ?: emptyMap())
            map.putAll(expansionSynthesis[token to expansion] ?: emptyMap())
        }.entries.joinToString(", ") { "\t\tvalue.${it.key} = ${it.value}" }
    }

    private fun getStateVisitBody(token: Token.StateToken, expansion: Expansion): String {
        return """
        val children = mutableListOf<${dataObjectName}>()
${expansion.filter { it !is Token.UniqueToken }.mapIndexed { i, child ->
            "\t\tchildren.add(visit_${child}(node.getChild($i), ${dataObjectName}(${getInheritance(
                token, expansion, i
            )})))"
        }.joinToString("\n")}
${getSynthesis(token, expansion)}
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
                token, expansions.first()
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
    fun visit_${token}_${it.getId()}(node: ${getNodeType(token)}, value: $dataObjectName): $dataObjectName {${getStateVisitBody(
                    token, it
                )}
    }"""
            }}"""
        }
    }

    private fun getTerminalVisitMethods(token: Token): String {
        return """
    fun visit_${token}(node: ${getNodeType(token)}, value: $dataObjectName): $dataObjectName {
        return visitTerminal(node.getToken()).also { it.text = node.getToken().getText() }
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

    fun getDataClass(): String {
        return """/**
This code is generated by [${WalkerBaseBuilder::class.qualifiedName}] deriving from base class [${MetaVisitorBase::class.qualifiedName}] 
generated by [${VisitorBaseBuilder::class.qualifiedName}]
basing on grammar description [${grammarInfoObject::class.qualifiedName}] derived from [${GrammarInfo::class.qualifiedName}]
*/

package $packageName

import java.io.File
import ${Parser::class.qualifiedName}
import ${grammarInfoObject::class.qualifiedName}

open class $dataObjectName(${inheritance.joinToString(", ") { "val $it = ${getTypeInit(it.type)}" }}) {

    companion object Macro {
${Beautifier.plusIndent(macro)}
    }
    
    var text: String = ""
${visitList("\n",
            visitList(
                "",
                synthesis.joinToString("") { "\n\tvar $it = ${getTypeInit(it.type)}" },
                compute.joinToString("") { "\n\t$it" }
            ), """
    override fun toString(): String {
        return "${(inheritance + synthesis).joinToString("\\n") { "${it.name}:\\n${"$"}{${it.name}}" }}"
    }"""
        )}
    
}
"""
    }

    override fun getAll(): String {
        return Beautifier.detabify(
            """/**
This code is generated by [${VisitorBuilder::class.qualifiedName}] derived from [${MetaVisitorBase::class.qualifiedName}] 
generated by [${VisitorBaseBuilder::class.qualifiedName}]
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