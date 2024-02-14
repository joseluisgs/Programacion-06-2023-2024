package dev.joseluisgs

import dev.joseluisgs.models.Persona
import dev.joseluisgs.repositories.personas.PersonasRepository

fun main() {
    val personasRepository = PersonasRepository()

    println("Consultamos todas las personas: ")
    personasRepository.getAll().forEach { persona ->
        println(persona)
    }

    println("Creamos una nueva persona: ")
    var personaToSaved = Persona(name = "Pepe", email = "pepe@gmail.com")
    var personaSaved = personasRepository.save(personaToSaved)
    println(personaSaved)

    println("Consultamos todas las personas: ")
    personasRepository.getAll().forEach { persona ->
        println(persona)
    }

    println("Consultamos una persona por su id: 1")
    personasRepository.getById(1)?.let { persona ->
        println(persona)
    } ?: println("No existe la persona")

    println("Consultamos una persona por su id: 99")
    personasRepository.getById(99)?.let { persona ->
        println(persona)
    } ?: println("No existe la persona")

    println("Creamos otra persona: ")
    personaToSaved = Persona(name = "Ana", email = "ana@gmail.com")
    personaSaved = personasRepository.save(personaToSaved)
    println(personaSaved)

    println("Consultamos todas las personas: ")
    personasRepository.getAll().forEach { persona ->
        println(persona)
    }

    println("Actualizamos una persona por su id: 1")
    val personaToUpdate = Persona(name = "Jose", email = "jose@gmail.com")
    personasRepository.update(1, personaToUpdate)?.let { persona ->
        println(persona)
    } ?: println("No existe la persona")

    println("Actualizamos una persona por su id: 99")
    personasRepository.update(99, personaToUpdate)?.let { persona ->
        println(persona)
    } ?: println("No existe la persona")

    println("Consultamos todas las personas: ")
    personasRepository.getAll().forEach { persona ->
        println(persona)
    }

    println("Borramos una persona por su id: 1")
    personasRepository.delete(1)?.let { persona ->
        println(persona)
    } ?: println("No existe la persona")

    println("Borramos una persona por su id: 99")
    personasRepository.delete(99)?.let { persona ->
        println(persona)
    } ?: println("No existe la persona")

    println("Consultamos todas las personas: ")
    personasRepository.getAll().forEach { persona ->
        println(persona)
    }
}