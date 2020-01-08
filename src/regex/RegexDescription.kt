package regex

import grammar.Expansion
import grammar.Grammar
import grammar.token.Token
import structure.Description

object RegexDescription : Description {

    // @formatter:off

    val ALPHA = Token.CharRangeToken("<alpha>", 'a'..'z')
    val NUMBER = Token.RegexToken("<number>", "[1-9]\\d*".toRegex())

    val LPAREN = Token.StringToken("<lparen>","(")
    val RPAREN = Token.StringToken("<rparen>", ")")
    val KLEENE = Token.StringToken("<kleene>", "*")
    val CHOICE = Token.StringToken("<choice>", "|")

    val R0 = Token.StateToken("REGEX")
    val R1 = R0.derived()
    val S0 = Token.StateToken("SEQUENCE")
    val S1 = S0.derived()
    val T  = Token.StateToken("TERM")
    val N  = Token.StateToken("NUMBER")
    val C  = Token.StateToken("CLOSURE")
    val A  = Token.StateToken("ALPHA")

    private val grammar = Grammar(
        R0,

        R0 into Expansion(S0, R1),
        R1 into Expansion(Token.UniqueToken.EPSILON),
        R1 into Expansion(CHOICE, R0),
        S0 into Expansion(T, S1),
        S1 into Expansion(Token.UniqueToken.EPSILON),
        S1 into Expansion(S0),
        T  into Expansion(A, N, C),
        N  into Expansion(Token.UniqueToken.EPSILON),
        N  into Expansion(NUMBER),
        C  into Expansion(Token.UniqueToken.EPSILON),
        C  into Expansion(KLEENE, C),
        A  into Expansion(LPAREN, R0, RPAREN),
        A  into Expansion(ALPHA)
    ).order()

    // @formatter:on

    override fun getGrammar(): Grammar {
        return grammar
    }

    override fun getSkippedTokens(): Set<Token> {
        return emptySet()
    }

}