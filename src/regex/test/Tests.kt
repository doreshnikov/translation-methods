package regex.test

import regex.RegexGrammarInfo
import grammar.token.Token
import structure.ASTNode

@Suppress("DuplicatedCode")
fun main() {

    // @formatter:off
    Test.CorrectnessTest(
        "CorrectnessTreeLetter", "a",
        ASTNode.InnerNode(
            RegexGrammarInfo.regex,
            ASTNode.InnerNode(
                RegexGrammarInfo.sequence,
                ASTNode.InnerNode(
                    RegexGrammarInfo.term,
                    ASTNode.InnerNode(
                        RegexGrammarInfo.atom, ASTNode.TerminalNode(
                            RegexGrammarInfo.ALPHA.instantiate("a"))),
                    ASTNode.InnerNode(RegexGrammarInfo.number, ASTNode.TerminalNode(Token.UniqueToken.EPSILON)),
                    ASTNode.InnerNode(RegexGrammarInfo.closure, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                ),
                ASTNode.InnerNode(RegexGrammarInfo.sequencePlus, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
            ),
            ASTNode.InnerNode(RegexGrammarInfo.regexPlus, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeChoice", "a|b",
        ASTNode.InnerNode(
            RegexGrammarInfo.regex,
            ASTNode.InnerNode(
                RegexGrammarInfo.sequence,
                ASTNode.InnerNode(
                    RegexGrammarInfo.term,
                    ASTNode.InnerNode(
                        RegexGrammarInfo.atom, ASTNode.TerminalNode(
                            RegexGrammarInfo.ALPHA.instantiate("a"))),
                    ASTNode.InnerNode(RegexGrammarInfo.number, ASTNode.TerminalNode(Token.UniqueToken.EPSILON)),
                    ASTNode.InnerNode(RegexGrammarInfo.closure, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                ),
                ASTNode.InnerNode(RegexGrammarInfo.sequencePlus, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
            ),
            ASTNode.InnerNode(
                RegexGrammarInfo.regexPlus,
                ASTNode.TerminalNode(RegexGrammarInfo.CHOICE),
                ASTNode.InnerNode(
                    RegexGrammarInfo.regex,
                    ASTNode.InnerNode(
                        RegexGrammarInfo.sequence,
                        ASTNode.InnerNode(
                            RegexGrammarInfo.term,
                            ASTNode.InnerNode(
                                RegexGrammarInfo.atom, ASTNode.TerminalNode(
                                    RegexGrammarInfo.ALPHA.instantiate("b"))),
                            ASTNode.InnerNode(RegexGrammarInfo.number, ASTNode.TerminalNode(Token.UniqueToken.EPSILON)),
                            ASTNode.InnerNode(RegexGrammarInfo.closure, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                        ),
                        ASTNode.InnerNode(RegexGrammarInfo.sequencePlus, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                    ),
                    ASTNode.InnerNode(RegexGrammarInfo.regexPlus, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                )
            )
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeNumber", "a5",
        ASTNode.InnerNode(
            RegexGrammarInfo.regex,
            ASTNode.InnerNode(
                RegexGrammarInfo.sequence,
                ASTNode.InnerNode(
                    RegexGrammarInfo.term,
                    ASTNode.InnerNode(
                        RegexGrammarInfo.atom, ASTNode.TerminalNode(
                            RegexGrammarInfo.ALPHA.instantiate("a"))),
                    ASTNode.InnerNode(
                        RegexGrammarInfo.number, ASTNode.TerminalNode(
                            RegexGrammarInfo.UINT.instantiate("5"))),
                    ASTNode.InnerNode(RegexGrammarInfo.closure, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                ),
                ASTNode.InnerNode(RegexGrammarInfo.sequencePlus, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
            ),
            ASTNode.InnerNode(RegexGrammarInfo.regexPlus, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeKleene", "a*",
        ASTNode.InnerNode(
            RegexGrammarInfo.regex,
            ASTNode.InnerNode(
                RegexGrammarInfo.sequence,
                ASTNode.InnerNode(
                    RegexGrammarInfo.term,
                    ASTNode.InnerNode(
                        RegexGrammarInfo.atom, ASTNode.TerminalNode(
                            RegexGrammarInfo.ALPHA.instantiate("a"))),
                    ASTNode.InnerNode(RegexGrammarInfo.number, ASTNode.TerminalNode(Token.UniqueToken.EPSILON)),
                    ASTNode.InnerNode(
                        RegexGrammarInfo.closure,
                        ASTNode.TerminalNode(RegexGrammarInfo.KLEENE),
                        ASTNode.InnerNode(RegexGrammarInfo.closure, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                    )
                ),
                ASTNode.InnerNode(RegexGrammarInfo.sequencePlus, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
            ),
            ASTNode.InnerNode(RegexGrammarInfo.regexPlus, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeConcatenate", "ab",
        ASTNode.InnerNode(
            RegexGrammarInfo.regex,
            ASTNode.InnerNode(
                RegexGrammarInfo.sequence,
                ASTNode.InnerNode(
                    RegexGrammarInfo.term,
                    ASTNode.InnerNode(
                        RegexGrammarInfo.atom, ASTNode.TerminalNode(
                            RegexGrammarInfo.ALPHA.instantiate("a"))),
                    ASTNode.InnerNode(RegexGrammarInfo.number, ASTNode.TerminalNode(Token.UniqueToken.EPSILON)),
                    ASTNode.InnerNode(RegexGrammarInfo.closure, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                ),
                ASTNode.InnerNode(
                    RegexGrammarInfo.sequencePlus,
                    ASTNode.InnerNode(
                        RegexGrammarInfo.sequence,
                        ASTNode.InnerNode(
                            RegexGrammarInfo.term,
                            ASTNode.InnerNode(
                                RegexGrammarInfo.atom, ASTNode.TerminalNode(
                                    RegexGrammarInfo.ALPHA.instantiate("b"))),
                            ASTNode.InnerNode(RegexGrammarInfo.number, ASTNode.TerminalNode(Token.UniqueToken.EPSILON)),
                            ASTNode.InnerNode(RegexGrammarInfo.closure, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                        ),
                        ASTNode.InnerNode(RegexGrammarInfo.sequencePlus, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                    )
                )
            ),
            ASTNode.InnerNode(RegexGrammarInfo.regexPlus, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeParenthesis", "(a)", ASTNode.InnerNode(
            RegexGrammarInfo.regex,
            ASTNode.InnerNode(
                RegexGrammarInfo.sequence,
                ASTNode.InnerNode(
                    RegexGrammarInfo.term,
                    ASTNode.InnerNode(
                        RegexGrammarInfo.atom,
                        ASTNode.TerminalNode(RegexGrammarInfo.LPAREN),
                        ASTNode.InnerNode(
                            RegexGrammarInfo.regex,
                            ASTNode.InnerNode(
                                RegexGrammarInfo.sequence,
                                ASTNode.InnerNode(
                                    RegexGrammarInfo.term,
                                    ASTNode.InnerNode(
                                        RegexGrammarInfo.atom, ASTNode.TerminalNode(
                                            RegexGrammarInfo.ALPHA.instantiate("a"))),
                                    ASTNode.InnerNode(RegexGrammarInfo.number, ASTNode.TerminalNode(Token.UniqueToken.EPSILON)),
                                    ASTNode.InnerNode(RegexGrammarInfo.closure, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                                ),
                                ASTNode.InnerNode(RegexGrammarInfo.sequencePlus, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                            ),
                            ASTNode.InnerNode(RegexGrammarInfo.regexPlus, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                        ),
                        ASTNode.TerminalNode(RegexGrammarInfo.RPAREN)
                    ),
                    ASTNode.InnerNode(RegexGrammarInfo.number, ASTNode.TerminalNode(Token.UniqueToken.EPSILON)),
                    ASTNode.InnerNode(RegexGrammarInfo.closure, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                ),
                ASTNode.InnerNode(RegexGrammarInfo.sequencePlus, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
            ),
            ASTNode.InnerNode(RegexGrammarInfo.regexPlus, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
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