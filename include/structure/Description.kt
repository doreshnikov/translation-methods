package structure

import grammar.Grammar
import grammar.token.Token

interface Description {

    fun getGrammar(): Grammar

    fun getSkippedTokens(): Set<Token>

    fun check(vararg tokens: Token) {
        tokens.forEach { it.getName() }
    }

}