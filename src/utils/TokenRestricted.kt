package utils

import grammar.Token
import kotlin.reflect.KClass

interface TokenRestricted {

    object Universal : Restriction("Universal", Token.PredefinedToken::class, Token.AlphaToken::class)
    object State : Restriction("State", Token.State::class)
    object First : Restriction("First", Token.EPSILON::class)
    object Follow : Restriction("Follow", Token.END::class)

    fun pass(token: Token): Token

    open class Restriction(private val name: String, vararg clazz: KClass<*>) : TokenRestricted {

        private val allowed = clazz.toSet()

        private fun check(token: Token): Boolean {
            return allowed.any { it.isInstance(token) }
        }

        operator fun plus(restriction: Restriction): Restriction {
            return Restriction(
                "$name+${restriction.name}",
                *(allowed.plus(restriction.allowed).toTypedArray())
            )
        }

        override fun pass(token: Token): Token {
            if (check(token)) {
                return token
            }
            throw IllegalStateException("Unexpected token $token on a class restricted by '$name'")
        }

    }

}