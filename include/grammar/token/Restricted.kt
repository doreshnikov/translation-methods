package grammar.token

import kotlin.reflect.KClass

interface Restricted {

    private object Data : Restriction("D", Token.DataToken::class)
    private object Variant : Restriction("V", Token.VariantToken::class)
    private object VariantInstance : Restriction("VI", Token.VariantToken.VariantInstanceToken::class)

    object State : Restriction("S", Token.StateToken::class)
    object Epsilon : Restriction("e", Token.UniqueToken.EPSILON::class)
    object Eof : Restriction("$", Token.UniqueToken.EOF::class)

    companion object {
        val Terminal: Restriction
            get() = Data + Variant + Epsilon
        val Symbolic: Restriction
            get() = Data + VariantInstance + Eof
    }

    fun pass(token: Token): Token

    open class Restriction(private val name: String, vararg clazz: KClass<*>) : Restricted {

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

        final override fun pass(token: Token): Token {
            if (check(token)) {
                return token
            }
            throw IllegalStateException("Unexpected token $token on a class restricted by '$name'")
        }

    }

}