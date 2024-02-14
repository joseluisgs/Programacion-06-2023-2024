package dev.joseluisgs

import java.time.LocalDateTime

class PesonasListRepository : PersonasRepository {

    private val personas = mutableListOf<Persona>()

    override fun findAll(): List<Persona> {
        return personas.sortedBy { it.id }.toList()
    }

    override fun findById(id: Int): Persona? {
        return personas.find { it.id == id }
    }

    override fun findByNombre(nombre: String): List<Persona> {
        return personas.filter { it.nombre.contains(nombre, ignoreCase = true) }
    }

    override fun save(t: Persona): Persona {
        val personaToSave = t.copy(
            id = Persona.getNextId(),
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        personas.add(personaToSave)
        return personaToSave
    }

    override fun update(id: Int, t: Persona): Persona? {
        val index = personas.indexOfFirst { it.id == id }
        if (index != -1) {
            val personaToUpdate = t.copy(
                id = id,
                nombre = t.nombre,
                edad = t.edad,
                createdAt = personas[index].createdAt,
                updatedAt = LocalDateTime.now()
            )
            personas[index] = personaToUpdate
            return personaToUpdate
        }
        return null
    }

    override fun delete(id: Int): Persona? {
        return personas.find { it.id == id }?.let {
            personas.remove(it)
            return it
        }
    }
}