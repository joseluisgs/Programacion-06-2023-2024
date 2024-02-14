package dev.joseluisgs

import dev.joseluisgs.extensions.esAprobado
import dev.joseluisgs.extensions.filter
import dev.joseluisgs.extensions.media
import dev.joseluisgs.extensions.not
import dev.joseluisgs.models.Estudiante

fun main() {
    println("Hello Student!")

    val e1 = Estudiante("Jose", 10.0)
    println(e1)
    println(e1.esAprobado())
    val e2 = Estudiante("Pedro", 4.99)
    println(e2)
    println(e2.esAprobado())

    var estudiantesArray = arrayOf(e1, e2)
    estudiantesArray.forEach {
        println(it)
    }
    estudiantesArray.forEach {
        println("$it es aprobado: ${it.esAprobado()}")
    }

    val aprobados = estudiantesArray.filter {
        it.esAprobado()
    }
    aprobados.forEach {
        println(it)
    }

    val suspensos = estudiantesArray.filter {
        !it.esAprobado()
    }
    suspensos.forEach {
        println(it)
    }

    val notables = estudiantesArray.filter {
        it.nota >= 7.0 && it.nota < 9.0
    }
    notables.forEach {
        println(it)
    }

    println("La media es: ${estudiantesArray.media()}")

    val e3 = Estudiante("Juan", 5.0)
    val e4 = Estudiante("Ana", 6.0)

    estudiantesArray = estudiantesArray + e3
    estudiantesArray = estudiantesArray + e4

    println(estudiantesArray.contentToString())

    var suspensos2 = estudiantesArray.filter {
        !it.esAprobado()
    }
    println(suspensos2.contentToString())
    var suspensos3 = !estudiantesArray
    println(suspensos3.contentToString())

}