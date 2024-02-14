package dev.joseluisgs.services

import dev.joseluisgs.exceptions.VehiculoException
import dev.joseluisgs.models.Vehiculo
import dev.joseluisgs.repositories.cache.CacheImpl
import dev.joseluisgs.repositories.vehiculos.VehiculosRepository
import org.lighthousegames.logging.logging

/**
 * Implementación de nuestro servicio de vehículos
 */

private val logger = logging()

class VehiculosServiceImpl(
    private val vehiculosRepository: VehiculosRepository,
    private val cache: CacheImpl<Vehiculo, String>

) : VehiculosService {

    override fun findByMarca(marca: String): List<Vehiculo> {
        logger.debug { "Buscando vehículos por marca: $marca" }

        return vehiculosRepository.findByMarca(marca)
    }

    override fun findByAñoMatriculacion(añoMatriculacion: Int): List<Vehiculo> {
        logger.debug { "Buscando vehículos por fecha de matriculación: $añoMatriculacion" }

        return vehiculosRepository.findByAñoMatriculacion(añoMatriculacion)
    }

    override fun findByVendido(vendido: Boolean): List<Vehiculo> {
        logger.debug { "Buscando vehículos por vendido: $vendido" }

        return vehiculosRepository.findByVendido(vendido)
    }

    override fun findAll(page: Int?, pageSize: Int): List<Vehiculo> {
        logger.debug { "Buscando todos los vehículos" }

        if (page == null || pageSize < 0) {
            logger.debug { "Buscando todos los vehículos sin paginación" }
            return vehiculosRepository.findAll()
        }
        //return vehiculosRepository.findAll().subList(page * pageSize, (page + 1) * pageSize)
        logger.debug { "Buscando todos los vehículos con page: $page y pageSize: $pageSize" }
        return vehiculosRepository.findAll()
            .windowed(pageSize, pageSize, true)[page]

    }

    override fun findById(id: String): Vehiculo {
        logger.debug { "Buscando vehículo por id: $id" }
        // Primero en cache
        /*val vehiculo = cache.get(id)
        if (vehiculo != null) {
            logger.debug { "Vehículo encontrado en cache: $vehiculo" }
            return vehiculo
        }
        // Si no está en cache, lo buscamos en el repositorio
        val vehiculoRepo = vehiculosRepository.findById(id)
        if (vehiculoRepo != null) {
            logger.debug { "Vehículo encontrado en repositorio: $vehiculoRepo" }
            cache.put(vehiculoRepo.matricula, vehiculoRepo)
            return vehiculoRepo
        }
        // Si no está en el repositorio, lanzamos una excepción
        logger.debug { "Vehículo no encontrado" }
        throw VehiculoException.VehiculoNoEncontradoException(id)*/

        // Ahora solo con control de nulos y elvis
        return cache.get(id) ?: vehiculosRepository.findById(id)
            ?.also { cache.put(it.matricula, it) }
        ?: throw VehiculoException.VehiculoNoEncontradoException(id)

    }

    override fun create(vehiculo: Vehiculo): Vehiculo {
        logger.debug { "Creando vehículo: $vehiculo" }

        // Buscamos si ya existe en la cache y en el repositorio
        /*if (cache.get(vehiculo.matricula) != null) {
            logger.debug { "Vehículo con matrícula: ${vehiculo.matricula} ya existe" }
            throw VehiculoException.VehiculoMatriculaYaExisteException(vehiculo.matricula)
        }

        if (vehiculosRepository.findById(vehiculo.matricula) != null) {
            logger.debug { "Vehículo con matrícula: ${vehiculo.matricula} ya existe" }
            throw VehiculoException.VehiculoMatriculaYaExisteException(vehiculo.matricula)
        }
        */
        cache.get(vehiculo.matricula)?.let {
            logger.debug { "Vehículo con matrícula: ${vehiculo.matricula} ya existe" }
            throw VehiculoException.VehiculoMatriculaYaExisteException(vehiculo.matricula)
        } ?: vehiculosRepository.findById(vehiculo.matricula)?.let {
            logger.debug { "Vehículo con matrícula: ${vehiculo.matricula} ya existe" }
            throw VehiculoException.VehiculoMatriculaYaExisteException(vehiculo.matricula)
        }

        return vehiculosRepository.save(vehiculo).also { cache.put(it.matricula, it) }
    }

    override fun update(vehiculo: Vehiculo): Vehiculo {
        logger.debug { "Actualizando vehículo: $vehiculo" }
        // Si no existe en la cache, lo buscamos en el repositorio

        /* if (cache.get(vehiculo.matricula) == null) {
             if (vehiculosRepository.findById(vehiculo.matricula) == null) {
                 logger.debug { "Vehículo con matrícula: ${vehiculo.matricula} no existe" }
                 throw VehiculoException.VehiculoNoEncontradoException(vehiculo.matricula)
             }
         }*/

        // Sustituimos el if anterior por elvis
        cache.get(vehiculo.matricula) ?: vehiculosRepository.findById(vehiculo.matricula)
        ?: throw VehiculoException.VehiculoNoEncontradoException(vehiculo.matricula)

        return vehiculosRepository.update(vehiculo.matricula, vehiculo)!!.also { cache.put(it.matricula, it) }

    }

    override fun delete(id: String): Vehiculo {
        logger.debug { "Eliminando vehículo por id: $id" }

        // Borramos de la cache y del repositorio
        return vehiculosRepository.deleteById(id)?.also {
            cache.remove(it.matricula)
            return it
        } ?: throw VehiculoException.VehiculoNoEncontradoException(id)
    }
}