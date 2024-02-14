package dev.joseluisgs

interface PersonasRepository : CrudRepository<Persona, Int> {
    fun findByNombre(nombre: String): List<Persona>

}