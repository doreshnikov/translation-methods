package structure

import grammar.token.Token

interface Walker<R> {

    fun <T : Token> collect(root: ASTNode<T>) : R {
        return visit(root)
    }

    fun visit(node: ASTNode<out Token>) : R

}