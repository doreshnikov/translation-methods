
package gen.calculator

import java.io.File

import grammar.token.Token
import grammar.Expansion
import utils.Beautifier

import structure.ASTNode

class CalculatorVisitorInfoData() {


}
    
class CalculatorVisitorInfoTerminalNode<T : Token>(token: T) : 
    ASTNode.TerminalNode<T>(token) {
    val data = CalculatorVisitorInfoData()
}
    
class CalculatorVisitorInfoInnerNode<T : Token>(token: T, expansion: Expansion) : 
    ASTNode.InnerNode<T>(token, expansion) {
    val data = CalculatorVisitorInfoData()    
}

fun main() {
    File("C:\\Users\\jetbrains\\IdeaProjects\\translation-methods\\src\\gen\\calculator\\${CalculatorVisitorInfo.className}.kt").bufferedWriter().use { out ->
        out.write(CalculatorVisitorInfo.getAll())
    }
}
