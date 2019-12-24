package grammar.token

interface RepresentedBy<T : Token.RepresentationToken> {

    fun getRepresentation() : T

    class RepresentedByObject<T : Token.RepresentationToken>(private val value: T) :
        RepresentedBy<T> {
        override fun getRepresentation() : T {
            return value
        }
    }

}