package structure

import grammar.token.Restricted
import grammar.token.Token

abstract class ASTNode<T : Token> private constructor(private val id: Int) {

    companion object ID {
        var lastId = 0
            get() = field++
    }

    abstract fun getToken(): T

    open fun visit(): String {
        return id.toString()
    }

    fun <R> visit(visitor: Visitor<R>): R {
        return visitor.visit(this)
    }

    open class TerminalNode<T : Token>(private val token: T) : ASTNode<T>(lastId),
        Restricted by Restricted.Symbolic + Restricted.Epsilon {

        override fun getToken(): T {
            return token.also { pass(it) }
        }

    }

    open class InnerNode<T : Token>(private val token: T, vararg children_: ASTNode<out Token>) : ASTNode<T>(lastId),
        Restricted by Restricted.State {

        val children = mutableListOf<ASTNode<out Token>>().also { it.addAll(children_) }

        override fun getToken(): T {
            return token.also { pass(it) }
        }

        fun <R : Token> addChild(node: ASTNode<R>) {
            children.add(node)
        }

        @Suppress("UNCHECKED_CAST")
        fun <R : ASTNode<out Token>> getChild(i: Int): R {
            return children[i] as R
        }

    }

    fun checkIsSame(node: ASTNode<*>): Boolean {
        return true
    }

    override fun toString(): String {
        return "[$id]"
    }

}