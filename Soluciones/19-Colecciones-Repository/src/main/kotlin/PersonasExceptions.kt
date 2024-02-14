package dev.joseluisgs

sealed class PersonasExceptions(message: String) : Exception(message) {
    class PersonaNotFoundException(id: Int) : PersonasExceptions("Persona no encontrada, con id: $id")
    class PersonaSaveException(persona: Persona) : PersonasExceptions("Error al guardar la persona: $persona")
}