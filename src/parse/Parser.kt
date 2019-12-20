package parse

import grammar.Expansion
import grammar.Grammar
import grammar.Token
import structure.Tree
import java.text.ParseException

class Parser(val grammar: Grammar) {

    private val helper = Helper(grammar)

    private fun parseExpandable(state: Token.State, expansion: Expansion, lexer: Lexer): Tree {
        fun checked(token: Token): Token {
            if (token != lexer.getToken()) {
                if (lexer.getToken() == Token.END) {
                    throw ParseException(
                        "Reached end of the line, expected $token while parsing rule $state -> $expansion",
                        lexer.getIndex()
                    )
                }
                throw ParseException(
                    "Invalid token ${lexer.getToken()} met, expected $token while parsing rule $state -> $expansion",
                    lexer.getIndex()
                )
            }
            return token
        }

        val node = Tree.InnerNode(state)
        for (token in expansion) {
            node.add(
                when (token) {
                    is Token.State -> parse(token, lexer)
                    is Token.PredefinedToken -> Tree.Leaf(checked(token)).also { lexer.nextToken() }
                    is Token.AlphaToken -> Tree.Leaf(checked(token)).also { lexer.nextToken() }
                    else -> throw IllegalStateException("Unexpected token ${lexer.getToken()} received from lexer")
                }
            )
        }
        return node
    }

    private fun parseNullable(state: Token.State, expansion: Expansion): Tree {
        val node = Tree.InnerNode(state)
        for (token in expansion) {
            node.add(
                when (token) {
                    is Token.State -> parseNullable(
                        token,
                        grammar.RULES[state].expansions.first { Token.EPSILON in helper.FIRST(it) }
                    )
                    is Token.EPSILON -> Tree.Leaf(token)
                    else -> throw IllegalArgumentException("Unexpected token $token in expansion of $state ->* ${Token.EPSILON}")
                }
            )
        }
        return node
    }

    private fun parse(state: Token.State, lexer: Lexer): Tree {
        val rule = grammar.RULES[state]

        val byFirst = rule.expansions.filter { lexer.getToken() in helper.FIRST(it) }
        val byFollow = rule.expansions.filter { Token.EPSILON in helper.FIRST(it) }
        val isNullable = Token.EPSILON in helper.FIRST[state] && lexer.getToken() in helper.FOLLOW[state]

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

    fun parse(line: String): Tree {
        val lexer = Lexer(line)
        return parse(grammar.getStart(), lexer).also {
            if (lexer.getToken() != Token.END) {
                throw ParseException("Parsing ended and end of the line is not reached", lexer.getIndex())
            }
        }
    }

}