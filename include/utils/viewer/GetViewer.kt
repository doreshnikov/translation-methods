package utils.viewer

interface GetViewer<K, V> {

    fun all(): List<V> {
        throw NotImplementedError("Invalid getter configuration `all()`")
    }

    operator fun get(key: K): V

}
