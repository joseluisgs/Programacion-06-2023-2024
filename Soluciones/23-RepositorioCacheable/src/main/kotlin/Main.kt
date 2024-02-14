package dev.joseluisgs

import dev.joseluisgs.exceptions.VehiculoException
import dev.joseluisgs.models.Vehiculo
import dev.joseluisgs.repositories.cache.CacheImpl
import dev.joseluisgs.repositories.vehiculos.VehiculosRepositoryImpl
import dev.joseluisgs.services.VehiculosServiceImpl
import java.time.LocalDate

fun main() {
    val service = VehiculosServiceImpl(
        VehiculosRepositoryImpl(),
        CacheImpl<Vehiculo, String>()
    )

    val v1 = Vehiculo(
        "1234ABC",
        "Seat",
        "Ibiza",
        "Rojo",
        12000.0,
        LocalDate.of(2015, 1, 1),
        false
    )

    val v2 = Vehiculo(
        "5678DEF",
        "Ford",
        "Focus",
        "Azul",
        15000.0,
        LocalDate.of(2016, 1, 1),
        false
    )

    val v3 = Vehiculo(
        "91011GHI",
        "Seat",
        "León",
        "Blanco",
        18000.0,
        LocalDate.of(2017, 1, 1),
        false
    )

    val v4 = Vehiculo(
        "121314JKL",
        "Ford",
        "Fiesta",
        "Negro",
        10000.0,
        LocalDate.of(2017, 1, 1),
        false
    )

    val v5 = Vehiculo(
        "151617MNO",
        "Seat",
        "Toledo",
        "Verde",
        20000.0,
        LocalDate.of(2017, 1, 1),
        false
    )

    val v6 = Vehiculo(
        "181920PQR",
        "Renault",
        "Clio",
        "Blanco",
        30000.0,
        LocalDate.of(2020, 1, 1),
        true
    )

    // Insertamos los datos en la base de datos
    service.create(v1)
    service.create(v2)
    service.create(v3)
    service.create(v4)
    service.create(v5)
    service.create(v6)

    // Obtenemos los datos de la base de datos
    println(service.findAll())

    // Obtenemos los datos de la base de datos
    println(service.findByVendido(false))

    println(service.findByAñoMatriculacion(2020))

    // Pagina 2 de tamaño 1
    println(service.findAll(1, 2))

    // Violacion de clave!!
    try {
        val v7 = Vehiculo(
            "121314JKL",
            "Ford",
            "Fiesta",
            "Negro",
            10000.0,
            LocalDate.of(2017, 1, 1),
            false
        )
        service.create(v7)
    } catch (e: VehiculoException.VehiculoMatriculaYaExisteException) {
        println(e.message)
    }

    // Actualizamos un vehiculo
    val v8 = v6.copy(
        marca = "Renault",
        modelo = "Clio",
        color = "Amarillo",
        precio = 40000.0
    )

    println(service.update(v8))

    // Eliminamos un vehiculo
    println(service.delete("1234ABC"))

    // Eliminamos un vehiculo que no existe
    try {
        service.delete("KAKA")
    } catch (e: VehiculoException.VehiculoNoEncontradoException) {
        println(e.message)
    }

    // Obtenemos todos los vehículos
    val vehiculos = service.findAll()

    // Vehiculo más caro
    println(vehiculos.maxBy { it.precio })

    // Vehiculo más barato
    println(vehiculos.minBy { it.precio })

    // La media de precios
    println(vehiculos.map { it.precio }.average())

    // Agrupar por marca
    println(vehiculos.groupBy { it.marca })

    // Agrupar por marca y contar
    // println(vehiculos.groupingBy { it.marca }.eachCount())
    println(
        vehiculos.groupBy { it.marca }
            .mapValues { it.value.size }
    )

    // Cuantos Renault hay
    println(vehiculos.count { it.marca == "Renault" })

    // Precio medio por marca
    println(vehiculos.groupBy { it.marca }
        .mapValues { it.value.map { c -> c.precio }.average() }
    )

    // Precio medio de los coches de color blanco
    println(vehiculos.filter { it.color == "Blanco" }
        .map { it.precio }
        .average()
    )

    // Precio maximo de los coches de color blanco
    println(vehiculos.filter { it.color == "Blanco" }
        .map { it.precio }
        .maxOrNull()
    )

    // Filtrar por Tipos de Objetos
    println(vehiculos.filter { it is Vehiculo })
    println(vehiculos.filterIsInstance<Vehiculo>())

    // Agrupar por tipos de objetos
    println(
        vehiculos.groupBy { it::class }
            .mapKeys { it.key.simpleName }
    )
}


