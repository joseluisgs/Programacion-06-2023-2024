package dev.joseluisgs.models

class Smith(localizacion: Localizacion, val probInfectar: Int) : Persona("Smith", localizacion), Personaje {
    init {
        nombre = "$nombre-$id"
    }

    override fun toString(): String {
        return "\uD83D\uDE08"
    }

    override fun mostrar(): String {
        return "Smith{id=$id, nombre='$nombre', localizacion=$localizacion, createdAt=$createdAt, probInfectar=$probInfectar}"
    }

    override fun pedir() {
        println(this.toString())
    }

    override fun generar() {
        println("Generando un nuevo Smith")
    }
}