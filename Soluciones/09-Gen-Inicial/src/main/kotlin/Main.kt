package dev.joseluisgs

import dev.joseluisgs.types.Box
import dev.joseluisgs.types.Pair

fun main() {
    val b1 = Box(1)
    val b2 = Box("Hola")
    val b3 = Box(1.0)

    val p1 = Pair(1, "Hola")
    val p2 = Pair("Hola", 1)
    val p3 = Pair("Hola", "Hola")

    printPairConcretos(p1)
    printPair(p2)

    val t1 = Triple(1, "Hola", 1.0)
    val t2 = Triple("Hola", 1, 1.0)
    val t3 = Triple("Hola", "Hola", "Hola")

    val locura = Triple(Triple(1, "Hola", Box(1.0)), Pair("Hola", 1), Pair("Hola", "Hola"))
}

fun printPairConcretos(pair: Pair<Int, String>) {
    println("Pair: ${pair.first} ${pair.second}")
}

fun <T, R> printPair(pair: Pair<T, R>) {
    println("Pair: ${pair.first} ${pair.second}")
}
