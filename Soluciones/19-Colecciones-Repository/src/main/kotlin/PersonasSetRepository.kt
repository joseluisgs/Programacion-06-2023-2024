package dev.joseluisgs

import java.time.LocalDateTime
import java.util.*

class PersonasSetRepository : PersonasRepository {
    // private val personas = mutableSetOf<Persona>()
    private val personas: TreeSet<Persona> = TreeSet<Persona> { p1, p2 -> p1.id.compareTo(p2.id) }

    override fun findAll(): List<Persona> {
        // return personas.sortedBy { it.id }.toList()
        return personas.toList()
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
        return personas.find { it.id == id }?.let {
            personas.remove(it)
            val personaToUpdate = t.copy(
                id = it.id,
                nombre = t.nombre,
                edad = t.edad,
                createdAt = it.createdAt,
                updatedAt = LocalDateTime.now()
            )
            personas.add(personaToUpdate)
            return personaToUpdate
        }
    }

    override fun delete(id: Int): Persona? {
        return personas.find { it.id == id }?.let {
            personas.remove(it)
            return it
        }
    }
}