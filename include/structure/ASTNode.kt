package structure

import grammar.Expansion
import grammar.token.Restricted
import grammar.token.Token

abstract class ASTNode<T : Token> private constructor(private val id: Int, internal val token: T) {

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

    abstract class TerminalNode<T : Token>(token: T) : ASTNode<T>(lastId, token),
        Restricted by Restricted.Symbolic + Restricted.Epsilon {
        override fun getToken(): T {
            return token
        }
    }

    abstract class InnerNode<T : Token>(token: T, private val expansion: Expansion) : ASTNode<T>(lastId, token),
        Restricted by Restricted.State {
        val children = mutableListOf<ASTNode<out Token>>()

        override fun getToken(): T {
            return token
        }

        fun getExpansion(): Expansion {
            return expansion
        }

        fun <R : Token> addChild(node: ASTNode<R>) {
            children.add(node)
        }

        @Suppress("UNCHECKED_CAST")
        fun <R : ASTNode<out Token>> getChild(i: Int): R {
            return children[i] as R
        }
    }

    class BaseTerminalNode<T : Token>(token: T) : TerminalNode<T>(token) {
        init {
            pass(token)
        }
    }

    class BaseInnerNode<T : Token>(token: T, expansion: Expansion) : InnerNode<T>(token, expansion) {
        init {
            pass(token)
        }

        constructor(token: T, vararg children_: ASTNode<out Token>) : this(token, Expansion()) {
            children.addAll(children_)
        }
    }

    fun checkIsSame(node: ASTNode<*>): Boolean {
        return true
    }

    override fun toString(): String {
        return "[$id]"
    }

}