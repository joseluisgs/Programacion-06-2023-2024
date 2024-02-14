package dev.joseluisgs

import java.time.LocalDateTime

data class Persona(
    val id: Int = 0,
    val nombre: String,
    val edad: Int,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {
    companion object {
        private var nextId = 0
        fun getNextId() = nextId++
    }
}
