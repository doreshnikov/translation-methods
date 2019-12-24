package utils

@Suppress("LeakingThis")
abstract class Factory<K, V> {

    companion object {
        val factories = ArrayList<Factory<*, *>>()

        fun resetAll() {
            factories.forEach { it.factory.clear() }
        }
    }

    init {
        factories.add(this)
    }

    protected val factory = HashMap<K, V>()

    abstract fun create(id: K): V

    operator fun invoke(id: K): V {
        return factory.getOrPut(id) { create(id) }
    }

}
