package translate.codegen.info

import grammar.Grammar
import grammar.token.Token

interface GrammarInfo {

    fun getGrammar(): Grammar

    fun getSkippedTokens(): Set<Token>

    fun getName(): String

    fun getAll(): List<Token>

    fun check() {
        getAll().forEach { it.getString() }
    }

}