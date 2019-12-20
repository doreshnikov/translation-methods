package regex

import grammar.Grammar
import grammar.Token
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
        T  into E(A, C),
        C  into E(EPSILON),
        C  into E(KLEENE, C),
        A  into E(LPAREN, R0, RPAREN)
    ).also {
        ('a'..'z').map { c -> A into E(AT(c)) }.forEach { r ->
            it.add(r)
        }
    }.order()

    // @formatter:on

}