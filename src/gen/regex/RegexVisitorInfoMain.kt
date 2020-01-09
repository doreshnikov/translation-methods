
package gen.regex

import java.io.File

import grammar.token.Token
import grammar.Expansion
import utils.Beautifier

import structure.ASTNode

class RegexVisitorInfoData() {


}
    
class RegexVisitorInfoTerminalNode<T : Token>(token: T) : 
    ASTNode.TerminalNode<T>(token) {
    val data = RegexVisitorInfoData()
}
    
class RegexVisitorInfoInnerNode<T : Token>(token: T, expansion: Expansion) : 
    ASTNode.InnerNode<T>(token, expansion) {
    val data = RegexVisitorInfoData()    
}

fun main() {
    File("C:\\Users\\jetbrains\\IdeaProjects\\translation-methods\\src\\gen\\${RegexVisitorInfo.className}.kt").bufferedWriter().use { out ->
        out.write(RegexVisitorInfo.getAll())
    }
}
