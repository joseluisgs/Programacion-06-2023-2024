package dev.joseluisgs

import dev.joseluisgs.cache.Cache

fun main() {
    val cache = Cache<Vehiculo, String>()

    val vehiculo1 = Vehiculo("1234ABC", "Seat", "Ibiza", "Blanco", 12000.0)
    val vehiculo2 = Vehiculo("5678DEF", "Renault", "Clio", "Azul", 10000.0)
    val vehiculo3 = Vehiculo("1010GHI", "Ford", "Focus", "Rojo", 15000.0)
    val vehiculo4 = Vehiculo("1212JKL", "Fiat", "Punto", "Verde", 8000.0)
    val vehiculo5 = Vehiculo("1313MNO", "Opel", "Corsa", "Amarillo", 11000.0)
    val vehiculo6 = Vehiculo("1414PQR", "Volkswagen", "Jetta", "Azul", 13000.0)
    val vehiculo7 = Vehiculo("1515TUV", "Volkswagen", "Jetta", "Rojo", 14000.0)

    cache.put(vehiculo1.matricula, vehiculo1)
    cache.put(vehiculo2.matricula, vehiculo2)
    cache.put(vehiculo3.matricula, vehiculo3)
    cache.put(vehiculo4.matricula, vehiculo4)
    cache.put(vehiculo1.matricula, vehiculo1)
    cache.put(vehiculo4.matricula, vehiculo4)
    cache.put(vehiculo5.matricula, vehiculo5)
    cache.put(vehiculo6.matricula, vehiculo6)
    cache.put(vehiculo4.matricula, vehiculo4)
    cache.put(vehiculo7.matricula, vehiculo7)
    cache.put(vehiculo6.matricula, vehiculo6)

    println(cache)

}