package regex.test

import grammar.token.Token
import structure.ASTNode

@Suppress("DuplicatedCode")
fun main() {

    val me = Test.me

    Test.CorrectnessTest(
        "CorrectnessTreeLetter", "a",
        ASTNode.InnerNode(
            me.R0,
            ASTNode.InnerNode(
                me.S0,
                ASTNode.InnerNode(
                    me.T,
                    ASTNode.InnerNode(me.A, ASTNode.TerminalNode(me.ALPHA.instantiate("a"))),
                    ASTNode.InnerNode(me.N, ASTNode.TerminalNode(Token.UniqueToken.EPSILON)),
                    ASTNode.InnerNode(me.C, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                ),
                ASTNode.InnerNode(me.S1, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
            ),
            ASTNode.InnerNode(me.R1, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeChoice", "a|b",
        ASTNode.InnerNode(
            me.R0,
            ASTNode.InnerNode(
                me.S0,
                ASTNode.InnerNode(
                    me.T,
                    ASTNode.InnerNode(me.A, ASTNode.TerminalNode(me.ALPHA.instantiate("a"))),
                    ASTNode.InnerNode(me.N, ASTNode.TerminalNode(Token.UniqueToken.EPSILON)),
                    ASTNode.InnerNode(me.C, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                ),
                ASTNode.InnerNode(me.S1, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
            ),
            ASTNode.InnerNode(
                me.R1,
                ASTNode.TerminalNode(me.CHOICE),
                ASTNode.InnerNode(
                    me.R0,
                    ASTNode.InnerNode(
                        me.S0,
                        ASTNode.InnerNode(
                            me.T,
                            ASTNode.InnerNode(me.A, ASTNode.TerminalNode(me.ALPHA.instantiate("b"))),
                            ASTNode.InnerNode(me.N, ASTNode.TerminalNode(Token.UniqueToken.EPSILON)),
                            ASTNode.InnerNode(me.C, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                        ),
                        ASTNode.InnerNode(me.S1, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                    ),
                    ASTNode.InnerNode(me.R1, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                )
            )
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeNumber", "a5",
        ASTNode.InnerNode(
            me.R0,
            ASTNode.InnerNode(
                me.S0,
                ASTNode.InnerNode(
                    me.T,
                    ASTNode.InnerNode(me.A, ASTNode.TerminalNode(me.ALPHA.instantiate("a"))),
                    ASTNode.InnerNode(me.N, ASTNode.TerminalNode(me.NUMBER.instantiate("5"))),
                    ASTNode.InnerNode(me.C, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                ),
                ASTNode.InnerNode(me.S1, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
            ),
            ASTNode.InnerNode(me.R1, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeKleene", "a*",
        ASTNode.InnerNode(
            me.R0,
            ASTNode.InnerNode(
                me.S0,
                ASTNode.InnerNode(
                    me.T,
                    ASTNode.InnerNode(me.A, ASTNode.TerminalNode(me.ALPHA.instantiate("a"))),
                    ASTNode.InnerNode(me.N, ASTNode.TerminalNode(Token.UniqueToken.EPSILON)),
                    ASTNode.InnerNode(
                        me.C,
                        ASTNode.TerminalNode(me.KLEENE),
                        ASTNode.InnerNode(me.C, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                    )
                ),
                ASTNode.InnerNode(me.S1, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
            ),
            ASTNode.InnerNode(me.R1, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeConcatenate", "ab",
        ASTNode.InnerNode(
            me.R0,
            ASTNode.InnerNode(
                me.S0,
                ASTNode.InnerNode(
                    me.T,
                    ASTNode.InnerNode(me.A, ASTNode.TerminalNode(me.ALPHA.instantiate("a"))),
                    ASTNode.InnerNode(me.N, ASTNode.TerminalNode(Token.UniqueToken.EPSILON)),
                    ASTNode.InnerNode(me.C, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                ),
                ASTNode.InnerNode(
                    me.S1,
                    ASTNode.InnerNode(
                        me.S0,
                        ASTNode.InnerNode(
                            me.T,
                            ASTNode.InnerNode(me.A, ASTNode.TerminalNode(me.ALPHA.instantiate("b"))),
                            ASTNode.InnerNode(me.N, ASTNode.TerminalNode(Token.UniqueToken.EPSILON)),
                            ASTNode.InnerNode(me.C, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                        ),
                        ASTNode.InnerNode(me.S1, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                    )
                )
            ),
            ASTNode.InnerNode(me.R1, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeParenthesis", "(a)", ASTNode.InnerNode(
            me.R0,
            ASTNode.InnerNode(
                me.S0,
                ASTNode.InnerNode(
                    me.T,
                    ASTNode.InnerNode(
                        me.A,
                        ASTNode.TerminalNode(me.LPAREN),
                        ASTNode.InnerNode(
                            me.R0,
                            ASTNode.InnerNode(
                                me.S0,
                                ASTNode.InnerNode(
                                    me.T,
                                    ASTNode.InnerNode(me.A, ASTNode.TerminalNode(me.ALPHA.instantiate("a"))),
                                    ASTNode.InnerNode(me.N, ASTNode.TerminalNode(Token.UniqueToken.EPSILON)),
                                    ASTNode.InnerNode(me.C, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                                ),
                                ASTNode.InnerNode(me.S1, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                            ),
                            ASTNode.InnerNode(me.R1, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                        ),
                        ASTNode.TerminalNode(me.RPAREN)
                    ),
                    ASTNode.InnerNode(me.N, ASTNode.TerminalNode(Token.UniqueToken.EPSILON)),
                    ASTNode.InnerNode(me.C, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                ),
                ASTNode.InnerNode(me.S1, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
            ),
            ASTNode.InnerNode(me.R1, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
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