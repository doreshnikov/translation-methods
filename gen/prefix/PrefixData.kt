/**
This code is generated by [translate.codegen.WalkerBaseBuilder] deriving from base class [translate.meta.MetaVisitorBase] 
generated by [translate.codegen.VisitorBaseBuilder]
basing on grammar description [prefix.PrefixGrammarInfo] derived from [translate.codegen.helpers.GrammarInfo]
*/

package prefix

import java.io.File
import parse.Parser
import prefix.PrefixGrammarInfo

open class PrefixData(val depth: Int = 0) {

    companion object Macro {
        fun tab(depth : Int): String {
            return "\t".repeat(depth)
        }
    }
    
    var text: String = ""

	var view: String = ""
	val tab: String
		get() = PrefixData.Macro.tab(depth)

    override fun toString(): String {
        return "depth:\n${depth}\nview:\n${view}"
    }
    
}