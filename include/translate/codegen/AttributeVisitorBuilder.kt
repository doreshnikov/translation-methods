package translate.codegen

import grammar.token.Token
import structure.ASTNode
import structure.Description
import translate.meta.MetaBaseVisitor
import translate.meta.MetaDescription
import utils.Beautifier

class AttributeVisitorBuilder(
    private val packageName: String,
    private val className: String
) : MetaBaseVisitor<String>() {

    data class Attribute(val name: String, val type: String) {
        override fun toString(): String {
            return "$name: $type"
        }
    }

    data class ComputedAttribute(val name: String, val type: String, val init: String) {
        override fun toString(): String {
            return "fun $name(): $type = $init"
        }
    }

    val synthesis = mutableListOf<Attribute>()
    val inheritance = mutableListOf<Attribute>()
    val compute = mutableListOf<Attribute>()

    override fun <T : Token> visitTerminal(token: T): String {
        return token.getText()
    }

    override fun <T : Token> collect(root: ASTNode<T>): String {
        val collected = visit(root)
        return Beautifier.detabify(
            """/**
This code is generated by [translate.codegen.DescriptionBuilder]
deriving from base class [translate.meta.MetaBaseVisitor] generated by [translate.codegen.AbstractVisitorBuilder]
*/

package $packageName

import grammar.token.Token
import grammar.Grammar
import grammar.Expansion

import structure.ASTNode
import structure.Visitor

class ${className}Visitor : Visitor {

    class ${className}Data(${inheritance.joinToString(", ") { "val $it" }}) {
${synthesis.joinToString("\n") { "\t\tlateinit val $it" }}
${compute.joinToString("\n") { "\t\t$it" }}
    }
    
    class ${className}TerminalNode<T : Token>(token: T, ${inheritance.joinToString(", ")}): ASTNode.TerminalNode<T>(token) {
        val data = ${className}Data(${inheritance.joinToString(", ") { it.name }})
    }
    
    class ${className}InnerNode<T : Token>(token: T, expansion: Expansion, ${inheritance.joinToString(", ")}): ASTNode.InnerNode<T>(token) {
        val data = ${className}Data(${inheritance.joinToString(", ") { it.name }})    
    }
    
$collected
}
"""
        )

    }

    /**
    all -> m t g
     */
    override fun visit_all(node: ASTNode.InnerNode<MetaDescription.all>): String {
        return ""
    }

    /**
    m -> MACRO LPAREN kfPlus RPAREN
     */
    override fun visit_m_0(node: ASTNode.InnerNode<MetaDescription.m>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    m -> <eps>
     */
    override fun visit_m_1(node: ASTNode.InnerNode<MetaDescription.m>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    t -> TOKENS LPAREN tComp tFrag tPlus RPAREN
     */
    override fun visit_t(node: ASTNode.InnerNode<MetaDescription.t>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    g -> GRAMMAR LPAREN gComp gPlus RPAREN
     */
    override fun visit_g(node: ASTNode.InnerNode<MetaDescription.g>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    kfPlus -> KOTLIN_FUNC kfPlus
     */
    override fun visit_kfPlus_0(node: ASTNode.InnerNode<MetaDescription.kfPlus>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    kfPlus -> <eps>
     */
    override fun visit_kfPlus_1(node: ASTNode.InnerNode<MetaDescription.kfPlus>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    tComp -> COMPANION LPAREN tSkip RPAREN
     */
    override fun visit_tComp_0(node: ASTNode.InnerNode<MetaDescription.tComp>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    tComp -> <eps>
     */
    override fun visit_tComp_1(node: ASTNode.InnerNode<MetaDescription.tComp>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    tFrag -> FRAGMENTS LPAREN tFragPlus RPAREN
     */
    override fun visit_tFrag_0(node: ASTNode.InnerNode<MetaDescription.tFrag>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    tFrag -> <eps>
     */
    override fun visit_tFrag_1(node: ASTNode.InnerNode<MetaDescription.tFrag>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    tPlus -> tLine tPlus
     */
    override fun visit_tPlus_0(node: ASTNode.InnerNode<MetaDescription.tPlus>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    tPlus -> <eps>
     */
    override fun visit_tPlus_1(node: ASTNode.InnerNode<MetaDescription.tPlus>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    tSkip -> SKIP DESCRIBE tArray EOLN
     */
    override fun visit_tSkip(node: ASTNode.InnerNode<MetaDescription.tSkip>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    tArray -> LARRAY CAPSNAME tArrayPlus RARRAY
     */
    override fun visit_tArray(node: ASTNode.InnerNode<MetaDescription.tArray>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    tArrayPlus -> SEP CAPSNAME tArrayPlus
     */
    override fun visit_tArrayPlus_0(node: ASTNode.InnerNode<MetaDescription.tArrayPlus>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    tArrayPlus -> <eps>
     */
    override fun visit_tArrayPlus_1(node: ASTNode.InnerNode<MetaDescription.tArrayPlus>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    tFragPlus -> tFragLine tFragPlus
     */
    override fun visit_tFragPlus_0(node: ASTNode.InnerNode<MetaDescription.tFragPlus>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    tFragPlus -> <eps>
     */
    override fun visit_tFragPlus_1(node: ASTNode.InnerNode<MetaDescription.tFragPlus>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    tFragLine -> CAPSNAME DESCRIBE STRING EOLN
     */
    override fun visit_tFragLine(node: ASTNode.InnerNode<MetaDescription.tFragLine>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    tLine -> CAPSNAME DESCRIBE tDef EOLN
     */
    override fun visit_tLine(node: ASTNode.InnerNode<MetaDescription.tLine>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    tDef -> STRING
     */
    override fun visit_tDef_0(node: ASTNode.InnerNode<MetaDescription.tDef>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    tDef -> RSTRING
     */
    override fun visit_tDef_1(node: ASTNode.InnerNode<MetaDescription.tDef>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    tDef -> LTRIG CHAR CHARRANGE CHAR RTRIG
     */
    override fun visit_tDef_2(node: ASTNode.InnerNode<MetaDescription.tDef>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    gComp -> COMPANION LPAREN gSynth gInh gCompv gStart RPAREN
     */
    override fun visit_gComp(node: ASTNode.InnerNode<MetaDescription.gComp>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    gPlus -> gLine gPlus
     */
    override fun visit_gPlus_0(node: ASTNode.InnerNode<MetaDescription.gPlus>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    gPlus -> <eps>
     */
    override fun visit_gPlus_1(node: ASTNode.InnerNode<MetaDescription.gPlus>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    gSynth -> SYNTHESIS LPAREN attribs RPAREN
     */
    override fun visit_gSynth_0(node: ASTNode.InnerNode<MetaDescription.gSynth>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    gSynth -> <eps>
     */
    override fun visit_gSynth_1(node: ASTNode.InnerNode<MetaDescription.gSynth>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    gInh -> INHERITANCE LPAREN attribs RPAREN
     */
    override fun visit_gInh_0(node: ASTNode.InnerNode<MetaDescription.gInh>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    gInh -> <eps>
     */
    override fun visit_gInh_1(node: ASTNode.InnerNode<MetaDescription.gInh>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    gCompv -> COMPUTE LPAREN attribs RPAREN
     */
    override fun visit_gCompv_0(node: ASTNode.InnerNode<MetaDescription.gCompv>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    gCompv -> <eps>
     */
    override fun visit_gCompv_1(node: ASTNode.InnerNode<MetaDescription.gCompv>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    gStart -> START DESCRIBE CAMELNAME EOLN
     */
    override fun visit_gStart(node: ASTNode.InnerNode<MetaDescription.gStart>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    attribs -> attrib attribsPlus
     */
    override fun visit_attribs(node: ASTNode.InnerNode<MetaDescription.attribs>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    attrib -> CAMELNAME DESCRIBE type setDef EOLN
     */
    override fun visit_attrib(node: ASTNode.InnerNode<MetaDescription.attrib>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    attribsPlus -> attrib attribsPlus
     */
    override fun visit_attribsPlus_0(node: ASTNode.InnerNode<MetaDescription.attribsPlus>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    attribsPlus -> <eps>
     */
    override fun visit_attribsPlus_1(node: ASTNode.InnerNode<MetaDescription.attribsPlus>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    type -> INT_TYPE
     */
    override fun visit_type_0(node: ASTNode.InnerNode<MetaDescription.type>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    type -> DOUBLE_TYPE
     */
    override fun visit_type_1(node: ASTNode.InnerNode<MetaDescription.type>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    type -> STRING_TYPE
     */
    override fun visit_type_2(node: ASTNode.InnerNode<MetaDescription.type>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    setDef -> DEFINE LPAREN DEFAULT ASSIGN defValue RPAREN
     */
    override fun visit_setDef_0(node: ASTNode.InnerNode<MetaDescription.setDef>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    setDef -> <eps>
     */
    override fun visit_setDef_1(node: ASTNode.InnerNode<MetaDescription.setDef>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    defValue -> STRING
     */
    override fun visit_defValue_0(node: ASTNode.InnerNode<MetaDescription.defValue>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    defValue -> defTerm defMod
     */
    override fun visit_defValue_1(node: ASTNode.InnerNode<MetaDescription.defValue>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    defValue -> SUB defTerm
     */
    override fun visit_defValue_2(node: ASTNode.InnerNode<MetaDescription.defValue>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    gLine -> CAMELNAME def DESCRIBE rules EOLN
     */
    override fun visit_gLine(node: ASTNode.InnerNode<MetaDescription.gLine>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    def -> DEFINE LPAREN defBody RPAREN
     */
    override fun visit_def_0(node: ASTNode.InnerNode<MetaDescription.def>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    def -> <eps>
     */
    override fun visit_def_1(node: ASTNode.InnerNode<MetaDescription.def>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    rules -> rule rulesPlus
     */
    override fun visit_rules(node: ASTNode.InnerNode<MetaDescription.rules>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    rule -> seq def
     */
    override fun visit_rule(node: ASTNode.InnerNode<MetaDescription.rule>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    rulesPlus -> CHOICE rule rulesPlus
     */
    override fun visit_rulesPlus_0(node: ASTNode.InnerNode<MetaDescription.rulesPlus>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    rulesPlus -> <eps>
     */
    override fun visit_rulesPlus_1(node: ASTNode.InnerNode<MetaDescription.rulesPlus>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    seq -> atom seqPlus
     */
    override fun visit_seq(node: ASTNode.InnerNode<MetaDescription.seq>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    atom -> CAPSNAME
     */
    override fun visit_atom_0(node: ASTNode.InnerNode<MetaDescription.atom>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    atom -> CAMELNAME pass
     */
    override fun visit_atom_1(node: ASTNode.InnerNode<MetaDescription.atom>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    seqPlus -> atom seqPlus
     */
    override fun visit_seqPlus_0(node: ASTNode.InnerNode<MetaDescription.seqPlus>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    seqPlus -> <eps>
     */
    override fun visit_seqPlus_1(node: ASTNode.InnerNode<MetaDescription.seqPlus>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    pass -> LPAREN defBody RPAREN
     */
    override fun visit_pass_0(node: ASTNode.InnerNode<MetaDescription.pass>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    pass -> <eps>
     */
    override fun visit_pass_1(node: ASTNode.InnerNode<MetaDescription.pass>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    defBody -> defAtom defPlus
     */
    override fun visit_defBody(node: ASTNode.InnerNode<MetaDescription.defBody>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    defAtom -> CAMELNAME ASSIGN defValue
     */
    override fun visit_defAtom(node: ASTNode.InnerNode<MetaDescription.defAtom>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    defPlus -> SEP defAtom defPlus
     */
    override fun visit_defPlus_0(node: ASTNode.InnerNode<MetaDescription.defPlus>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    defPlus -> <eps>
     */
    override fun visit_defPlus_1(node: ASTNode.InnerNode<MetaDescription.defPlus>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    defTerm -> atName
     */
    override fun visit_defTerm_0(node: ASTNode.InnerNode<MetaDescription.defTerm>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    defTerm -> INT
     */
    override fun visit_defTerm_1(node: ASTNode.InnerNode<MetaDescription.defTerm>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    defTerm -> DOUBLE
     */
    override fun visit_defTerm_2(node: ASTNode.InnerNode<MetaDescription.defTerm>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    defMod -> op defTerm
     */
    override fun visit_defMod_0(node: ASTNode.InnerNode<MetaDescription.defMod>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    defMod -> <eps>
     */
    override fun visit_defMod_1(node: ASTNode.InnerNode<MetaDescription.defMod>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    atName -> SPNAME
     */
    override fun visit_atName_0(node: ASTNode.InnerNode<MetaDescription.atName>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    atName -> CAMELNAME
     */
    override fun visit_atName_1(node: ASTNode.InnerNode<MetaDescription.atName>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    op -> ADD
     */
    override fun visit_op_0(node: ASTNode.InnerNode<MetaDescription.op>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    op -> SUB
     */
    override fun visit_op_1(node: ASTNode.InnerNode<MetaDescription.op>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    op -> MUL
     */
    override fun visit_op_2(node: ASTNode.InnerNode<MetaDescription.op>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
    op -> DIV
     */
    override fun visit_op_3(node: ASTNode.InnerNode<MetaDescription.op>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}