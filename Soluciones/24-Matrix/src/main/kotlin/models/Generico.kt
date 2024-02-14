package dev.joseluisgs.models

class Generico(nombre: String, localizacion: Localizacion) : Persona(nombre, localizacion), Personaje {
    var probMorir: Float = (0..100).random().toFloat()
        set(value) {
            field = value
            if (value < 0) {
                field = 0f
            }
        }


    override fun toString(): String {
        return "\uD83E\uDD28"
    }

    override fun mostrar(): String {
        // Usando DecimalFormat
        //
        // DecimalFormat df = new DecimalFormat("#.00");
        // System.out.println(df.format(number));
        // /* Salida : 1.42*/
        //Usando String.Format
        //
        //System.out.println(String.format("%.2f", number));
        ///* Salida : 1.42*/

        return "Generico{id=$id, nombre='$nombre', localizacion=$localizacion, createdAt=$createdAt, probMorir=${
            String.format(
                "%.2f",
                probMorir
            )
        }}"
    }

    override fun pedir() {
        println(this.toString())
    }

    override fun generar() {
        println("Generando un nuevo Generico")
    }

}