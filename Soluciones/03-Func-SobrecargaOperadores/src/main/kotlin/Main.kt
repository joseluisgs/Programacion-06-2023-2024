package dev.joseluisgs

import kotlin.math.pow
import kotlin.math.sqrt

data class Punto(var x: Double, var y: Double)

fun Punto.distancia(punto: Punto): Double {
    return sqrt((punto.x - this.x).pow(2.0) + (punto.y - this.y).pow(2.0))
}

// sobrecarga de operadores
operator fun Punto.plus(punto: Punto): Punto {
    return Punto(this.x + punto.x, this.y + punto.y)
}

operator fun Punto.minus(punto: Punto): Punto {
    return Punto(this.x - punto.x, this.y - punto.y)
}

operator fun Punto.plusAssign(punto: Punto) {
    this.x += punto.x
    this.y += punto.y

}

operator fun Punto.minusAssign(punto: Punto) {
    this.x -= punto.x
    this.y -= punto.y
}

operator fun Punto.unaryMinus(): Punto {
    return Punto(-this.x, -this.y)
}

operator fun Punto.times(multiplicador: Double): Punto {
    return Punto(this.x * multiplicador, this.y * multiplicador)
}

operator fun Punto.not(): Punto {
    return Punto(-this.y, -this.x)
}


data class Fraccion(var numerador: Int, var denominador: Int) {
    override fun toString(): String {
        return "$numerador/$denominador"
    }
}

fun Fraccion.simplificar() {
    // Maximo comun divisor
    var mcd = 1
    var a = this.numerador
    var b = this.denominador
    while (a != b) {
        if (a > b) {
            a -= b
        } else {
            b -= a
        }
    }
    mcd = a
    this.numerador /= mcd
    this.denominador /= mcd
}

operator fun Fraccion.plus(fraccion: Fraccion): Fraccion {
    return Fraccion(
        this.numerador * fraccion.denominador + fraccion.numerador * this.denominador,
        this.denominador * fraccion.denominador
    )
}

operator fun Fraccion.minus(fraccion: Fraccion): Fraccion {
    return Fraccion(
        this.numerador * fraccion.denominador - fraccion.numerador * this.denominador,
        this.denominador * fraccion.denominador
    )
}

operator fun Fraccion.times(fraccion: Fraccion): Fraccion {
    return Fraccion(this.numerador * fraccion.numerador, this.denominador * fraccion.denominador)

}

operator fun Fraccion.div(fraccion: Fraccion): Fraccion {
    return Fraccion(this.numerador * fraccion.denominador, this.denominador * fraccion.numerador)

}

operator fun Fraccion.unaryMinus(): Fraccion {
    return Fraccion(-this.numerador, this.denominador)

}

operator fun Fraccion.plusAssign(fraccion: Fraccion) {
    this.numerador += fraccion.denominador
    this.denominador += fraccion.numerador
}

operator fun Fraccion.minusAssign(fraccion: Fraccion) {
    this.numerador -= fraccion.denominador
    this.denominador -= fraccion.numerador
}

operator fun Fraccion.timesAssign(fraccion: Fraccion) {
    this.numerador *= fraccion.numerador
    this.denominador *= fraccion.denominador
}

operator fun Fraccion.divAssign(fraccion: Fraccion) {
    this.numerador /= fraccion.denominador
    this.denominador /= fraccion.numerador
}


fun main() {
    println("Hello Point!")
    val p1 = Punto(1.0, 2.0)
    val p2 = Punto(3.0, 4.0)

    println(p1)
    println(p2)

    println(p1.distancia(p2))
    println(p1 + p2)
    println(p1 - p2)
    p1 += p2
    println(p1)
    p1 -= p2
    println(p1)
    println(-p1)
    println(p1 * 2.0)
    println(!p1)

    println("Hello Fraction!")
    val f1 = Fraccion(1, 2)
    val f2 = Fraccion(3, 4)

    println(f1)
    println(f2)

    println(f1 + f2)
    println(f1 - f2)
    println(f1 * f2)
    println(f1 / f2)

    f1 += f2
    println(f1)

    f1 -= f2
    println(f1)

    f1 *= f2
    println(f1)

    f1 /= f2
    println(f1)
}