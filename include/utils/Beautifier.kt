package utils

import grammar.token.Token

class Beautifier {

    companion object {
        fun println(any: Any) {
            kotlin.io.println(any).also { println() }
        }

        fun tokenFold(tokens: Collection<Token>, separator: String = ", "): String {
            val alphas = tokens.filterIsInstance<Token.AlphaToken>()
            return tokens.filter { it !is Token.AlphaToken }.sortedBy { it.toString() }.let {
                if (alphas.isNotEmpty()) {
                    it.plus(alphaTokenFold(alphas, separator))
                } else {
                    it
                }
            }.joinToString(separator)
        }

        fun alphaTokenFold(tokens: Collection<Token.AlphaToken>, separator: String = ", "): String {
            return tokens.sortedBy { it.data }.fold(arrayListOf<ArrayList<Token.AlphaToken>>(), { list, token ->
                list.also {
                    if (list.isNotEmpty() && list.last().last().data == token.data - 1) {
                        list.last().add(token)
                    } else {
                        list.add(arrayListOf(token))
                    }
                }
            }).joinToString(separator) { list ->
                if (list.size > 1)
                    "[${list.first()}..${list.last()}]"
                else
                    "${list[0]}"
            }
        }
    }

}