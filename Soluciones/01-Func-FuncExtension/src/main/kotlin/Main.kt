package dev.joseluisgs

// Finción de extensión, añadimos una función a la clase que queramos
// this es el objeto al que se le aplica la función
fun String.hola() {
    println("Hola $this")
}

fun String.repetirVeces(veces: Int): String {
    val res = StringBuilder()
    for (i in 0..veces) {
        res.append(this)
    }
    return res.toString()
}

fun String.mayusculas(): String {
    return this.uppercase()
}

fun Int.esPrimo(): Boolean {
    for (i in 2 until this) {
        if (this % i == 0) {
            return false
        }
    }
    return true
}

fun Int.sumar(a: Int): Int {
    return this + a
}

fun IntArray.forEach(action: (Int) -> Unit) {
    for (element in this) action(element)
}

fun IntArray.filter(action: (Int) -> Boolean): IntArray {
    val result = Array<Int?>(this.size) { null }
    var index = 0
    for (i in this) {
        if (action(i)) {
            result[index] = i
            index++
        }
    }
    // Quitamos los nulos
    var countNulls = 0
    for (i in result.indices) {
        if (result[i] == null) {
            countNulls++
        }
    }
    val result2 = IntArray(this.size - countNulls)
    index = 0
    for (i in result.indices) {
        if (result[i] != null) {
            result2[index] = result[i]!!
            index++
        }
    }
    return result2
}

fun IntArray.map(action: (Int) -> Int): IntArray {
    val result = IntArray(this.size)
    for (i in this.indices) {
        result[i] = action(this[i])
    }
    return result
}

fun IntArray.find(action: (Int) -> Boolean): Int? {
    for (i in this.indices) {
        if (action(this[i])) {
            return this[i]
        }
    }
    return null
}

fun IntArray.indexOf(action: (Int) -> Boolean): Int {
    for (i in this.indices) {
        if (action(this[i])) {
            return i
        }
    }
    return -1
}

data class Persona(val nombre: String, val edad: Int)

fun Persona.esMayorDeEdad() = this.edad >= 18


fun main() {
    "1DAW".hola()
    println("1DAW".repetirVeces(5))
    println("1daw".mayusculas())
    println(5.esPrimo())
    println(5.sumar(3))

    val array = intArrayOf(1, 2, 3, 4, 5)
    array.forEach {
        println(it)
    }

    val array2 = array.filter {
        it % 2 == 0
    }
    println(array2.contentToString())

    val array3 = array.filter {
        it % 2 != 0
    }
    println(array3.contentToString())

    val array4 = array.filter {
        it.esPrimo()
    }
    println(array4.contentToString())

    val array5 = array.map {
        it * 2
    }
    println(array5.contentToString())

    val res = array.find {
        it == 3
    }
    println(res)

    val res2 = array.find { it % 2 == 0 }
    println(res2)

    val res3 = array.indexOf { it % 2 == 0 }
    println(res3)

    val p = Persona("Jose", 18)
    println(p.esMayorDeEdad())

}