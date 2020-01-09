/**
This code is generated by [translate.codegen.DescriptionBuilder2]
deriving from base class [translate.meta.MetaBaseVisitor2] generated by [translate.codegen.VisitorBuilder]
*/

package gen

import grammar.token.Token
import grammar.Grammar
import grammar.Expansion
import structure.Description

object RegexDescription : Description {

    override fun getSkippedTokens(): Set<Token> {
        return setOf()
    }
    
    override fun getGrammar(): Grammar {
        return grammar
    }
    
    init {
        check(LPAREN, RPAREN, KLEENE, CHOICE, ALPHA, UINT)
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
        /*
        
        */
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
