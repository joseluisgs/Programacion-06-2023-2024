package dev.joseluisgs.repositories.crud

interface CrudRepository<T, ID> {
    fun findAll(): List<T>
    fun findById(id: ID): T?
    fun save(entity: T): T
    fun update(id: ID, entity: T): T?
    fun deleteById(id: ID): T?
    fun deleteAll()
}