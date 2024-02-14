package dev.joseluisgs

import java.time.LocalDateTime
import java.util.*

class PersonasMapRepository : PersonasRepository {
    // val personas: MutableMap<Int, Persona> = mutableMapOf()
    val personas: TreeMap<Int, Persona> = TreeMap<Int, Persona>()

    override fun findAll(): List<Persona> {
        //  return personas.toSortedMap().values.toList()
        // return personas.values.sortedBy { it.id }.toList()
        return personas.values.toList()
    }

    override fun findById(id: Int): Persona? {
        return personas[id]
    }

    override fun findByNombre(nombre: String): List<Persona> {
        return personas.values.filter { it.nombre.contains(nombre, ignoreCase = true) }
    }

    override fun save(t: Persona): Persona {
        val personaToSave = t.copy(
            id = Persona.getNextId(),
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        personas[personaToSave.id] = personaToSave
        return personaToSave
    }

    override fun update(id: Int, t: Persona): Persona? {
        return personas[id]?.let {
            val personaToUpdate = t.copy(
                id = it.id,
                createdAt = it.createdAt,
                updatedAt = LocalDateTime.now()
            )
            personas[id] = personaToUpdate
            return personaToUpdate
        }
    }

    override fun delete(id: Int): Persona? {
        return personas.remove(id)
    }
}