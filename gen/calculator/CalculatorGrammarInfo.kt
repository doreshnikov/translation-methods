/**
This code is generated by [translate.codegen.GrammarInfoBuilder] derived from [translate.meta.MetaWalkerBase] 
generated by [translate.codegen.WalkerBaseBuilder] 
basing on grammar description [translate.meta.helpers.MetaGrammarInfo] derived from [translate.codegen.helpers.GrammarInfo]
*/

package calculator

import grammar.token.Token
import grammar.Grammar
import grammar.Expansion

import translate.codegen.helpers.GrammarInfo

object CalculatorGrammarInfo : GrammarInfo {

    override fun getSkippedTokens(): Set<Token> {
        return setOf(WHITESPACE)
    }
    
    override fun getGrammar(): Grammar {
        return grammar
    }
    
    override fun getDefinedTokens(): List<Token> {
        return listOf(
            WHITESPACE, LPAREN, RPAREN, PLUS, MINUS, TIMES, DIV, FACT, UINT,
            expression, expressionPlus, term, termPlus, factor, primitive, atom, factorial            
        )
    }
    
    override fun getSimpleName(): String {
        return "Calculator"
    }
    
    init {
        Token.switchTo(getName())
        check()
    }
    
    object expression : Token.StateToken("expression")
    object expressionPlus : Token.StateToken("expressionPlus")
    object term : Token.StateToken("term")
    object termPlus : Token.StateToken("termPlus")
    object factor : Token.StateToken("factor")
    object primitive : Token.StateToken("primitive")
    object atom : Token.StateToken("atom")
    object factorial : Token.StateToken("factorial")

    object WHITESPACE : Token.RegexToken("WHITESPACE", "[ \r\n\t]".toRegex())
    object LPAREN : Token.StringToken("LPAREN", "(")
    object RPAREN : Token.StringToken("RPAREN", ")")
    object PLUS : Token.StringToken("PLUS", "+")
    object MINUS : Token.StringToken("MINUS", "-")
    object TIMES : Token.StringToken("TIMES", "*")
    object DIV : Token.StringToken("DIV", "/")
    object FACT : Token.StringToken("FACT", "!")
    object UINT : Token.RegexToken("UINT", "[0-9]*".toRegex())

    private val grammar = Grammar(
        expression,

        expression into Expansion(term, expressionPlus),
        expressionPlus into Expansion(PLUS, term, expressionPlus),
        expressionPlus into Expansion(MINUS, term, expressionPlus),
        expressionPlus into Expansion(Token.UniqueToken.EPSILON),
        term into Expansion(factor, termPlus),
        termPlus into Expansion(TIMES, factor, termPlus),
        termPlus into Expansion(DIV, factor, termPlus),
        termPlus into Expansion(Token.UniqueToken.EPSILON),
        factor into Expansion(MINUS, factor),
        factor into Expansion(primitive),
        primitive into Expansion(atom, factorial),
        atom into Expansion(LPAREN, expression, RPAREN),
        atom into Expansion(UINT),
        factorial into Expansion(FACT, factorial),
        factorial into Expansion(Token.UniqueToken.EPSILON)
    ).order()

}