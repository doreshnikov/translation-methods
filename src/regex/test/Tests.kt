package regex.test

import structure.Tree
import utils.*

@Suppress("DuplicatedCode")
fun main() {

/*
Start: R
R  -> S R'
R' -> eps | '|' R
S  -> T S'
S' -> eps | S
T  -> A C
C  -> eps | '*' C
A  -> ['a'..'z'] | '(' R ')'

 */

    val me = Test.me

    Test.CorrectnessTest(
        "CorrectnessTreeLetter", "a", Tree.InnerNode(
            me.R0,
            Tree.InnerNode(
                me.S0,
                Tree.InnerNode(
                    me.T,
                    Tree.InnerNode(me.A, Tree.Leaf(AT('a'))),
                    Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                ),
                Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
            ),
            Tree.InnerNode(me.R1, Tree.Leaf(EPSILON))
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeChoice", "a|b", Tree.InnerNode(
            me.R0,
            Tree.InnerNode(
                me.S0,
                Tree.InnerNode(
                    me.T,
                    Tree.InnerNode(me.A, Tree.Leaf(AT('a'))),
                    Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                ),
                Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
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
                            Tree.InnerNode(me.A, Tree.Leaf(AT('b'))),
                            Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                        ),
                        Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
                    ),
                    Tree.InnerNode(me.R1, Tree.Leaf(EPSILON))
                )
            )
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeKleene", "a*", Tree.InnerNode(
            me.R0,
            Tree.InnerNode(
                me.S0,
                Tree.InnerNode(
                    me.T,
                    Tree.InnerNode(me.A, Tree.Leaf(AT('a'))),
                    Tree.InnerNode(
                        me.C,
                        Tree.Leaf(me.KLEENE),
                        Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                    )
                ),
                Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
            ),
            Tree.InnerNode(me.R1, Tree.Leaf(EPSILON))
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeConcatenate", "ab", Tree.InnerNode(
            me.R0,
            Tree.InnerNode(
                me.S0,
                Tree.InnerNode(
                    me.T,
                    Tree.InnerNode(me.A, Tree.Leaf(AT('a'))),
                    Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                ),
                Tree.InnerNode(
                    me.S1,
                    Tree.InnerNode(
                        me.S0,
                        Tree.InnerNode(
                            me.T,
                            Tree.InnerNode(me.A, Tree.Leaf(AT('b'))),
                            Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                        ),
                        Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
                    )
                )
            ),
            Tree.InnerNode(me.R1, Tree.Leaf(EPSILON))
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
                                    Tree.InnerNode(me.A, Tree.Leaf(AT('a'))),
                                    Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                                ),
                                Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
                            ),
                            Tree.InnerNode(me.R1, Tree.Leaf(EPSILON))
                        ),
                        Tree.Leaf(me.RPAREN)
                    ),
                    Tree.InnerNode(me.C, Tree.Leaf(EPSILON))
                ),
                Tree.InnerNode(me.S1, Tree.Leaf(EPSILON))
            ),
            Tree.InnerNode(me.R1, Tree.Leaf(EPSILON))
        )
    )

    Test.CorrectnessTest("ParsesLongConcatenation", "abcdef")
    Test.CorrectnessTest("ParsesLongChoice", "a|b|c|d|e|f")
    Test.CorrectnessTest("ParsesLongKleene", "a*****")
    Test.CorrectnessTest("ParsesLongParenthesis", "(((((a)))))")

    Test.CorrectnessTest("ParsesComplicated (1)", "a|b*|c**|(a*|b**|(c*|d)**)*ab")
    Test.CorrectnessTest("ParsesComplicated (2)", "(z(y(x*)|(y|z*))*|(x|y)|z*)**")

    Test.ParseExceptionTest("LexerAlphabet", "abcDef")
    Test.ParseExceptionTest("LexerDigits", "abcd3f")
    Test.ParseExceptionTest("LexerSymbols", "^bcdef")

    Test.ParseExceptionTest("StartsWithChoice", "|abcd")
    Test.ParseExceptionTest("StartsWithChoiceEmpty", "|")
    Test.ParseExceptionTest("StartsWithAsterisk", "*abcd")
    Test.ParseExceptionTest("StartsWithAsteriskEmpty", "*")
    Test.ParseExceptionTest("StartsWithRightParenthesis", ")abc(d)")

    Test.ParseExceptionTest("EndsWithChoice", "a|b|c|")
    Test.ParseExceptionTest("EndsWithOpeningParenthesis", "a(bc(")

    Test.ParseExceptionTest("Empty", "")
    Test.ParseExceptionTest("EmptyChoice", "a||b")
    Test.ParseExceptionTest("EmptyParenthesis", "a()b")

    Test.ParseExceptionTest("UnmatchedOpeningParenthesis", "a(bc(d)e")
    Test.ParseExceptionTest("UnmatchedClosingParenthesis", "x(abc)d)e")
    Test.ParseExceptionTest("AsteriskAfterLeftParenthesis", "a(*b)")
    Test.ParseExceptionTest("AsteriskAfterChoice", "a|b|*")

    Test.CorrectnessTest("NoNumberNoAsterisk", "(x)")

    Test.CorrectnessTest("NumberOnly", "(b)a1")
    Test.CorrectnessTest("NumberOnlyLong", "(b)a12345")
    Test.CorrectnessTest("NumberOnlyLongToParenthesis", "(a)12345")
    Test.CorrectnessTest("AsteriskOnly", "(b)a*")
    Test.CorrectnessTest("AsteriskOnlyLong", "(b)a****")
    Test.CorrectnessTest("AsteriskOnlyLongToParenthesis", "(a)****")

    Test.CorrectnessTest("NumberAndAsterisk", "a12*")
    Test.CorrectnessTest("NumberAndAsteriskLong", "a12***")

    Test.ParseExceptionTest("AsteriskAndNumber", "a*12")
    Test.ParseExceptionTest("AsteriskWithNoAtom", "*d12")
    Test.ParseExceptionTest("NumberWithNoAtom", "12d*")

    Test.runAll()

}