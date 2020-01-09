package regex.test

import grammar.token.Token
import gen.RegexDescription
import structure.ASTNode

@Suppress("DuplicatedCode")
fun main() {

    // @formatter:off
    Test.CorrectnessTest(
        "CorrectnessTreeLetter", "a",
        ASTNode.BaseInnerNode(
            RegexDescription.regex,
            ASTNode.BaseInnerNode(
                RegexDescription.sequence,
                ASTNode.BaseInnerNode(
                    RegexDescription.term,
                    ASTNode.BaseInnerNode(
                        RegexDescription.atom, ASTNode.BaseTerminalNode(
                            RegexDescription.ALPHA.instantiate("a"))),
                    ASTNode.BaseInnerNode(RegexDescription.number, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON)),
                    ASTNode.BaseInnerNode(RegexDescription.closure, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
                ),
                ASTNode.BaseInnerNode(RegexDescription.sequencePlus, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
            ),
            ASTNode.BaseInnerNode(RegexDescription.regexPlus, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeChoice", "a|b",
        ASTNode.BaseInnerNode(
            RegexDescription.regex,
            ASTNode.BaseInnerNode(
                RegexDescription.sequence,
                ASTNode.BaseInnerNode(
                    RegexDescription.term,
                    ASTNode.BaseInnerNode(
                        RegexDescription.atom, ASTNode.BaseTerminalNode(
                            RegexDescription.ALPHA.instantiate("a"))),
                    ASTNode.BaseInnerNode(RegexDescription.number, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON)),
                    ASTNode.BaseInnerNode(RegexDescription.closure, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
                ),
                ASTNode.BaseInnerNode(RegexDescription.sequencePlus, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
            ),
            ASTNode.BaseInnerNode(
                RegexDescription.regexPlus,
                ASTNode.BaseTerminalNode(RegexDescription.CHOICE),
                ASTNode.BaseInnerNode(
                    RegexDescription.regex,
                    ASTNode.BaseInnerNode(
                        RegexDescription.sequence,
                        ASTNode.BaseInnerNode(
                            RegexDescription.term,
                            ASTNode.BaseInnerNode(
                                RegexDescription.atom, ASTNode.BaseTerminalNode(
                                    RegexDescription.ALPHA.instantiate("b"))),
                            ASTNode.BaseInnerNode(RegexDescription.number, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON)),
                            ASTNode.BaseInnerNode(RegexDescription.closure, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
                        ),
                        ASTNode.BaseInnerNode(RegexDescription.sequencePlus, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
                    ),
                    ASTNode.BaseInnerNode(RegexDescription.regexPlus, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
                )
            )
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeNumber", "a5",
        ASTNode.BaseInnerNode(
            RegexDescription.regex,
            ASTNode.BaseInnerNode(
                RegexDescription.sequence,
                ASTNode.BaseInnerNode(
                    RegexDescription.term,
                    ASTNode.BaseInnerNode(
                        RegexDescription.atom, ASTNode.BaseTerminalNode(
                            RegexDescription.ALPHA.instantiate("a"))),
                    ASTNode.BaseInnerNode(
                        RegexDescription.number, ASTNode.BaseTerminalNode(
                            RegexDescription.UINT.instantiate("5"))),
                    ASTNode.BaseInnerNode(RegexDescription.closure, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
                ),
                ASTNode.BaseInnerNode(RegexDescription.sequencePlus, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
            ),
            ASTNode.BaseInnerNode(RegexDescription.regexPlus, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeKleene", "a*",
        ASTNode.BaseInnerNode(
            RegexDescription.regex,
            ASTNode.BaseInnerNode(
                RegexDescription.sequence,
                ASTNode.BaseInnerNode(
                    RegexDescription.term,
                    ASTNode.BaseInnerNode(
                        RegexDescription.atom, ASTNode.BaseTerminalNode(
                            RegexDescription.ALPHA.instantiate("a"))),
                    ASTNode.BaseInnerNode(RegexDescription.number, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON)),
                    ASTNode.BaseInnerNode(
                        RegexDescription.closure,
                        ASTNode.BaseTerminalNode(RegexDescription.KLEENE),
                        ASTNode.BaseInnerNode(RegexDescription.closure, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
                    )
                ),
                ASTNode.BaseInnerNode(RegexDescription.sequencePlus, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
            ),
            ASTNode.BaseInnerNode(RegexDescription.regexPlus, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeConcatenate", "ab",
        ASTNode.BaseInnerNode(
            RegexDescription.regex,
            ASTNode.BaseInnerNode(
                RegexDescription.sequence,
                ASTNode.BaseInnerNode(
                    RegexDescription.term,
                    ASTNode.BaseInnerNode(
                        RegexDescription.atom, ASTNode.BaseTerminalNode(
                            RegexDescription.ALPHA.instantiate("a"))),
                    ASTNode.BaseInnerNode(RegexDescription.number, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON)),
                    ASTNode.BaseInnerNode(RegexDescription.closure, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
                ),
                ASTNode.BaseInnerNode(
                    RegexDescription.sequencePlus,
                    ASTNode.BaseInnerNode(
                        RegexDescription.sequence,
                        ASTNode.BaseInnerNode(
                            RegexDescription.term,
                            ASTNode.BaseInnerNode(
                                RegexDescription.atom, ASTNode.BaseTerminalNode(
                                    RegexDescription.ALPHA.instantiate("b"))),
                            ASTNode.BaseInnerNode(RegexDescription.number, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON)),
                            ASTNode.BaseInnerNode(RegexDescription.closure, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
                        ),
                        ASTNode.BaseInnerNode(RegexDescription.sequencePlus, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
                    )
                )
            ),
            ASTNode.BaseInnerNode(RegexDescription.regexPlus, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeParenthesis", "(a)", ASTNode.BaseInnerNode(
            RegexDescription.regex,
            ASTNode.BaseInnerNode(
                RegexDescription.sequence,
                ASTNode.BaseInnerNode(
                    RegexDescription.term,
                    ASTNode.BaseInnerNode(
                        RegexDescription.atom,
                        ASTNode.BaseTerminalNode(RegexDescription.LPAREN),
                        ASTNode.BaseInnerNode(
                            RegexDescription.regex,
                            ASTNode.BaseInnerNode(
                                RegexDescription.sequence,
                                ASTNode.BaseInnerNode(
                                    RegexDescription.term,
                                    ASTNode.BaseInnerNode(
                                        RegexDescription.atom, ASTNode.BaseTerminalNode(
                                            RegexDescription.ALPHA.instantiate("a"))),
                                    ASTNode.BaseInnerNode(RegexDescription.number, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON)),
                                    ASTNode.BaseInnerNode(RegexDescription.closure, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
                                ),
                                ASTNode.BaseInnerNode(RegexDescription.sequencePlus, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
                            ),
                            ASTNode.BaseInnerNode(RegexDescription.regexPlus, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
                        ),
                        ASTNode.BaseTerminalNode(RegexDescription.RPAREN)
                    ),
                    ASTNode.BaseInnerNode(RegexDescription.number, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON)),
                    ASTNode.BaseInnerNode(RegexDescription.closure, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
                ),
                ASTNode.BaseInnerNode(RegexDescription.sequencePlus, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
            ),
            ASTNode.BaseInnerNode(RegexDescription.regexPlus, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
        )
    )
    // @formatter:on

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