/**
This code is generated by [translate.codegen.WalkerBaseBuilder] deriving from base class [translate.meta.MetaVisitorBase] 
generated by [translate.codegen.VisitorBaseBuilder]
basing on grammar description [calculator.CalculatorGrammarInfo] derived from [translate.codegen.helpers.GrammarInfo]
*/

package calculator

import java.io.File
import parse.Parser
import calculator.CalculatorGrammarInfo

open class CalculatorData(val left: Int = 0) {

    companion object Macro {
        fun toInt(value: String): Int {
            return value.toInt()
        }
        fun factorial(value: Int, cnt: Int): Int {
            fun fact(n: Int): Int {
                return if (n == 0) 1 else n * fact(n - 1)
            }
            return if (cnt == 0) value else factorial(fact(value), cnt - 1)
        }
    }
    
    var text: String = ""

	var value: Int = 0

    override fun toString(): String {
        return "left:\n${left}\nvalue:\n${value}"
    }
    
}
