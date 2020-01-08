package regex.test

import grammar.token.Token
import regex.RegexDescription
import structure.ASTNode

@Suppress("DuplicatedCode")
fun main() {

    Test.CorrectnessTest(
        "CorrectnessTreeLetter", "a",
        ASTNode.InnerNode(
            RegexDescription.regex,
            ASTNode.InnerNode(
                RegexDescription.sequence,
                ASTNode.InnerNode(
                    RegexDescription.term,
                    ASTNode.InnerNode(RegexDescription.atom, ASTNode.TerminalNode(RegexDescription.ALPHA.instantiate("a"))),
                    ASTNode.InnerNode(RegexDescription.numberState, ASTNode.TerminalNode(Token.UniqueToken.EPSILON)),
                    ASTNode.InnerNode(RegexDescription.closure, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                ),
                ASTNode.InnerNode(RegexDescription.sequencePlus, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
            ),
            ASTNode.InnerNode(RegexDescription.regexPlus, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeChoice", "a|b",
        ASTNode.InnerNode(
            RegexDescription.regex,
            ASTNode.InnerNode(
                RegexDescription.sequence,
                ASTNode.InnerNode(
                    RegexDescription.term,
                    ASTNode.InnerNode(RegexDescription.atom, ASTNode.TerminalNode(RegexDescription.ALPHA.instantiate("a"))),
                    ASTNode.InnerNode(RegexDescription.numberState, ASTNode.TerminalNode(Token.UniqueToken.EPSILON)),
                    ASTNode.InnerNode(RegexDescription.closure, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                ),
                ASTNode.InnerNode(RegexDescription.sequencePlus, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
            ),
            ASTNode.InnerNode(
                RegexDescription.regexPlus,
                ASTNode.TerminalNode(RegexDescription.CHOICE),
                ASTNode.InnerNode(
                    RegexDescription.regex,
                    ASTNode.InnerNode(
                        RegexDescription.sequence,
                        ASTNode.InnerNode(
                            RegexDescription.term,
                            ASTNode.InnerNode(RegexDescription.atom, ASTNode.TerminalNode(RegexDescription.ALPHA.instantiate("b"))),
                            ASTNode.InnerNode(RegexDescription.numberState, ASTNode.TerminalNode(Token.UniqueToken.EPSILON)),
                            ASTNode.InnerNode(RegexDescription.closure, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                        ),
                        ASTNode.InnerNode(RegexDescription.sequencePlus, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                    ),
                    ASTNode.InnerNode(RegexDescription.regexPlus, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                )
            )
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeNumber", "a5",
        ASTNode.InnerNode(
            RegexDescription.regex,
            ASTNode.InnerNode(
                RegexDescription.sequence,
                ASTNode.InnerNode(
                    RegexDescription.term,
                    ASTNode.InnerNode(RegexDescription.atom, ASTNode.TerminalNode(RegexDescription.ALPHA.instantiate("a"))),
                    ASTNode.InnerNode(RegexDescription.numberState, ASTNode.TerminalNode(RegexDescription.NUMBER.instantiate("5"))),
                    ASTNode.InnerNode(RegexDescription.closure, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                ),
                ASTNode.InnerNode(RegexDescription.sequencePlus, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
            ),
            ASTNode.InnerNode(RegexDescription.regexPlus, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeKleene", "a*",
        ASTNode.InnerNode(
            RegexDescription.regex,
            ASTNode.InnerNode(
                RegexDescription.sequence,
                ASTNode.InnerNode(
                    RegexDescription.term,
                    ASTNode.InnerNode(RegexDescription.atom, ASTNode.TerminalNode(RegexDescription.ALPHA.instantiate("a"))),
                    ASTNode.InnerNode(RegexDescription.numberState, ASTNode.TerminalNode(Token.UniqueToken.EPSILON)),
                    ASTNode.InnerNode(
                        RegexDescription.closure,
                        ASTNode.TerminalNode(RegexDescription.KLEENE),
                        ASTNode.InnerNode(RegexDescription.closure, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                    )
                ),
                ASTNode.InnerNode(RegexDescription.sequencePlus, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
            ),
            ASTNode.InnerNode(RegexDescription.regexPlus, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeConcatenate", "ab",
        ASTNode.InnerNode(
            RegexDescription.regex,
            ASTNode.InnerNode(
                RegexDescription.sequence,
                ASTNode.InnerNode(
                    RegexDescription.term,
                    ASTNode.InnerNode(RegexDescription.atom, ASTNode.TerminalNode(RegexDescription.ALPHA.instantiate("a"))),
                    ASTNode.InnerNode(RegexDescription.numberState, ASTNode.TerminalNode(Token.UniqueToken.EPSILON)),
                    ASTNode.InnerNode(RegexDescription.closure, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                ),
                ASTNode.InnerNode(
                    RegexDescription.sequencePlus,
                    ASTNode.InnerNode(
                        RegexDescription.sequence,
                        ASTNode.InnerNode(
                            RegexDescription.term,
                            ASTNode.InnerNode(RegexDescription.atom, ASTNode.TerminalNode(RegexDescription.ALPHA.instantiate("b"))),
                            ASTNode.InnerNode(RegexDescription.numberState, ASTNode.TerminalNode(Token.UniqueToken.EPSILON)),
                            ASTNode.InnerNode(RegexDescription.closure, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                        ),
                        ASTNode.InnerNode(RegexDescription.sequencePlus, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                    )
                )
            ),
            ASTNode.InnerNode(RegexDescription.regexPlus, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
        )
    )
    Test.CorrectnessTest(
        "CorrectnessTreeParenthesis", "(a)", ASTNode.InnerNode(
            RegexDescription.regex,
            ASTNode.InnerNode(
                RegexDescription.sequence,
                ASTNode.InnerNode(
                    RegexDescription.term,
                    ASTNode.InnerNode(
                        RegexDescription.atom,
                        ASTNode.TerminalNode(RegexDescription.LPAREN),
                        ASTNode.InnerNode(
                            RegexDescription.regex,
                            ASTNode.InnerNode(
                                RegexDescription.sequence,
                                ASTNode.InnerNode(
                                    RegexDescription.term,
                                    ASTNode.InnerNode(RegexDescription.atom, ASTNode.TerminalNode(RegexDescription.ALPHA.instantiate("a"))),
                                    ASTNode.InnerNode(RegexDescription.numberState, ASTNode.TerminalNode(Token.UniqueToken.EPSILON)),
                                    ASTNode.InnerNode(RegexDescription.closure, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                                ),
                                ASTNode.InnerNode(RegexDescription.sequencePlus, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                            ),
                            ASTNode.InnerNode(RegexDescription.regexPlus, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                        ),
                        ASTNode.TerminalNode(RegexDescription.RPAREN)
                    ),
                    ASTNode.InnerNode(RegexDescription.numberState, ASTNode.TerminalNode(Token.UniqueToken.EPSILON)),
                    ASTNode.InnerNode(RegexDescription.closure, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
                ),
                ASTNode.InnerNode(RegexDescription.sequencePlus, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
            ),
            ASTNode.InnerNode(RegexDescription.regexPlus, ASTNode.TerminalNode(Token.UniqueToken.EPSILON))
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