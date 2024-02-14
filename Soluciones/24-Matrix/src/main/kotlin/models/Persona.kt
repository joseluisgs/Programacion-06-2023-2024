package dev.joseluisgs.models

import java.time.LocalDateTime


abstract class Persona(var nombre: String, var localizacion: Localizacion) : Comparable<Persona?> {
    var id: Int
        protected set

    var createdAt: LocalDateTime
        protected set

    init {
        this.createdAt = LocalDateTime.now()
        this.id = ++contador
    }

    companion object {
        private var contador = 0
    }

    override fun toString(): String {
        return "Persona(nombre='$nombre', id=$id, createdAt=$createdAt)"
    }

    override fun compareTo(other: Persona?): Int {
        return this.id - other!!.id
    }

}
