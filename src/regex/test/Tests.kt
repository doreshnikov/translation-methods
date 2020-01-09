package regex.test

import grammar.token.Token
import gen.RegexGrammarInfo
import structure.ASTNode

@Suppress("DuplicatedCode")
fun main() {

    // @formatter:off
    Test.CorrectnessTest(
        "CorrectnessTreeLetter", "a",
        ASTNode.BaseInnerNode(
            RegexGrammarInfo.regex,
            ASTNode.BaseInnerNode(
                RegexGrammarInfo.sequence,
                ASTNode.BaseInnerNode(
                    RegexGrammarInfo.term,
                    ASTNode.BaseInnerNode(
                        RegexGrammarInfo.atom, ASTNode.BaseTerminalNode(
                            RegexGrammarInfo.ALPHA.instantiate("a"))),
                    ASTNode.BaseInnerNode(RegexGrammarInfo.number, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON)),
                    ASTNode.BaseInnerNode(RegexGrammarInfo.closure, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
                ),
                ASTNode.BaseInnerNode(RegexGrammarInfo.sequencePlus, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
            ),
            ASTNode.BaseInnerNode(RegexGrammarInfo.regexPlus, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeChoice", "a|b",
        ASTNode.BaseInnerNode(
            RegexGrammarInfo.regex,
            ASTNode.BaseInnerNode(
                RegexGrammarInfo.sequence,
                ASTNode.BaseInnerNode(
                    RegexGrammarInfo.term,
                    ASTNode.BaseInnerNode(
                        RegexGrammarInfo.atom, ASTNode.BaseTerminalNode(
                            RegexGrammarInfo.ALPHA.instantiate("a"))),
                    ASTNode.BaseInnerNode(RegexGrammarInfo.number, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON)),
                    ASTNode.BaseInnerNode(RegexGrammarInfo.closure, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
                ),
                ASTNode.BaseInnerNode(RegexGrammarInfo.sequencePlus, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
            ),
            ASTNode.BaseInnerNode(
                RegexGrammarInfo.regexPlus,
                ASTNode.BaseTerminalNode(RegexGrammarInfo.CHOICE),
                ASTNode.BaseInnerNode(
                    RegexGrammarInfo.regex,
                    ASTNode.BaseInnerNode(
                        RegexGrammarInfo.sequence,
                        ASTNode.BaseInnerNode(
                            RegexGrammarInfo.term,
                            ASTNode.BaseInnerNode(
                                RegexGrammarInfo.atom, ASTNode.BaseTerminalNode(
                                    RegexGrammarInfo.ALPHA.instantiate("b"))),
                            ASTNode.BaseInnerNode(RegexGrammarInfo.number, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON)),
                            ASTNode.BaseInnerNode(RegexGrammarInfo.closure, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
                        ),
                        ASTNode.BaseInnerNode(RegexGrammarInfo.sequencePlus, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
                    ),
                    ASTNode.BaseInnerNode(RegexGrammarInfo.regexPlus, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
                )
            )
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeNumber", "a5",
        ASTNode.BaseInnerNode(
            RegexGrammarInfo.regex,
            ASTNode.BaseInnerNode(
                RegexGrammarInfo.sequence,
                ASTNode.BaseInnerNode(
                    RegexGrammarInfo.term,
                    ASTNode.BaseInnerNode(
                        RegexGrammarInfo.atom, ASTNode.BaseTerminalNode(
                            RegexGrammarInfo.ALPHA.instantiate("a"))),
                    ASTNode.BaseInnerNode(
                        RegexGrammarInfo.number, ASTNode.BaseTerminalNode(
                            RegexGrammarInfo.UINT.instantiate("5"))),
                    ASTNode.BaseInnerNode(RegexGrammarInfo.closure, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
                ),
                ASTNode.BaseInnerNode(RegexGrammarInfo.sequencePlus, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
            ),
            ASTNode.BaseInnerNode(RegexGrammarInfo.regexPlus, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeKleene", "a*",
        ASTNode.BaseInnerNode(
            RegexGrammarInfo.regex,
            ASTNode.BaseInnerNode(
                RegexGrammarInfo.sequence,
                ASTNode.BaseInnerNode(
                    RegexGrammarInfo.term,
                    ASTNode.BaseInnerNode(
                        RegexGrammarInfo.atom, ASTNode.BaseTerminalNode(
                            RegexGrammarInfo.ALPHA.instantiate("a"))),
                    ASTNode.BaseInnerNode(RegexGrammarInfo.number, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON)),
                    ASTNode.BaseInnerNode(
                        RegexGrammarInfo.closure,
                        ASTNode.BaseTerminalNode(RegexGrammarInfo.KLEENE),
                        ASTNode.BaseInnerNode(RegexGrammarInfo.closure, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
                    )
                ),
                ASTNode.BaseInnerNode(RegexGrammarInfo.sequencePlus, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
            ),
            ASTNode.BaseInnerNode(RegexGrammarInfo.regexPlus, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeConcatenate", "ab",
        ASTNode.BaseInnerNode(
            RegexGrammarInfo.regex,
            ASTNode.BaseInnerNode(
                RegexGrammarInfo.sequence,
                ASTNode.BaseInnerNode(
                    RegexGrammarInfo.term,
                    ASTNode.BaseInnerNode(
                        RegexGrammarInfo.atom, ASTNode.BaseTerminalNode(
                            RegexGrammarInfo.ALPHA.instantiate("a"))),
                    ASTNode.BaseInnerNode(RegexGrammarInfo.number, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON)),
                    ASTNode.BaseInnerNode(RegexGrammarInfo.closure, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
                ),
                ASTNode.BaseInnerNode(
                    RegexGrammarInfo.sequencePlus,
                    ASTNode.BaseInnerNode(
                        RegexGrammarInfo.sequence,
                        ASTNode.BaseInnerNode(
                            RegexGrammarInfo.term,
                            ASTNode.BaseInnerNode(
                                RegexGrammarInfo.atom, ASTNode.BaseTerminalNode(
                                    RegexGrammarInfo.ALPHA.instantiate("b"))),
                            ASTNode.BaseInnerNode(RegexGrammarInfo.number, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON)),
                            ASTNode.BaseInnerNode(RegexGrammarInfo.closure, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
                        ),
                        ASTNode.BaseInnerNode(RegexGrammarInfo.sequencePlus, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
                    )
                )
            ),
            ASTNode.BaseInnerNode(RegexGrammarInfo.regexPlus, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeParenthesis", "(a)", ASTNode.BaseInnerNode(
            RegexGrammarInfo.regex,
            ASTNode.BaseInnerNode(
                RegexGrammarInfo.sequence,
                ASTNode.BaseInnerNode(
                    RegexGrammarInfo.term,
                    ASTNode.BaseInnerNode(
                        RegexGrammarInfo.atom,
                        ASTNode.BaseTerminalNode(RegexGrammarInfo.LPAREN),
                        ASTNode.BaseInnerNode(
                            RegexGrammarInfo.regex,
                            ASTNode.BaseInnerNode(
                                RegexGrammarInfo.sequence,
                                ASTNode.BaseInnerNode(
                                    RegexGrammarInfo.term,
                                    ASTNode.BaseInnerNode(
                                        RegexGrammarInfo.atom, ASTNode.BaseTerminalNode(
                                            RegexGrammarInfo.ALPHA.instantiate("a"))),
                                    ASTNode.BaseInnerNode(RegexGrammarInfo.number, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON)),
                                    ASTNode.BaseInnerNode(RegexGrammarInfo.closure, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
                                ),
                                ASTNode.BaseInnerNode(RegexGrammarInfo.sequencePlus, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
                            ),
                            ASTNode.BaseInnerNode(RegexGrammarInfo.regexPlus, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
                        ),
                        ASTNode.BaseTerminalNode(RegexGrammarInfo.RPAREN)
                    ),
                    ASTNode.BaseInnerNode(RegexGrammarInfo.number, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON)),
                    ASTNode.BaseInnerNode(RegexGrammarInfo.closure, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
                ),
                ASTNode.BaseInnerNode(RegexGrammarInfo.sequencePlus, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
            ),
            ASTNode.BaseInnerNode(RegexGrammarInfo.regexPlus, ASTNode.BaseTerminalNode(Token.UniqueToken.EPSILON))
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