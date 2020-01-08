package regex.test

import grammar.token.Token
import structure.Tree
import utils.*

@Suppress("DuplicatedCode")
fun main() {

    val me = Test.me

    Test.CorrectnessTest(
        "CorrectnessTreeLetter", "a",
        Tree.InnerNode(
            me.R0,
            Tree.InnerNode(
                me.S0,
                Tree.InnerNode(
                    me.T,
                    Tree.InnerNode(me.A, Tree.Leaf(me.ALPHA.instantiate("a"))),
                    Tree.InnerNode(me.N, Tree.Leaf(Token.UniqueToken.EPSILON)),
                    Tree.InnerNode(me.C, Tree.Leaf(Token.UniqueToken.EPSILON))
                ),
                Tree.InnerNode(me.S1, Tree.Leaf(Token.UniqueToken.EPSILON))
            ),
            Tree.InnerNode(me.R1, Tree.Leaf(Token.UniqueToken.EPSILON))
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeChoice", "a|b",
        Tree.InnerNode(
            me.R0,
            Tree.InnerNode(
                me.S0,
                Tree.InnerNode(
                    me.T,
                    Tree.InnerNode(me.A, Tree.Leaf(me.ALPHA.instantiate("a"))),
                    Tree.InnerNode(me.N, Tree.Leaf(Token.UniqueToken.EPSILON)),
                    Tree.InnerNode(me.C, Tree.Leaf(Token.UniqueToken.EPSILON))
                ),
                Tree.InnerNode(me.S1, Tree.Leaf(Token.UniqueToken.EPSILON))
            ),
            Tree.InnerNode(
                me.R1,
                Tree.Leaf(me.CHOICE),
                Tree.InnerNode(
                    me.R0,
                    Tree.InnerNode(
                        me.S0,
                        Tree.InnerNode(
                            me.T,
                            Tree.InnerNode(me.A, Tree.Leaf(me.ALPHA.instantiate("b"))),
                            Tree.InnerNode(me.N, Tree.Leaf(Token.UniqueToken.EPSILON)),
                            Tree.InnerNode(me.C, Tree.Leaf(Token.UniqueToken.EPSILON))
                        ),
                        Tree.InnerNode(me.S1, Tree.Leaf(Token.UniqueToken.EPSILON))
                    ),
                    Tree.InnerNode(me.R1, Tree.Leaf(Token.UniqueToken.EPSILON))
                )
            )
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeNumber", "a5",
        Tree.InnerNode(
            me.R0,
            Tree.InnerNode(
                me.S0,
                Tree.InnerNode(
                    me.T,
                    Tree.InnerNode(me.A, Tree.Leaf(me.ALPHA.instantiate("a"))),
                    Tree.InnerNode(me.N, Tree.Leaf(me.NUMBER.instantiate("5"))),
                    Tree.InnerNode(me.C, Tree.Leaf(Token.UniqueToken.EPSILON))
                ),
                Tree.InnerNode(me.S1, Tree.Leaf(Token.UniqueToken.EPSILON))
            ),
            Tree.InnerNode(me.R1, Tree.Leaf(Token.UniqueToken.EPSILON))
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeKleene", "a*",
        Tree.InnerNode(
            me.R0,
            Tree.InnerNode(
                me.S0,
                Tree.InnerNode(
                    me.T,
                    Tree.InnerNode(me.A, Tree.Leaf(me.ALPHA.instantiate("a"))),
                    Tree.InnerNode(me.N, Tree.Leaf(Token.UniqueToken.EPSILON)),
                    Tree.InnerNode(
                        me.C,
                        Tree.Leaf(me.KLEENE),
                        Tree.InnerNode(me.C, Tree.Leaf(Token.UniqueToken.EPSILON))
                    )
                ),
                Tree.InnerNode(me.S1, Tree.Leaf(Token.UniqueToken.EPSILON))
            ),
            Tree.InnerNode(me.R1, Tree.Leaf(Token.UniqueToken.EPSILON))
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeConcatenate", "ab",
        Tree.InnerNode(
            me.R0,
            Tree.InnerNode(
                me.S0,
                Tree.InnerNode(
                    me.T,
                    Tree.InnerNode(me.A, Tree.Leaf(me.ALPHA.instantiate("a"))),
                    Tree.InnerNode(me.N, Tree.Leaf(Token.UniqueToken.EPSILON)),
                    Tree.InnerNode(me.C, Tree.Leaf(Token.UniqueToken.EPSILON))
                ),
                Tree.InnerNode(
                    me.S1,
                    Tree.InnerNode(
                        me.S0,
                        Tree.InnerNode(
                            me.T,
                            Tree.InnerNode(me.A, Tree.Leaf(me.ALPHA.instantiate("b"))),
                            Tree.InnerNode(me.N, Tree.Leaf(Token.UniqueToken.EPSILON)),
                            Tree.InnerNode(me.C, Tree.Leaf(Token.UniqueToken.EPSILON))
                        ),
                        Tree.InnerNode(me.S1, Tree.Leaf(Token.UniqueToken.EPSILON))
                    )
                )
            ),
            Tree.InnerNode(me.R1, Tree.Leaf(Token.UniqueToken.EPSILON))
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeParenthesis", "(a)", Tree.InnerNode(
            me.R0,
            Tree.InnerNode(
                me.S0,
                Tree.InnerNode(
                    me.T,
                    Tree.InnerNode(
                        me.A,
                        Tree.Leaf(me.LPAREN),
                        Tree.InnerNode(
                            me.R0,
                            Tree.InnerNode(
                                me.S0,
                                Tree.InnerNode(
                                    me.T,
                                    Tree.InnerNode(me.A, Tree.Leaf(me.ALPHA.instantiate("a"))),
                                    Tree.InnerNode(me.N, Tree.Leaf(Token.UniqueToken.EPSILON)),
                                    Tree.InnerNode(me.C, Tree.Leaf(Token.UniqueToken.EPSILON))
                                ),
                                Tree.InnerNode(me.S1, Tree.Leaf(Token.UniqueToken.EPSILON))
                            ),
                            Tree.InnerNode(me.R1, Tree.Leaf(Token.UniqueToken.EPSILON))
                        ),
                        Tree.Leaf(me.RPAREN)
                    ),
                    Tree.InnerNode(me.N, Tree.Leaf(Token.UniqueToken.EPSILON)),
                    Tree.InnerNode(me.C, Tree.Leaf(Token.UniqueToken.EPSILON))
                ),
                Tree.InnerNode(me.S1, Tree.Leaf(Token.UniqueToken.EPSILON))
            ),
            Tree.InnerNode(me.R1, Tree.Leaf(Token.UniqueToken.EPSILON))
        )
    )

    Test.CorrectnessTest("ParsesLongConcatenation", "abcdef")
    Test.CorrectnessTest("ParsesLongChoice", "a|b|c|d|e|f")
    Test.CorrectnessTest("ParsesLongKleene", "a*****")
    Test.CorrectnessTest("ParsesLongParenthesis", "(((((a)))))")

    Test.CorrectnessTest("ParsesComplicated (1)", "a|b*|c**|(a*|b**|(c*|d)**)*ab")
    Test.CorrectnessTest("ParsesComplicated (2)", "(z(y(x*)|(y|z*))*|(x|y)|z*)**")

    Test.ParseExceptionTest("LexerCapital", "abcDef")
    Test.ParseExceptionTest("LexerUnknown", "^bcdef")

    Test.ParseExceptionTest("StartsWithChoice", "|abcd")
    Test.ParseExceptionTest("StartsWithChoiceEmpty", "|")
    Test.ParseExceptionTest("StartsWithAsterisk", "*abcd")
    Test.ParseExceptionTest("StartsWithAsteriskEmpty", "*")
    Test.ParseExceptionTest("StartsWithRightParenthesis", ")abc(d)")

    Test.ParseExceptionTest("EndsWithChoice", "a|b|c|")
    Test.ParseExceptionTest("EndsWithOpeningParenthesis (1)", "a(bc(")
    Test.ParseExceptionTest("EndsWithOpeningParenthesis (2)", "a(bc)(")

    Test.ParseExceptionTest("Empty", "")
    Test.ParseExceptionTest("EmptyChoice", "a||b")
    Test.ParseExceptionTest("EmptyParenthesis", "a()b")

    Test.ParseExceptionTest("UnmatchedOpeningParenthesis", "a(bc(d)e")
    Test.ParseExceptionTest("UnmatchedClosingParenthesis", "x(abc)d)e")
    Test.ParseExceptionTest("AsteriskAfterLeftParenthesis", "a(*b)")
    Test.ParseExceptionTest("AsteriskAfterChoice", "a|b|*")

    Test.CorrectnessTest("NoNumberNoAsterisk", "(x)")

    Test.CorrectnessTest("Number", "(b)a1")
    Test.CorrectnessTest("NumberLong", "(b)a12345")
    Test.CorrectnessTest("NumberLongToParenthesis", "(a)12345")
    Test.CorrectnessTest("Asterisk", "(b)a*")
    Test.CorrectnessTest("AsteriskLong", "(b)a****")
    Test.CorrectnessTest("AsteriskLongToParenthesis", "(a)****")

    Test.CorrectnessTest("NumberAndAsterisk", "a12*")
    Test.CorrectnessTest("NumberAndAsteriskLong", "a12***")

    Test.ParseExceptionTest("AsteriskAndNumber", "a*12")
    Test.ParseExceptionTest("AsteriskWithNoAtom", "*d12")
    Test.ParseExceptionTest("NumberWithNoAtom", "12d*")

    Test.runAll()

}