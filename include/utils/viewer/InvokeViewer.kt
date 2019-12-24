package utils.viewer

interface InvokeViewer<K, V> {

    operator fun invoke(key: K): V

}