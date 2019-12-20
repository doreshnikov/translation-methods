package parse

import grammar.Token
import utils.TR
import utils.TRFollow
import utils.TRUniversal
import java.text.ParseException

class Lexer(private val input: String) : TR by TRUniversal + TRFollow {

    private var index = -1
    private var char: Char? = null
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
        nextChar()
        when (char) {
            null -> Token.END
            in Token.PredefinedToken -> Token.PredefinedToken[char!!]
            in 'a'..'z' -> Token.AlphaToken(char!!)
            else -> throw ParseException(
                "Expected predefined token or lowercase latin letter instead of '$char'",
                index
            )
        }.also { token = pass(it) }
        return token
    }

}