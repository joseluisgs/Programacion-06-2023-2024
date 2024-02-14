package dev.joseluisgs.repositories.vehiculos

import dev.joseluisgs.models.Vehiculo
import dev.joseluisgs.repositories.crud.CrudRepository

interface VehiculosRepository : CrudRepository<Vehiculo, String> {
    fun findByMarca(marca: String): List<Vehiculo>
    fun findByAñoMatriculacion(añoMatriculacion: Int): List<Vehiculo>
    fun findByVendido(vendido: Boolean): List<Vehiculo>
}