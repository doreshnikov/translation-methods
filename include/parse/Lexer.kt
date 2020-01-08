package parse

import grammar.token.Restricted
import grammar.token.Token
import structure.Description
import java.text.ParseException

class Lexer(private val input: String, private val description: Description) :
    Restricted by Restricted.Symbolic + Restricted.Eof {

    private var offset = 0
    private var token: Token

    init {
        token = nextToken()
    }

    fun getIndex(): Int {
        return offset
    }

    fun getToken(): Token {
        return token
    }

    fun nextToken(): Token {
        for (grammarToken in Token.REGISTERED.all()) {
            val item = grammarToken.consume(input, offset) ?: continue
            offset += item.length
            when (grammarToken) {
                is Token.VariantToken.Instantiable -> grammarToken.instantiate(item)
                else -> grammarToken
            }.also { token = pass(it) }

            return if (Token.isAcceptable(token, description.getSkippedTokens())) nextToken() else token
        }
        throw ParseException("No valid tokens found in input on position $offset", offset)
    }

}