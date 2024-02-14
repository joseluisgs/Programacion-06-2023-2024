package dev.joseluisgs.factories

import dev.joseluisgs.models.Localizacion
import dev.joseluisgs.models.Smith


class SmithFactory {
    /**
     * Crea un Smith aleatorio.
     *
     * @return Smith aleatorio.
     */

    companion object {
        fun createRandom(): Smith {
            val ciudad = listOf("Madrid", "New York", "Pekin", "Leganés", "Londres", "Paris")
            val localizacion = Localizacion(
                -1,
                -1,
                ciudad.random()
            )
            return Smith(
                localizacion,  // Solo infecta en las 8 direcciones: N, NE, E, SE, S, SW, W, NW
                (0..7).random() // Dirección aleatoria
            )
        }
    }
}
