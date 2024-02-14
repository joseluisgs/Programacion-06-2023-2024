package dev.joseluisgs.models

class Neo : Persona("Neo", Localizacion(0, 0, "Luis Vives")), Personaje {
    val isElegido: Boolean
        /**
         * Método que devuelve una probabilidad de que el personaje indique si es el Elegido.
         *
         * @return
         */
        get() = (0..100).random() > 50

    override fun toString(): String {
        return "\uD83D\uDE0E"
    }

    override fun mostrar(): String {
        return "Neo{id=$id, nombre='$nombre', localizacion=$localizacion, createdAt=$createdAt, isElegido=$isElegido}"

    }

    override fun pedir() {
        println(this.toString())
    }

    override fun generar() {
        println("Generando un nuevo Neo")
    }
}