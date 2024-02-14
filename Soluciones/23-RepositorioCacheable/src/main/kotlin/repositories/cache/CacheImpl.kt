package dev.joseluisgs.repositories.cache

import org.lighthousegames.logging.logging

/**
 * Clase que representa un cache limitada a un número de elementos máximo
 * @param T Tipo de elementos que se almacenarán en el cache
 */

private val logger = logging()

class CacheImpl<T, ID>(private val maxElements: Int = 5) {
    private val elements = mutableMapOf<ID, T>()

    /**
     * Añade un elemento al cache
     * @param key Clave del elemento
     * @param value Valor del elemento
     */
    fun put(key: ID, value: T) {
        logger.debug { "Añadiendo elemento al cache con key: $key" }
        // Si el cache está lleno, eliminamos el primer elemento

        elements[key]?.let { elements.remove(key) }

        if (elements.size >= maxElements) {
            logger.debug { "Cache completo, eliminando el primer elemento" }
            elements.remove(elements.keys.first())
        }

        logger.debug { "Añadiendo elemento al cache" }
        elements[key] = value
    }

    /**
     * Obtiene un elemento del cache
     * @param key Clave del elemento
     * @return Valor del elemento
     */
    fun get(key: ID): T? {
        logger.debug { "Obteniendo elemento del cache con key: $key" }
        return elements[key]
    }

    /**
     * Elimina un elemento del cache
     * @param key Clave del elemento
     */
    fun remove(key: ID) {
        logger.debug { "Eliminando elemento del cache con key: $key" }
        elements.remove(key)
    }

    /**
     * Limpia el cache
     */
    fun clear() {
        elements.clear()
    }
}