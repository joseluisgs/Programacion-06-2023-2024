package dev.joseluisgs.cache

import java.time.LocalDateTime

data class CacheItem<T>(
    val value: T,
    val updatedAt: LocalDateTime = LocalDateTime.now(),
) : Comparable<CacheItem<T>> {
    override fun compareTo(other: CacheItem<T>): Int {
        return this.updatedAt.compareTo(other.updatedAt)
    }

    override fun toString(): String {
        return "CacheItem(value=$value, updatedAt=$updatedAt)"
    }
}

class Cache<T, ID>(private val maxSize: Int = 3) {
    private val items: MutableMap<ID, CacheItem<T>> = mutableMapOf()

    fun get(key: ID): T? {
        return items[key]?.let { cacheItem ->
            // Actualiza la fecha de 'updatedAt' cuando se accede al elemento
            items[key] = cacheItem.copy(updatedAt = LocalDateTime.now())
            cacheItem.value
        }
    }

    fun put(key: ID, value: T) {
        // Si la clave ya existe, simplemente actualiza el valor y la fecha de actualización
        items[key]?.let {
            items[key] = it.copy(value = value, updatedAt = LocalDateTime.now())
        } ?: run {
            // Si no existe y el tamaño es mayor que el máximo, elimina el más antiguo
            if (items.size >= maxSize) {
                removeOldestItem()
            }
            // Agrega el nuevo elemento al caché
            items[key] = CacheItem(value)
        }
    }

    private fun removeOldestItem() {
        // Encuentra la clave del elemento más antiguo y elimínalo
        items.minByOrNull { it.value.updatedAt }?.key?.let { oldestKey ->
            items.remove(oldestKey)
        }
    }

    override fun toString(): String {
        // ordena los elementos por fecha de actualización
        val sortedItems = items.entries.sortedBy { it.value }
        return "Cache(items=$sortedItems)"
    }
}