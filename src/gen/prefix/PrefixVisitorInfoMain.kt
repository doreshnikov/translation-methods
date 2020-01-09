
package gen.prefix

import java.io.File

import grammar.token.Token
import grammar.Expansion
import utils.Beautifier

import structure.ASTNode

class PrefixVisitorInfoData() {


}
    
class PrefixVisitorInfoTerminalNode<T : Token>(token: T) : 
    ASTNode.TerminalNode<T>(token) {
    val data = PrefixVisitorInfoData()
}
    
class PrefixVisitorInfoInnerNode<T : Token>(token: T, expansion: Expansion) : 
    ASTNode.InnerNode<T>(token, expansion) {
    val data = PrefixVisitorInfoData()    
}

fun main() {
    File("C:\\Users\\jetbrains\\IdeaProjects\\translation-methods\\src\\gen\\prefix\\${PrefixVisitorInfo.className}.kt").bufferedWriter().use { out ->
        out.write(PrefixVisitorInfo.getAll())
    }
}
