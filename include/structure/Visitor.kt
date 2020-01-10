package structure

import grammar.token.Token

interface Visitor<R, A> {

    fun <T : Token> collect(root: ASTNode<T>) : R

    fun visit(node: ASTNode<out Token>, value: A) : R

}