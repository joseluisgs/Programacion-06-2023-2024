package dev.joseluisgs.services

import dev.joseluisgs.models.Vehiculo

interface VehiculosService {
    fun findByMarca(marca: String): List<Vehiculo>
    fun findByAñoMatriculacion(añoMatriculacion: Int): List<Vehiculo>
    fun findByVendido(vendido: Boolean): List<Vehiculo>
    fun findAll(page: Int? = null, pageSize: Int = 10): List<Vehiculo>
    fun findById(id: String): Vehiculo
    fun create(vehiculo: Vehiculo): Vehiculo
    fun update(vehiculo: Vehiculo): Vehiculo
    fun delete(id: String): Vehiculo
}