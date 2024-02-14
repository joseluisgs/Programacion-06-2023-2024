package dev.joseluisgs

import dev.joseluisgs.controllers.TheMatrixController

fun main() {
    println("Bienvenid@ a Matrix!\n")
    // Leemos la dimension de la matriz

    val config = initConfig()
    // var dimension = 5;
    println()
    println("Iniciando The Matrix con dimension: ${config["dimension"]}x${config["dimension"]} y tiempo ${config["time"]}s")
    // Controlador de la lógica del programa
    val theMatrix = TheMatrixController.getInstance(config)
    // iniciamos los elementos del sistema
    theMatrix.init()
    // imprimimos la matriz...
    // theMatrix.print();
    // iniciamos la simulacion
    theMatrix.simulate()
    // informe de resultados
    theMatrix.report()
}

/**
 * Inicializamos la configuración del programa.
 *
 * @return dimension de la matriz
 */
private fun initConfig(): Map<String, Int> {
    return mutableMapOf("dimension" to readDimension(), "time" to readTime())
}


// Leemos los valores de la matriz interactuando con el usuario
// Opciones de filtro con valores posibles. Expresiones regulares.
// https://www.vogella.com/tutorials/JavaRegularExpressions/article.html
private fun readDimension(): Int {
    var input: String
    // Expresion regular para validar el formato de la entrada
    // caracteres que representa un digito entre 5 y 9, y dos caracteres que son 1 y 0, es decir 10.
    val regex = "[5-9]|10"
    do {
        println("Introduce la dimension de la matriz [5-10]: ")
        input = readln()
        if (!input.matches(regex.toRegex())) {
            println("Error: La dimension debe estar entre 5 y 10\n")
        }
    } while (!input.matches(regex.toRegex()))
    return input.toInt()
}

private fun readTime(): Int {
    var input: String
    // Expresion regular para validar el formato de la entrada
    // carcter que va desde 2 hasta 9, siguiente caracter que es un digito entre 0 y 9,
    // o tres caracteres que son 1 0 0, es decir 100.
    val regex = "[2-9][0-9]|100"
    do {
        println("Introduce el tiempo de ejecución en segundos [20-100]: ")
        input = readln()
        if (!input.matches(regex.toRegex())) {
            println("Error: El tiempo de ejecución debe estar entre 20 y 100\n")
        }
    } while (!input.matches(regex.toRegex()))
    return input.toInt()
}