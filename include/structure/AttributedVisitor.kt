package structure

import grammar.token.Token

interface AttributedVisitor<R, A> {

    fun <T : Token> collect(root: ASTNode<T>) : R {
        return visit(root, null)
    }

    fun visit(node: ASTNode<out Token>, value: A?) : R

}