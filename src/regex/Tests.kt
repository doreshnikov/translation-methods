package regex

import grammar.token.Token
import structure.ASTNode
import test.Test

@Suppress("DuplicatedCode")
fun main() {

    Test.register(RegexGrammarInfo, null)

    // @formatter:off
    Test.ParseTest(
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
    Test.ParseTest(
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
    Test.ParseTest(
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
    Test.ParseTest(
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
    Test.ParseTest(
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
    Test.ParseTest(
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

    Test.ParseTest("ParsesLongConcatenation", "abcdef")
    Test.ParseTest("ParsesLongChoice", "a|b|c|d|e|f")
    Test.ParseTest("ParsesLongKleene", "a*****")
    Test.ParseTest("ParsesLongParenthesis", "(((((a)))))")

    Test.ParseTest("ParsesComplicated (1)", "a|b*|c**|(a*|b**|(c*|d)**)*ab")
    Test.ParseTest("ParsesComplicated (2)", "(z(y(x*)|(y|z*))*|(x|y)|z*)**")

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

    Test.ParseTest("NoNumberNoAsterisk", "(x)")

    Test.ParseTest("Number", "(b)a1")
    Test.ParseTest("NumberLong", "(b)a12345")
    Test.ParseTest("NumberLongToParenthesis", "(a)12345")
    Test.ParseTest("Asterisk", "(b)a*")
    Test.ParseTest("AsteriskLong", "(b)a****")
    Test.ParseTest("AsteriskLongToParenthesis", "(a)****")

    Test.ParseTest("NumberAndAsterisk", "a12*")
    Test.ParseTest("NumberAndAsteriskLong", "a12***")

    Test.ParseExceptionTest("AsteriskAndNumber", "a*12")
    Test.ParseExceptionTest("AsteriskWithNoAtom", "*d12")
    Test.ParseExceptionTest("NumberWithNoAtom", "12d*")

    Test.runAll()

}