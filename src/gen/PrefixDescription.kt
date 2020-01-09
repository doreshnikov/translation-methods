/**
This code is generated by [translate.codegen.DescriptionBuilder]
deriving from base class [translate.meta.MetaBaseVisitor] generated by [translate.codegen.VisitorBuilder]
*/

package gen

import grammar.token.Token
import grammar.Grammar
import grammar.Expansion
import structure.Description

object PrefixDescription : Description {

    override fun getSkippedTokens(): Set<Token> {
        return setOf(WHITESPACE)
    }
    
    override fun getGrammar(): Grammar {
        return grammar
    }
    
    init {
        check(WHITESPACE, IF, FOR, PASS, BOTH, PRINT, PLUS, MINUS, TIMES, DIV, NOT, XOR, AND, OR, GE, GT, LE, LT, EQ, NE, ASSIGN, VAR, UINT)
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
	object IF : Token.StringToken("IF", "if")
	object FOR : Token.StringToken("FOR", "for")
	object PASS : Token.StringToken("PASS", "pass")
	object BOTH : Token.StringToken("BOTH", "both")
	object PRINT : Token.StringToken("PRINT", "print")
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
        main,
        
		main into Expansion(code),
		code into Expansion(codeBlock, code),
		code into Expansion(Token.UniqueToken.EPSILON),
		codeBlock into Expansion(statement),
		codeBlock into Expansion(ifBlock),
		codeBlock into Expansion(forBlock),
		codeBlock into Expansion(PASS),
		statement into Expansion(print),
		statement into Expansion(assignment),
		print into Expansion(PRINT, expression),
		assignment into Expansion(ASSIGN, VAR, expression),
		ifBlock into Expansion(IF, logicalExpression, innerBody, innerBody),
		forBlock into Expansion(FOR, VAR, arithmeticAtom, arithmeticAtom, innerBody),
		innerBody into Expansion(codeBlock),
		innerBody into Expansion(BOTH, codeBlock, innerBody),
		expression into Expansion(logicalExpression),
		expression into Expansion(arithmeticExpression),
		logicalBinop into Expansion(AND),
		logicalBinop into Expansion(OR),
		logicalBinop into Expansion(XOR),
		logicalUnop into Expansion(NOT),
		logicalExpression into Expansion(logicalBinop, logicalExpression, logicalExpression),
		logicalExpression into Expansion(logicalUnop, logicalExpression),
		logicalExpression into Expansion(logicalAtom),
		logicalAtom into Expansion(VAR),
		logicalAtom into Expansion(comparison),
		relation into Expansion(EQ),
		relation into Expansion(NE),
		relation into Expansion(GT),
		relation into Expansion(GE),
		relation into Expansion(LT),
		relation into Expansion(LE),
		comparison into Expansion(relation, arithmeticExpression, arithmeticExpression),
		arithmeticBinop into Expansion(PLUS),
		arithmeticBinop into Expansion(MINUS),
		arithmeticBinop into Expansion(TIMES),
		arithmeticBinop into Expansion(DIV),
		arithmeticExpression into Expansion(arithmeticBinop, arithmeticExpression, arithmeticExpression),
		arithmeticExpression into Expansion(arithmeticAtom),
		arithmeticAtom into Expansion(VAR),
		arithmeticAtom into Expansion(UINT)
    ).order()

}