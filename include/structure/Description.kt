package structure

import grammar.Grammar
import grammar.token.Token

interface Description {

    fun getGrammar(): Grammar

    fun getSkippedTokens(): Set<Token>

    fun getName(): String

    fun getAll(): List<Token>

    fun check() {
        getAll().forEach { it.getString() }
    }

}