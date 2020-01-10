package parse

import grammar.Expansion
import grammar.token.Token
import translate.codegen.helpers.GrammarInfo
import structure.ASTNode
import utils.Beautifier
import java.text.ParseException

class Parser(private val grammarInfo: GrammarInfo) {

    private val helper = Helper(grammarInfo.getGrammar())

    private fun parseExpandable(
        state: Token.StateToken,
        expansion: Expansion,
        lexer: Lexer
    ): ASTNode<Token.StateToken> {
        fun checked(token: Token): Token {
            if (!Token.isAcceptable(lexer.getToken(), token)) {
                if (lexer.getToken() == Token.UniqueToken.EOF) {
                    throw ParseException(
                        "Reached end of the line, expected ${Beautifier.escape(token.toString())}" +
                                " while parsing rule $state -> " + Beautifier.escape(expansion.toString()),
                        lexer.getIndex()
                    )
                }
                throw ParseException(
                    "Invalid token ${lexer.getToken()} met, expected ${Beautifier.escape(token.toString())}" +
                            " while parsing rule $state -> " + Beautifier.escape(expansion.toString()),
                    lexer.getIndex()
                )
            }
            return lexer.getToken()
        }

        val node = ASTNode.InnerNode(state, expansion)
        for (token in expansion) {
            node.addChild(
                when (token) {
                    is Token.StateToken -> parse(token, lexer)
                    else -> ASTNode.TerminalNode(checked(token)).also { lexer.nextToken() }
                }
            )
        }
        return node
    }

    private fun parseNullable(state: Token.StateToken, expansion: Expansion): ASTNode<Token.StateToken> {
        val node = ASTNode.InnerNode(state, expansion)
        for (token in expansion) {
            node.addChild(
                when (token) {
                    is Token.StateToken -> parseNullable(
                        token,
                        grammarInfo.getGrammar().RULES[state].expansions.first {
                            Token.isAcceptable(Token.UniqueToken.EPSILON, helper.FIRST(it))
                        }
                    )
                    is Token.UniqueToken.EPSILON -> ASTNode.TerminalNode(token)
                    else -> throw IllegalArgumentException(
                        "Unexpected token $token in expansion of $state ->* " +
                                "${Token.UniqueToken.EPSILON}"
                    )
                }
            )
        }
        return node
    }

    private fun parse(state: Token.StateToken, lexer: Lexer): ASTNode<Token.StateToken> {
        val rule = grammarInfo.getGrammar().RULES[state]

        val byFirst = rule.expansions.filter { Token.isAcceptable(lexer.getToken(), helper.FIRST(it)) }
        val byFollow = rule.expansions.filter { Token.isAcceptable(Token.UniqueToken.EPSILON, helper.FIRST(it)) }
        val isNullable = Token.isAcceptable(Token.UniqueToken.EPSILON, helper.FIRST[state]) &&
                Token.isAcceptable(lexer.getToken(), helper.FOLLOW[state])

        return when (val options = byFirst.size + (if (isNullable) byFollow.size else 0)) {
            0 -> throw ParseException(
                "Could not parse state $state: no expansions starting with ${lexer.getToken()}",
                lexer.getIndex()
            )
            1 -> {
                if (byFirst.isNotEmpty()) {
                    parseExpandable(state, byFirst.first(), lexer)
                } else {
                    parseNullable(state, byFollow.first())
                }
            }
            else -> throw ParseException(
                "Could not parse state $state: $options ambiguous expansions starting with ${lexer.getToken()}",
                lexer.getIndex()
            )
        }
    }

    fun parse(line: String): ASTNode<Token.StateToken> {
        Token.switchTo(grammarInfo.getName())
        val lexer = Lexer(line, grammarInfo)
        return parse(grammarInfo.getGrammar().getStart(), lexer).also {
            if (lexer.getToken() != Token.UniqueToken.EOF) {
                throw ParseException("Parsing ended and end of the line is not reached", lexer.getIndex())
            }
        }
    }

}