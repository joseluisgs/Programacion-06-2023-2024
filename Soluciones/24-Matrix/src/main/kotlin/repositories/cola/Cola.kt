package dev.joseluisgs.repositories.cola

interface Cola<T> {
    fun queue(elem: T)

    fun dequeue(): T

    fun isEmpty(): Boolean

    fun size(): Int

    fun first(): T

    operator fun get(index: Int): T
}
