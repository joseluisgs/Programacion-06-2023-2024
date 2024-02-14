package dev.joseluisgs.extensions

import dev.joseluisgs.models.Estudiante

fun Estudiante.esAprobado(limite: Double = 5.0): Boolean {
    return this.nota >= limite
}

fun Array<Estudiante>.forEach(action: (e: Estudiante) -> Unit) {
    for (e in this) {
        action(e)
    }
}

fun Array<Estudiante>.filter(predicate: (e: Estudiante) -> Boolean): Array<Estudiante?> {
    val aux: Array<Estudiante?> = arrayOfNulls<Estudiante>(this.size)

    var i = 0
    for (e in this) {
        if (predicate(e)) {
            aux[i] = e
            i++
        }
    }

    // contamos los nulos
    var nulos = 0
    for (e in aux) {
        if (e == null) {
            nulos++
        }
    }

    // creamos el array final
    val final: Array<Estudiante?> = arrayOfNulls<Estudiante>(this.size - nulos)
    var j = 0
    for (e in aux) {
        if (e != null) {
            final[j] = e
            j++
        }
    }

    return final
}

fun Array<Estudiante>.media(): Double {
    var media = 0.0
    for (e in this) {
        media += e.nota
    }
    return media / this.size
}

operator fun Estudiante.plus(estudiante: Estudiante): Estudiante {
    return Estudiante(this.nombre + estudiante.nombre, this.nota + estudiante.nota)
}


operator fun Array<Estudiante>.plus(estudiante: Estudiante): Array<Estudiante?> {
    val aux: Array<Estudiante?> = arrayOfNulls<Estudiante>(this.size + 1)
    for (i in this.indices) {
        aux[i] = this[i]
    }
    aux[this.size] = estudiante
    return aux
}

operator fun Array<Estudiante>.not(): Array<Estudiante?> {
    val aux: Array<Estudiante?> = arrayOfNulls<Estudiante>(this.size)
    // Quitamos los suspensos
    var i = 0
    for (e in this) {
        if (!e.esAprobado()) {
            aux[i] = e
            i++
        }
    }
    // Quitamos los nulos
    var nulos = 0
    for (e in aux) {
        if (e == null) {
            nulos++
        }
    }
    // creamos el array final
    val final: Array<Estudiante?> = arrayOfNulls<Estudiante>(this.size - nulos)
    var j = 0
    for (e in aux) {
        if (e != null) {
            final[j] = e
            j++
        }
    }
    return final
}