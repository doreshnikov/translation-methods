package translate.codegen.helpers

import grammar.Grammar
import grammar.token.Token

interface GrammarInfo {

    fun getGrammar(): Grammar

    fun getSkippedTokens(): Set<Token>

    fun getName(): String

    fun getDefinedTokens(): List<Token>

    fun check() {
        getDefinedTokens().forEach { it.getString() }
    }

}