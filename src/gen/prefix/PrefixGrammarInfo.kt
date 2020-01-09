/**
This code is generated by [translate.codegen.GrammarInfoBuilder]
deriving from base class [translate.meta.MetaBaseVisitor] generated by [translate.meta.MetaVisitorInfo]
*/

package gen.prefix

import grammar.token.Token
import grammar.Grammar
import grammar.Expansion

import translate.codegen.info.GrammarInfo

object PrefixGrammarInfo : GrammarInfo {

    override fun getSkippedTokens(): Set<Token> {
        return setOf(WHITESPACE)
    }
    
    override fun getGrammar(): Grammar {
        return grammar
    }
    
    override fun getDefinedTokens(): List<Token> {
        return listOf(
            WHITESPACE, IFW, FORW, PASS, BOTH, PRINTW, PLUS, MINUS, TIMES, DIV, NOT, XOR, AND, OR, GE, GT, LE, LT, EQ, NE, ASSIGN, VAR, UINT,
            main, code, codeBlock, statement, print, assignment, ifBlock, forBlock, innerBody, expression, logicalBinop, logicalUnop, logicalExpression, logicalAtom, relation, comparison, arithmeticBinop, arithmeticExpression, arithmeticAtom            
        )
    }
    
    override fun getName(): String {
        return "PrefixGrammarInfo"
    }
    
    init {
        Token.switchTo(getName())
        check()
    }
    
    object main : Token.StateToken("main")
    object code : Token.StateToken("code")
    object codeBlock : Token.StateToken("codeBlock")
    object statement : Token.StateToken("statement")
    object print : Token.StateToken("print")
    object assignment : Token.StateToken("assignment")
    object ifBlock : Token.StateToken("ifBlock")
    object forBlock : Token.StateToken("forBlock")
    object innerBody : Token.StateToken("innerBody")
    object expression : Token.StateToken("expression")
    object logicalBinop : Token.StateToken("logicalBinop")
    object logicalUnop : Token.StateToken("logicalUnop")
    object logicalExpression : Token.StateToken("logicalExpression")
    object logicalAtom : Token.StateToken("logicalAtom")
    object relation : Token.StateToken("relation")
    object comparison : Token.StateToken("comparison")
    object arithmeticBinop : Token.StateToken("arithmeticBinop")
    object arithmeticExpression : Token.StateToken("arithmeticExpression")
    object arithmeticAtom : Token.StateToken("arithmeticAtom")

    fun tab(depth : Int): String {
        return "\t".repeat(depth)
    }

    val LETTER = "[a-zA-Z]"
    val DIGIT = "[0-9]"
    val FIRST = "($LETTER|_)"
    object WHITESPACE : Token.RegexToken("WHITESPACE", "[ \r\n\t]".toRegex())
    object IFW : Token.StringToken("IFW", "if")
    object FORW : Token.StringToken("FORW", "for")
    object PASS : Token.StringToken("PASS", "pass")
    object BOTH : Token.StringToken("BOTH", "both")
    object PRINTW : Token.StringToken("PRINTW", "print")
    object PLUS : Token.StringToken("PLUS", "+")
    object MINUS : Token.StringToken("MINUS", "-")
    object TIMES : Token.StringToken("TIMES", "*")
    object DIV : Token.StringToken("DIV", "/")
    object NOT : Token.StringToken("NOT", "!")
    object XOR : Token.StringToken("XOR", "^")
    object AND : Token.StringToken("AND", "&")
    object OR : Token.StringToken("OR", "|")
    object GE : Token.StringToken("GE", ">=")
    object GT : Token.StringToken("GT", ">")
    object LE : Token.StringToken("LE", "<=")
    object LT : Token.StringToken("LT", "<")
    object EQ : Token.StringToken("EQ", "==")
    object NE : Token.StringToken("NE", "!=")
    object ASSIGN : Token.StringToken("ASSIGN", "=")
    object VAR : Token.RegexToken("VAR", "$FIRST($FIRST|$DIGIT)*".toRegex())
    object UINT : Token.RegexToken("UINT", "$DIGIT*".toRegex())

    private val grammar = Grammar(
        /*
        synthesis: view: String = @1.view 
        inheritance: depth: Int = @.depth 
        compute: tab: String = @macro.tab(depth) 
        */
        main,
        
        main into Expansion(code) /* { view = "fun main() {\n${@1.view}}\n" } */,
        code into Expansion(codeBlock /* { depth = 1  } */, code) /* { view = "${@1.view}${@2.view}" } */,
        code into Expansion(Token.UniqueToken.EPSILON) /* { view = "" } */,
        codeBlock into Expansion(statement),
        codeBlock into Expansion(ifBlock),
        codeBlock into Expansion(forBlock),
        codeBlock into Expansion(PASS) /* { view = "\n" } */,
        statement /* { view = "$tab${@1.view}" } */ into Expansion(print),
        statement /* { view = "$tab${@1.view}" } */ into Expansion(assignment),
        print into Expansion(PRINTW, expression) /* { view = "print(${@2.view})\n" } */,
        assignment into Expansion(ASSIGN, VAR, expression) /* { view = "$@2 = ${@3.view}\n" } */,
        ifBlock into Expansion(IFW, logicalExpression, innerBody /* { depth = @.depth + 1 } */, innerBody /* { depth = @.depth + 1 } */) /* { view = "${tab}if (${@1.view}) {\n${@3.view}${tab}} else {\n${@4.view}${tab}}" } */,
        forBlock into Expansion(FORW, VAR, arithmeticAtom, arithmeticAtom, innerBody /* { depth = @.depth + 1 } */) /* { view = "${tab}for ($@2 in ${@3.view}..${@4.view}) {\n${@5.view}${tab}}" } */,
        innerBody into Expansion(codeBlock),
        innerBody into Expansion(BOTH, codeBlock, innerBody) /* { view = "${@2.view}${@3.view}" } */,
        expression into Expansion(logicalExpression),
        expression into Expansion(arithmeticExpression),
        logicalBinop /* { view = @1.STRING  } */ into Expansion(AND),
        logicalBinop /* { view = @1.STRING  } */ into Expansion(OR),
        logicalBinop /* { view = @1.STRING  } */ into Expansion(XOR),
        logicalUnop /* { view = @1.STRING  } */ into Expansion(NOT),
        logicalExpression into Expansion(logicalBinop, logicalExpression, logicalExpression) /* { view = "(${@2.view} ${@1.view} ${@3.view})" } */,
        logicalExpression into Expansion(logicalUnop, logicalExpression) /* { view = "${@1.view}${@2.view}" } */,
        logicalExpression into Expansion(logicalAtom),
        logicalAtom into Expansion(VAR) /* { view = @1.STRING  } */,
        logicalAtom into Expansion(comparison),
        relation /* { view = @1.STRING  } */ into Expansion(EQ),
        relation /* { view = @1.STRING  } */ into Expansion(NE),
        relation /* { view = @1.STRING  } */ into Expansion(GT),
        relation /* { view = @1.STRING  } */ into Expansion(GE),
        relation /* { view = @1.STRING  } */ into Expansion(LT),
        relation /* { view = @1.STRING  } */ into Expansion(LE),
        comparison into Expansion(relation, arithmeticExpression, arithmeticExpression) /* { view = "(${@2.view} ${@1.view} ${@3.view})" } */,
        arithmeticBinop /* { view = @1.STRING  } */ into Expansion(PLUS),
        arithmeticBinop /* { view = @1.STRING  } */ into Expansion(MINUS),
        arithmeticBinop /* { view = @1.STRING  } */ into Expansion(TIMES),
        arithmeticBinop /* { view = @1.STRING  } */ into Expansion(DIV),
        arithmeticExpression into Expansion(arithmeticBinop, arithmeticExpression, arithmeticExpression) /* { view = "(${@2.view} ${@1.view} ${@3.view})" } */,
        arithmeticExpression into Expansion(arithmeticAtom),
        arithmeticAtom /* { view = @1.STRING  } */ into Expansion(VAR),
        arithmeticAtom /* { view = @1.STRING  } */ into Expansion(UINT)
    ).order()

}