package regex

import grammar.Grammar
import grammar.token.Token
import utils.*

object Regex {

    init {
        Token.AlphaToken.allow('a'..'z')
    }

    // @formatter:off

    val LPAREN = PD('(')
    val RPAREN = PD(')')
    val KLEENE = PD('*')
    val CHOICE = PD('|')

    val R0 = ST("R")
    val R1 = ST("R`")
    val S0 = ST("S")
    val S1 = ST("S`")
    val T  = ST("T")
    val M  = ST("M")
    val N  = ST("N")
    val C  = ST("C")
    val A  = ST("A")

    val grammar = Grammar(
        R0,

        R0 into E(S0, R1),
        R1 into E(EPSILON),
        R1 into E(CHOICE, R0),
        S0 into E(T, S1),
        S1 into E(EPSILON),
        S1 into E(S0),
        T  into E(A, M),
        M  into E(N, C),
        N  into E(EPSILON),
        N  into E(Token.RepresentationToken.AnyNumber),
        C  into E(EPSILON),
        C  into E(KLEENE, C),
        A  into E(LPAREN, R0, RPAREN),
        A  into E(Token.RepresentationToken.AnyAlpha)
    ).order()

    // @formatter:on

}