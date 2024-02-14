package dev.joseluisgs

import java.util.*

// Aquí esta claro que extendemos  con una función de extensión y con una función de receptor
fun Int.sumExtension(other: Int): Int = this + other
// 3.sumExtension(4) --> 7 pero yo no puedo hacer sumExtension(3,4), pierdo esa opción

// Pero vamos a hacerlo de esta manera. De esta manera usamos el lambda con el receptor
// tenemos implicito el this
val sum: Int.(Int) -> Int = { a -> this + a } // this es el Int que recibe la función

// Con funciones de orden superior y labda
fun operacion(valor: Int, accion: (Int) -> Int): Int = accion(valor)

// Aqui extendemos con opp que a su vez como parámetro usa en vez de una función o lambda una función de extensión
fun Int.opp(operacion: Int.() -> Int): Int = operacion()

// safebuilders o DSL
// accedemos a la función append de la clase StringBuilder al ser el receptor
fun buildString(actions: StringBuilder.() -> Unit): String {
    val builder = StringBuilder()
    // Aquí usamos la función de extensión append de la clase StringBuilder y ejecutamos
    // el bloque de código que nos pasan como parámetro
    builder.actions()
    return builder.toString()
}

data class Persona(var nombre: String, var apellidos: String)

// builder con funcion de extensión
fun buildPersona(actions: Persona.() -> Unit): Persona {
    val persona = Persona("", "")
    persona.actions()
    return persona
}

// DSL type safe builder for table HTML kotlin


fun main() {
    // Uso de la función de extensión
    println(3.sumExtension(4)) // 7

    // Uso de la función de receptor
    println(sum(3, 4)) // 7
    println(3.sum(4)) // 7

    // uso de la una funcion de orden superior con la lambda
    println(operacion(3) { it + 4 })
    println(operacion(3) { it - 4 })

    // Uso de la función de extensión con la función de receptor
    println(3.opp { this + 4 })
    println(3.opp { this - 4 })
    println(3.opp { this * 4 })
    println(3.opp { this / 4 })

    // safebuilders o DSL
    val result = buildString {
        append("Hola")
        append(" ")
        append("Mundo")
    }.uppercase(Locale.getDefault())
    println(result) // HOLA MUNDO

    val persona = buildPersona {
        nombre = "Jose Luis"
        apellidos = "García Sastre"
    }
    println(persona) // Persona(nombre=Jose Luis, apellidos=García Sastre)

    val persona2 = buildPersona {
        nombre = arrayOf("Jose", "Luis", "Ana", "Natalia").random()
        apellidos = arrayOf("García", "Sastre", "García Sastre").random()
    }
    println(persona2) // Persona(nombre=Ana, apellidos=García Sastre)

    val myTable = html {
        table {
            tr {
                td("Celda 1")
                td("Celda 2")
            }
            tr {
                td("Celda 3")
                td("Celda 4")
            }
        }
    }

    println(myTable)
}

class HTML {
    private val elements = mutableListOf<Table>()

    fun table(init: Table.() -> Unit) {
        val table = Table().apply(init)
        elements.add(table)
    }

    override fun toString(): String {
        return elements.joinToString(
            separator = "\n",
            prefix = "<html>\n<body>\n",
            postfix = "\n</body>\n</html>"
        ) { it.toString() }
    }
}

class Table {
    private val rows = mutableListOf<Tr>()

    fun tr(init: Tr.() -> Unit) {
        val tr = Tr().apply(init)
        rows.add(tr)
    }

    override fun toString(): String {
        return rows.joinToString(separator = "\n", prefix = "<table>\n", postfix = "\n</table>") { it.toString() }
    }
}

class Tr {
    private val cells = mutableListOf<Td>()

    fun td(content: String) {
        val td = Td(content)
        cells.add(td)
    }

    override fun toString(): String {
        return cells.joinToString(separator = "", prefix = "  <tr>\n", postfix = "\n  </tr>") { it.toString() }
    }
}

class Td(private val content: String) {
    override fun toString(): String {
        return "    <td>$content</td>"
    }
}

fun html(init: HTML.() -> Unit): HTML {
    return HTML().apply(init)
}
