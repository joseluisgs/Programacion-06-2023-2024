package dev.joseluisgs.repositories.vehiculos

import dev.joseluisgs.models.Vehiculo
import org.lighthousegames.logging.logging
import java.time.LocalDateTime

/**
 * Implementación de nuestro repositorio de vehículos
 */
private val logger = logging()

class VehiculosRepositoryImpl : VehiculosRepository {
    private val items = mutableMapOf<String, Vehiculo>()

    /**
     * Busca un vehículo por su marca
     * @param marca Marca del vehículo
     * @return Lista de vehículos con esa marca
     */
    override fun findByMarca(marca: String): List<Vehiculo> {
        logger.debug { "Buscando vehículos por marca: $marca" }

        return items.values.filter { it.marca.lowercase() == marca.lowercase() }
    }

    /**
     * Busca un vehículo por su modelo
     * @param modelo Modelo del vehículo
     * @return Lista de vehículos con ese modelo
     */
    override fun findByAñoMatriculacion(añoMatriculacion: Int): List<Vehiculo> {
        logger.debug { "Buscando vehículos por fecha de matriculación: $añoMatriculacion" }

        return items.values.filter { it.fechaMatriculacion.year == añoMatriculacion }
    }

    /**
     * Busca un vehículo por su vendido
     * @param vendido Si está vendido o no
     * @return Lista de vehículos con ese estado
     */
    override fun findByVendido(vendido: Boolean): List<Vehiculo> {
        logger.debug { "Buscando vehículos por vendido: $vendido" }

        return items.values.filter { it.vendido == vendido }
    }

    /**
     * Busca un vehículo por su precio
     * @param precio Precio del vehículo
     * @return Lista de vehículos con ese precio
     */
    override fun findAll(): List<Vehiculo> {
        logger.debug { "Buscando todos los vehículos" }

        return items.values.toList()
    }

    /**
     * Busca un vehículo por su id
     * @param id Identificador del vehículo
     * @return Vehículo con ese id
     */
    override fun findById(id: String): Vehiculo? {
        logger.debug { "Buscando vehículo por id: $id" }

        return items[id]
    }

    /**
     * Busca un vehículo por su matricula
     * @param matricula Matricula del vehículo
     * @return Vehículo con ese matricula
     */
    override fun save(entity: Vehiculo): Vehiculo {
        logger.debug { "Guardando vehículo: $entity" }

        items[entity.matricula] = entity.copy(
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        return entity
    }

    /**
     * Actualiza un vehículo por su id
     * @param id Identificador del vehículo
     * @param entity Vehículo con los datos a actualizar
     * @return Vehículo actualizado
     */
    override fun update(id: String, entity: Vehiculo): Vehiculo? {
        logger.debug { "Actualizando vehículo por id: $id" }

        return items[id]?.let {
            items[id] = entity.copy(
                updatedAt = LocalDateTime.now()
            )
            return items[id]
        }
    }

    /**
     * Elimina un vehículo por su id
     * @param id Identificador del vehículo
     * @return Vehículo eliminado
     */
    override fun deleteById(id: String): Vehiculo? {
        logger.debug { "Eliminando vehículo por id: $id" }

        return items.remove(id)
    }

    /**
     * Elimina todos los vehículos
     */
    override fun deleteAll() {
        logger.debug { "Eliminando todos los vehículos" }

        items.clear()
    }
}