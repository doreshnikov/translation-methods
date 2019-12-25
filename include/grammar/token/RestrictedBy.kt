package grammar.token

import kotlin.reflect.KClass

interface RestrictedBy {

    object General : Restriction("General", Token.SpecialToken::class)

    object Lexer : Restriction("Lexer", Token.AlphaToken::class, Token.NumberToken::class)
    object Representation : Restriction("Representation", Token.RepresentationToken::class)
    object State : Restriction("State", Token.State::class)

    object First : Restriction("First", Token.EPSILON::class)
    object Follow : Restriction("Follow", Token.END::class)

    fun pass(token: Token): Token

    open class Restriction(private val name: String, vararg clazz: KClass<*>) : RestrictedBy {

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