package translate.codegen

import grammar.Expansion
import grammar.Grammar
import grammar.token.Token
import structure.ASTNode
import structure.Visitor
import translate.codegen.info.GrammarInfo
import translate.codegen.info.VisitorInfo
import translate.meta.grammar.MetaGrammarInfo
import translate.meta.visitors.MetaAttributedVisitorBase
import translate.meta.visitors.MetaAttributedVisitorInfo
import translate.test.loc
import utils.Beautifier
import java.io.File

class AttributedVisitorInfoBuilder(
    private val packageName: String,
    private val objectName: String,
    private val grammarInfoObjectName: String
) : MetaAttributedVisitorBase<String, String> {

    private val packagePath = packageName.replace(".", "\\")

    fun <T : Token> doAll(root: ASTNode<T>) {
        val path = "$loc\\src\\$packagePath"
        val objectData = collect(root)
        File("$path\\${objectName}Main.kt").bufferedWriter().use { out -> out.write(getMain()) }
        File("$path\\$objectName.kt").bufferedWriter().use { out -> out.write(objectData) }
    }

    data class Attribute(val name: String, val type: String) {
        override fun toString(): String {
            return "$name: $type"
        }
    }

    data class ComputedAttribute(val name: String, val type: String, val init: String) {
        override fun toString(): String {
            return "val $name: $type\n\t\tget() = $init"
        }
    }

    val synthesis = mutableListOf<Attribute>()
    val inheritance = mutableListOf<Attribute>()
    val compute = mutableListOf<ComputedAttribute>()

    override fun <T : Token> visitTerminal(token: T): String {
        return token.getText()
    }

    private fun visitList(sep: String, vararg elements: String): String {
        return elements.filter { it.isNotBlank() }.joinToString(sep)
    }

    private fun getMain(): String {
        return """/**
This code is generated by [${VisitorInfoBuilder::class.qualifiedName}]
deriving from base class [${MetaAttributedVisitorBase::class.qualifiedName}] generated by [${MetaAttributedVisitorInfo::class.qualifiedName}]
*/

package $packageName

import java.io.File

import ${Token::class.qualifiedName}
import ${Expansion::class.qualifiedName}
import ${Beautifier::class.qualifiedName}

import ${ASTNode::class.qualifiedName}

class ${objectName}Data(${inheritance.joinToString(", ") { "val $it" }}) {
${synthesis.joinToString("\n") { "\tvar $it" }}
${compute.joinToString("\n") { "\t$it" }}
}
    
class ${objectName}TerminalNode<T : ${Token::class.simpleName}>(token: T${inheritance.joinToString("") { ", $it" }}) : 
    ${ASTNode::class.simpleName}.${ASTNode.TerminalNode::class.simpleName}<T>(token) {
    val data = ${objectName}Data(${inheritance.joinToString(", ") { it.name }})
}
    
class ${objectName}InnerNode<T : ${Token::class.simpleName}>(token: T, expansion: Expansion${inheritance.joinToString("") { ", $it" }}) : 
    ${ASTNode::class.simpleName}.${ASTNode.InnerNode::class.simpleName}<T>(token, expansion) {
    val data = ${objectName}Data(${inheritance.joinToString(", ") { it.name }})    
}

fun main() {
    File("${Beautifier.escape(loc)}\\src\\${Beautifier.escape(packagePath)}\\${"$"}{$objectName.className}.kt").bufferedWriter().use { out ->
        out.write($objectName.getAll())
    }
}
"""
    }

    override fun <T : Token> collect(root: ASTNode<T>): String {
        val collected = visit(root, null)
        return Beautifier.detabify(
            """/**
This code is generated by [${VisitorInfoBuilder::class.qualifiedName}]
deriving from base class [${MetaAttributedVisitorBase::class.qualifiedName}] generated by [${MetaAttributedVisitorInfo::class.qualifiedName}]
*/

package $packageName

import ${Token::class.qualifiedName}
import ${Grammar::class.qualifiedName}
import ${Expansion::class.qualifiedName}
import ${Beautifier::class.qualifiedName}

import ${ASTNode::class.qualifiedName}
import ${Visitor::class.qualifiedName}

import ${VisitorInfo::class.qualifiedName}
import ${GrammarInfo::class.qualifiedName}

object $objectName : ${VisitorInfo::class.simpleName} {

    val grammarInfo = $grammarInfoObjectName
    val packageName = "$packageName"
    val className = "${objectName}Generated"
    
    override fun getDefinedTokens(): List<${Token::class.simpleName}> {
        return grammarInfo.getDefinedTokens()
    }
    
    override fun getFullName(token: ${Token::class.simpleName}): String {
        return "${"$"}{$grammarInfoObjectName.getName()}.${"$"}token"
    }
    
    override fun getNodeType(token: ${Token::class.simpleName}): String {
        return when (token) {
            is ${Token::class.simpleName}.${Token.StateToken::class.simpleName} -> 
                "${objectName}InnerNode<${"$"}{getFullName(token)}>"
            is ${Token::class.simpleName}.${Token.DataToken::class.simpleName} -> 
                "${objectName}TerminalNode<${"$"}{getFullName(token)}>"
            is ${Token::class.simpleName}.${Token.VariantToken::class.simpleName} -> 
                "${"${objectName}TerminalNode<${Token::class.simpleName}" +
                    ".${Token.VariantToken::class.simpleName}" +
                    ".${Token.VariantToken.VariantInstanceToken::class.simpleName}" +
                    "<${"$"}{getFullName(token)}>>"}"
            else -> throw IllegalArgumentException("Unexpected token ${"$"}token type")
        }
    }
    
    override fun getVisitMethods(token: ${Token::class.simpleName}): String {
        return when (token) {
            is ${Token::class.simpleName}.${Token.StateToken::class.simpleName} -> getStateVisitMethods(token)
            else -> getTerminalVisitMethods(token)
        }
    }
    
    private fun getStateVisitMethods(token: ${Token::class.simpleName}.${Token.StateToken::class.simpleName}): String {
        val expansions = grammarInfo.getGrammar().RULES[token].expansions
        return if (expansions.size == 1) {
            ""${'"'}
    /**
    ${"$"}token -> ${"$"}{expansions.first()}
    */
    fun visit_${"$"}{token}(node: ${"$"}{getNodeType(token)}): ${objectName}Data""${'"'}
        } else {
            ""${'"'}
    fun visit_${"$"}{token}(node: ${"$"}{getNodeType(token)}): ${objectName}Data {
        return when (val id = node.getExpansion().getId()) {
${"$"}{expansions.joinToString("\n") { "\t\t\t${"$"}{it.getId()} -> visit_${"$"}{token}_${"$"}{it.getId()}(node)" }}
            else -> throw IllegalStateException("Unexpected expansion id ${"$"}{"${"$"}"}id in expansion of ${"$"}token")
        }
    }
${"$"}{expansions.joinToString("\n") {
                ""${'"'}
    /**
    ${"$"}token -> ${"$"}it
    */
    fun visit_${"$"}{token}_${"$"}{it.getId()}(node: ${"$"}{getNodeType(token)}): ${objectName}Data""${'"'}
            }}""${'"'}
        }
    }

    private fun getTerminalVisitMethods(token: Token): String {
        return ""${'"'}
    fun visit_${"$"}{token}(node: ${"$"}{getNodeType(token)}): ${objectName}Data {
        return visitTerminal(node.getToken())
    }""${'"'}
    }
    
    override fun getAll(): String {
        return Beautifier.detabify(
            ""${'"'}/**
This code is generated by [${"$"}{$objectName::class.qualifiedName}] derived from [${"$"}{VisitorInfo::class.qualifiedName}]
based on grammar description [${"$"}{$grammarInfoObjectName::class.qualifiedName}] derived from [${"$"}{GrammarInfo::class.qualifiedName}]
*/

package ${"$"}packageName

import ${"$"}{Visitor::class.qualifiedName}
import ${"$"}{ASTNode::class.qualifiedName}
import ${"$"}{Token::class.qualifiedName}
import ${"$"}packageName.${"$"}{grammarInfo.getName()}

@Suppress("UNCHECKED_CAST")
interface ${"$"}className : ${"$"}{Visitor::class.simpleName}<${objectName}Data> {

/*
${"$"}{grammarInfo.getGrammar()}
*/

    override fun visit(node: ${ASTNode::class.simpleName}<out ${Token::class.simpleName}>): ${objectName}Data {${"$"}{getChoiceVisit()}
    }
    
    fun <T : ${Token::class.simpleName}> visitTerminal(token: T): ${objectName}Data
    
${"$"}{getVisitMethods()}

}""${'"'}
        )
    }
    
$collected
}
"""
        )
    }

    /**
    all -> m t g
     */
    override fun visit_all(node: ASTNode.InnerNode<MetaGrammarInfo.all>, value: String?): String {
        visit_g(node.getChild(2), null)
        return visit_m(node.getChild(0), null)
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
        return "${visit_KOTLIN_FUNC(node.getChild(0), null)}\n${visit_kfPlus(node.getChild(1), null)}"
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
//            visit_gPlus(node.getChild(3), null)
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
    gLine -> CAMELNAME def DESCRIBE rules EOLN
     */
    override fun visit_gLine(node: ASTNode.InnerNode<MetaGrammarInfo.gLine>, value: String?): String {
        return super.visit_gLine(node, value)
    }

    /**
    gPlus -> gLine gPlus
     */
    override fun visit_gPlus_0(node: ASTNode.InnerNode<MetaGrammarInfo.gPlus>, value: String?): String {
        return super.visit_gPlus_0(node, value)
    }

    /**
    gPlus -> <eps>
     */
    override fun visit_gPlus_1(node: ASTNode.InnerNode<MetaGrammarInfo.gPlus>, value: String?): String {
        return super.visit_gPlus_1(node, value)
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
        return visit_SPNAME(node.getChild(0), null)
    }

    /**
    atName -> CAMELNAME
     */
    override fun visit_atName_1(node: ASTNode.InnerNode<MetaGrammarInfo.atName>, value: String?): String {
        return visit_CAMELNAME(node.getChild(0), null)
    }

}