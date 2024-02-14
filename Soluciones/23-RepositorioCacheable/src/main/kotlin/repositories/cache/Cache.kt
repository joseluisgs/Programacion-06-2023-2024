package dev.joseluisgs.repositories.cache

interface Cache<T, ID> {
    fun put(key: ID, value: T)
    fun get(key: ID): T?
    fun remove(key: ID)
    fun clear()
}