/**
This code is generated by [translate.codegen.GrammarInfoBuilder] derived from [translate.meta.MetaWalkerBase] 
generated by [translate.codegen.WalkerBuilder] 
basing on grammar description [translate.meta.helpers.MetaGrammarInfo] derived from [translate.codegen.helpers.GrammarInfo]
*/

package regex

import grammar.token.Token
import grammar.Grammar
import grammar.Expansion

import translate.codegen.helpers.GrammarInfo

object RegexGrammarInfo : GrammarInfo {

    override fun getSkippedTokens(): Set<Token> {
        return setOf()
    }
    
    override fun getGrammar(): Grammar {
        return grammar
    }
    
    override fun getDefinedTokens(): List<Token> {
        return listOf(
            LPAREN, RPAREN, KLEENE, CHOICE, ALPHA, UINT,
            regex, regexPlus, sequence, sequencePlus, term, number, closure, atom            
        )
    }
    
    override fun getSimpleName(): String {
        return "Regex"
    }
    
    init {
        Token.switchTo(getName())
        check()
    }
    
    object regex : Token.StateToken("regex")
    object regexPlus : Token.StateToken("regexPlus")
    object sequence : Token.StateToken("sequence")
    object sequencePlus : Token.StateToken("sequencePlus")
    object term : Token.StateToken("term")
    object number : Token.StateToken("number")
    object closure : Token.StateToken("closure")
    object atom : Token.StateToken("atom")

    object LPAREN : Token.StringToken("LPAREN", "(")
    object RPAREN : Token.StringToken("RPAREN", ")")
    object KLEENE : Token.StringToken("KLEENE", "*")
    object CHOICE : Token.StringToken("CHOICE", "|")
    object ALPHA : Token.CharRangeToken("ALPHA", 'a'..'z')
    object UINT : Token.RegexToken("UINT", "[1-9][0-9]*".toRegex())

    private val grammar = Grammar(
        regex,

        regex into Expansion(sequence, regexPlus),
        regexPlus into Expansion(CHOICE, regex),
        regexPlus into Expansion(Token.UniqueToken.EPSILON),
        sequence into Expansion(term, sequencePlus),
        sequencePlus into Expansion(sequence),
        sequencePlus into Expansion(Token.UniqueToken.EPSILON),
        term into Expansion(atom, number, closure),
        number into Expansion(UINT),
        number into Expansion(Token.UniqueToken.EPSILON),
        closure into Expansion(KLEENE, closure),
        closure into Expansion(Token.UniqueToken.EPSILON),
        atom into Expansion(LPAREN, regex, RPAREN),
        atom into Expansion(ALPHA)
    ).order()

}