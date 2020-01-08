package structure

import grammar.token.Token

interface Visitor {

    fun <T : Token> collect(root: ASTNode<T>) : String

    fun <T : Token> visit(node: ASTNode<T>) : String

}