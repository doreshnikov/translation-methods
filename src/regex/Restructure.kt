package regex

import grammar.Grammar
import grammar.Token
import parse.Helper
import utils.*

fun main() {

    val me = Regex
    val initialGrammar = Grammar(
        ST("R"),

        ST("R") into E(ST("S")),
        ST("R") into E(ST("R"), Regex.CHOICE, ST("S")),
        ST("S") into E(ST("T")),
        ST("S") into E(ST("S"), ST("T")),
        ST("T") into E(ST("T"), Regex.KLEENE),
        ST("T") into E(ST("A")),
        ST("A") into E(Regex.LPAREN, ST("R"), Regex.RPAREN)
    ).also {
        ('a'..'z').map { c -> ST("A") into E(AT(c)) }.forEach { r ->
            it.add(r)
        }
    }.order()

    Beautifier.println(initialGrammar)
    val g1 = initialGrammar.directLeftRecursionElimination()
    Beautifier.println(g1)
    val g2 = g1.rightBranchingElimination()
    Beautifier.println(g2)

    Beautifier.println(me.grammar)
    assert(
        me.grammar.directLeftRecursionElimination().rightBranchingElimination().globalSize() ==
                me.grammar.globalSize()
    ) { "DLR and RB should be eliminated" }

    val helper = Helper(me.grammar)
    Beautifier.println(helper.FIRST)
    Beautifier.println(helper.FOLLOW)

}