package parse

import grammar.token.Token
import utils.TR
import utils.TRFollow
import utils.TRGeneral
import java.text.ParseException

class Lexer(private val input: String) : TR by TRGeneral + TRFollow {

    private var index = -1
    private var char: Char? = nextChar()
    private var token: Token

    init {
        token = nextToken()
    }

    private fun nextChar(): Char? {
        index++
        return input.getOrNull(index).also { char = it }
    }

    fun getIndex(): Int {
        return index
    }

    fun getToken(): Token {
        return token
    }

    fun nextToken(): Token {
        when (char) {
            null -> Token.END
            in Token.SpecialToken -> Token.SpecialToken[char!!].also { nextChar() }
            in 'a'..'z' -> Token.AlphaToken(char!!).also { nextChar() }
            in '0'..'9' -> {
                var number = 0
                while (char != null && char!! in '0'..'9') {
                    number = number * 10 + (char!! - '0')
                    nextChar()
                }
                Token.NumberToken(number)
            }
            else -> throw ParseException(
                "Expected predefined token or lowercase latin letter instead of '$char'",
                index
            )
        }.also { token = pass(it) }
        return token
    }

}