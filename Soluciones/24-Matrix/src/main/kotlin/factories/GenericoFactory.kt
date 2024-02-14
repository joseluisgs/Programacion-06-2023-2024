package dev.joseluisgs.factories

import dev.joseluisgs.models.Generico
import dev.joseluisgs.models.Localizacion


class GenericoFactory {
    /**
     * Crea un objeto de tipo Generico con datos aleatorios.
     *
     * @return Objeto de tipo Generico.
     */
    companion object {
        fun createRandom(): Generico {
            val nombre = listOf("Pepe", "Juan", "Maria", "Jose", "Ana", "Sonia")
            val ciudad = listOf("Madrid", "New York", "Pekin", "Leganés", "Londres", "Paris")
            val localizacion = Localizacion(
                -1,
                -1,
                ciudad.random()
            )
            return Generico(
                nombre.random(),
                localizacion
            )
        }
    }
}

