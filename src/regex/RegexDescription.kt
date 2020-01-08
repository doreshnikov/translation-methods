package regex

import grammar.Expansion
import grammar.Grammar
import grammar.token.Token
import structure.Description

object RegexDescription : Description {

    // @formatter:off

    object ALPHA : Token.CharRangeToken("ALPHA", 'a'..'z')
    object NUMBER : Token.RegexToken("NUMBER", "[1-9]\\d*".toRegex())

    object LPAREN : Token.StringToken("LPAREN","(")
    object RPAREN : Token.StringToken("RPAREN", ")")
    object KLEENE : Token.StringToken("KLEENE", "*")
    object CHOICE : Token.StringToken("CHOICE", "|")

    object regex        : Token.StateToken("regex")
    object regexPlus    : Token.StateToken("regexPlus")
    object sequence     : Token.StateToken("sequence")
    object sequencePlus : Token.StateToken("sequencePlus")
    object term         : Token.StateToken("term")
    object numberState  : Token.StateToken("numberState")
    object closure      : Token.StateToken("closure")
    object atom         : Token.StateToken("alpha")

    init {
        check(
            ALPHA, NUMBER, LPAREN, RPAREN, KLEENE, CHOICE
        )
    }

    private val grammar = Grammar(
        regex,

        regex           into Expansion(sequence, regexPlus),
        regexPlus       into Expansion(Token.UniqueToken.EPSILON),
        regexPlus       into Expansion(CHOICE, regex),
        sequence        into Expansion(term, sequencePlus),
        sequencePlus    into Expansion(Token.UniqueToken.EPSILON),
        sequencePlus    into Expansion(sequence),
        term            into Expansion(atom, numberState, closure),
        numberState     into Expansion(Token.UniqueToken.EPSILON),
        numberState     into Expansion(NUMBER),
        closure         into Expansion(Token.UniqueToken.EPSILON),
        closure         into Expansion(KLEENE, closure),
        atom            into Expansion(LPAREN, regex, RPAREN),
        atom            into Expansion(ALPHA)
    ).order()

    // @formatter:on

    override fun getGrammar(): Grammar {
        return grammar
    }

    override fun getSkippedTokens(): Set<Token> {
        return emptySet()
    }

}