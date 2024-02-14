package dev.joseluisgs.exceptions

sealed class VehiculoException(message: String) : Exception(message) {
    // No existe el vehículo
    class VehiculoNoEncontradoException(matricula: String) :
        VehiculoException("Vehículo con matricula: $matricula no encontrado")

    // Violación de clave primaria
    class VehiculoMatriculaYaExisteException(matricula: String) :
        VehiculoException("Vehículo con matricula: $matricula ya existe")

    // Violación de clave foránea
    class VehiculoNoValidoException(matricula: String, message: String) :
        VehiculoException("Vehículo con matricula: $matricula no es válido: $message")
}