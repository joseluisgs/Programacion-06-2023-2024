package dev.joseluisgs.controllers

import dev.joseluisgs.factories.GenericoFactory
import dev.joseluisgs.factories.SmithFactory
import dev.joseluisgs.models.Generico
import dev.joseluisgs.models.Neo
import dev.joseluisgs.models.Persona
import dev.joseluisgs.models.Smith
import dev.joseluisgs.repositories.cola.Cola
import dev.joseluisgs.repositories.cola.ColaImpl
import dev.joseluisgs.repositories.matriz.Matriz
import dev.joseluisgs.repositories.matriz.MatrizImpl
import java.util.*
import kotlin.math.min


class TheMatrixController private constructor(config: Map<String, Int>) {
    private val dimension: Int

    // Repositorios de objetos...
    // Almacen de personajes
    private val almacenRepository: Cola<Generico>

    // Lista de personajes Usados
    private val usadosRepository: MutableList<Persona>

    // Lista de virus Smith
    private val virusRepository: MutableList<Persona>

    // Lista (conjunto) de eliminados por Neo.
    private val eliminadosRepository: MutableSet<Persona> // no permite duplicados
    private val tiempo_max: Int

    // Mi estado
    private var matrix: Matriz<Persona?>
    private var tempMatrix: Matriz<Persona?>
    private var totalPersonajes = 0


    /**
     * Constructor privado para singleton
     *
     * @param config configuración de la simulación
     * - dimension: dimension de la matriz
     * - time: tiempo máximo de la simulación
     */
    init {
        dimension = config["dimension"] ?: 5
        tiempo_max = (config["time"] ?: 30) * 1000 // En segundos
        matrix = MatrizImpl(dimension, dimension)
        tempMatrix = MatrizImpl(dimension, dimension)
        // Iniciamos los repositorios de almacenamiento de la información
        almacenRepository = ColaImpl()
        usadosRepository = mutableListOf()
        virusRepository = mutableListOf()
        eliminadosRepository = TreeSet<Persona> { o1, o2 -> o1.createdAt.compareTo(o2.createdAt) }
    }

    /**
     * Imprime el estado de la matriz de Matrix
     */
    private fun print() {
        println("The Matrix Status: ")
        println(matrix)
    }

    /**
     * Inicializa el sistema Matrix
     */
    fun init() {
        // Inicializar almacen
        initAlmacen()
        // printAlmacen(); // Para analizar que hay toda la energia
        // Iniciamos a Neo
        initNeo()
        // Iniciamos a Smith
        initSmith()
        // situamos genericos en la matriz
        initGenIericos()
    }

    /**
     * Inicializa a los personajes genéricos dentro de la matriz
     */
    private fun initGenIericos() {
        // Situamos personajes en huecos libres
        for (fil in 0..<dimension) {
            for (col in 0..<dimension) {
                if (matrix[fil, col] == null) {
                    val generico = almacenRepository.dequeue()
                    matrix[fil, col] = generico
                    usadosRepository.add(generico)
                    updateLocalicacion(generico, fil, col)
                    // totalPersonajes++; No los pongo porque ya los he puesto en el almacen
                }
            }
        }
    }

    /**
     * Inicializa al primer agente Smith
     */
    private fun initSmith() {
        var fil: Int
        var col: Int
        do {
            fil = (0..<dimension).random()
            col = (0..<dimension).random()
        } while (matrix[fil, col] != null)
        val smith = SmithFactory.createRandom()
        matrix[fil, col] = smith
        updateLocalicacion(smith, fil, col)
        usadosRepository.add(smith)
        virusRepository.add(smith)
        totalPersonajes++
    }

    /**
     * Inicializa al Neo
     */
    private fun initNeo() {
        val fil = (0..<dimension).random()
        val col = (0..<dimension).random()
        val neo = Neo()
        matrix[fil, col] = neo
        updateLocalicacion(neo, fil, col)
        usadosRepository.add(neo)
        totalPersonajes++
    }

    /**
     * Actualiza la localización de un personaje en la matriz
     *
     * @param persona personaje a actualizar
     * @param fila    fila donde se encuentra es la latitud
     * @param columna columna donde se encuentra es la longitud
     */
    private fun updateLocalicacion(persona: Persona, fila: Int, columna: Int) {
        persona.localizacion.latitud = fila
        persona.localizacion.longitud = columna
    }

    private val neoPosition: Map<String, Int>?
        /**
         * Obtenemos la posición de Neo dentro de la matriz de Matrix
         *
         * @return posición de Neo como Mapa de coordenadas fila y columna
         */
        get() {
            for (fil in 0..<dimension) {
                for (col in 0..<dimension) {
                    if (matrix.get(fil, col) is Neo) {
                        val pos = HashMap<String, Int>()
                        pos["fila"] = fil + 1
                        pos["columna"] = col + 1
                        return pos
                    }
                }
            }
            return null
        }

    /**
     * Inicializa al almacen de personajes genéricos
     */
    private fun initAlmacen() {

        repeat(ALAMCEN_INIT_SIZE) {
            almacenRepository.queue(GenericoFactory.createRandom())
        }

        // Para llevar el total de personajes
        totalPersonajes += ALAMCEN_INIT_SIZE
    }

    /**
     * Imprime el almacen de personajes genéricos
     *
     */
    @Deprecated("No se usa y eliminar")
    private fun printAlmacen() {
        println("Almacen: ")
        println(almacenRepository)
        for (i in 0 until almacenRepository.size()) {
            println(almacenRepository[i])
        }
    }

    /**
     * Imprime el almacen de personajes genéricos
     */
    fun report() {
        println()
        println("Informe de simulación de The Matrix")
        println("The Matrix Final Status: ")
        println(matrix)
        println()

        println("Total Personajes: $totalPersonajes")
        println("Total Personajes Usados: " + usadosRepository.size)
        println("Total Personajes en almacen: " + almacenRepository.size())
        println("Total de Smith generados por virus: " + virusRepository.size)
        println("Total de Smith eliminados por Neo: " + eliminadosRepository.size)
        println()

        /*var neoPos = getNeoPosition();
        System.out.println("Posición de Neo \uD83D\uDE0E: [" + neoPos.get("fila") + "," + neoPos.get("columna") + "]");
        System.out.println();*/
        searchNeo()?.let { neo ->
            println("Posición de Neo \uD83D\uDE0E: [${neo.localizacion.latitud + 1},${neo.localizacion.longitud + 1}]")
        }
        println()


        println("Lista de personajes usados ordenados por id: ")
        usadosRepository
            .sortedBy { it.id } // .sorted() porque esta el comparable en persona
            .forEach { usado ->
                println("id: ${usado.id} - nombre: ${usado.nombre} - creado: ${usado.createdAt}")
            }

        println()

        println("Lista de virus Smith ordenados por Fecha descedente: ")
        virusRepository
            .sortedByDescending { it.createdAt }
            .forEach { smith ->
                println("id: ${smith.id} - nombre: ${smith.nombre} - creado: ${smith.createdAt}")
            }

        println()

        println("Lista de virus Smith eliminados por Neo: ")
        eliminadosRepository
            .forEach { eliminado ->
                println("id: ${eliminado.id} - nombre: ${eliminado.nombre} - creado: ${eliminado.createdAt}")
            }
    }

    /**
     * Busca al Neo en la matriz
     *
     * @return Neo
     */
    private fun searchNeo(): Persona? {
        for (fil in 0..<dimension) {
            for (col in 0..<dimension) {
                if (matrix[fil, col] is Neo) {
                    return matrix[fil, col]
                }
            }
        }
        return null
    }

    /**
     * Método que se encarga de la lógica de simulación de The Matrix
     */
    fun simulate() {
        var time = 0
        var exit: Boolean
        do {
            this.print()
            // Copiamos todos los cambios de la matriz original a la matriz temporal
            // leemos de la matriz original, y actuamos en la temporal.
            // y las acciones las escribiremos aquí.
            tempMatrix = matrix.clone()

            // incrementamos el tiempo y suspendemos el sistema para que no vaya tan rápido
            Thread.sleep(1000)
            time += 1000

            println("Tiempo: " + (time / 1000) + "s")

            if (time % 1000 == 0) {
                println("\uD83E\uDD28 Evaluando personajes")
                accionGenericos()
            }
            if (time % 2000 == 0) {
                println("\uD83D\uDE08 Agente Smith entra en acción")
                accionSmith()
            }
            if (time % 5000 == 0) {
                println("\uD83D\uDE0E Neo entra en acción")
                accionNeo()
            }
            if (time % 10000 == 0) {
                println("Nuevos personajes aparecen en The Matrix")
                accionNuevosGenericos()
            }

            // Cogemos todos lso cambios de la matriz temporal y se lo aplicamos a la original
            matrix = tempMatrix.clone()

            // Condiciones de salida
            exit = time > tiempo_max || almacenRepository.isEmpty()
        } while (!exit)
    }

    /**
     * Acción correspondiente a añadir nuevos genéricos a huecos libres de The Matrix
     * Siempre que haya hueco o como máximo 5, si tenemos en la cola.
     * Posición aleatoria de los nuevos genéricos
     */
    private fun accionNuevosGenericos() {
        println("Espacios libres en The Matrix: $freeSpace")
        println("Personajes en almacen dismponibles: ${almacenRepository.size()}")
        val max = min(almacenRepository.size(), min(freeSpace, 5))
        repeat(max) {
            val personaje = almacenRepository.dequeue()
            usadosRepository.add(personaje)
            //totalPersonajes++;
            // vamos a ver si lo ponemos en una posición aleatoria
            var fil: Int
            var col: Int
            var asigando = false
            do {
                fil = (0..<dimension).random()
                col = (0..<dimension).random()
                if (matrix[fil, col] == null) {
                    //matrix.set(fil, col, personaje);
                    tempMatrix[fil, col] = personaje
                    asigando = true
                    println("\uD83E\uDD28 Nuevo personaje en [${fil + 1},${col + 1}]")
                    updateLocalicacion(personaje, fil, col)
                }
            } while (!asigando)
        }
    }

    private val freeSpace: Int
        /**
         * Obtiene el número de huecos libres en The Matrix
         *
         * @return número de huecos libres
         */
        get() {
            var huecos = 0
            for (fil in 0 until dimension) {
                for (col in 0 until dimension) {
                    if (matrix[fil, col] == null) {
                        huecos++
                    }
                }
            }
            return huecos
        }

    /**
     * Acción correspondiente a la lógica de Neo
     */
    private fun accionNeo() {
        for (fil in 0..<dimension) {
            for (col in 0..<dimension) {
                // Si es Neo
                if (matrix[fil, col] is Neo) {
                    val neo = matrix[fil, col] as Neo
                    println("\uD83D\uDE0E Neo aparece desde la posicion [${fil + 1},${col + 1}]")
                    println("\uD83D\uDE0E Neo Decide si actua o no")
                    if (neo.isElegido) {
                        println("\uD83D\uDE0E Neo decide actuar y con ello elimina a los agentes Smith a su alrededor")
                        for (i in -1..1) {
                            for (j in -1..1) {
                                if ((fil + i >= 0) && (fil + i < dimension) && (col + j >= 0) && (col + j < dimension)) {
                                    // Si es un agente smith
                                    if (matrix[(fil + i), (col + j)] is Smith) {
                                        val smith = matrix[(fil + i), (col + j)] as Smith
                                        println("\uD83D\uDE0E Neo elimina a Smith ${smith.nombre} de la posicion [${fil + i + 1},${col + j + 1}]")
                                        // Añadimos a eliminados
                                        eliminadosRepository.add(smith)
                                        // ...y lo eliminamos de la matriz
                                        tempMatrix[(fil + i), (col + j)] = null
                                    }
                                }
                            }
                        }
                    } else {
                        println("\uD83D\uDE0E Neo decide no actuar")
                    }
                    // Debemos cambiar de posicion
                    println("\uD83D\uDE0E Neo cambia de posicion")
                    val newFil = (0..<dimension).random()
                    val newCol = (0..<dimension).random()
                    println("\uD83D\uDE0E Neo se mueve a la posicion [${newFil + 1},${newCol + 1}]")
                    // matrix.set(fil, col, null);
                    tempMatrix[fil, col] = null
                    // si esta ocupado por Smith --> muerte
                    if (matrix[newFil, newCol] is Smith) {
                        val smith = matrix[(newFil), (newCol)] as Smith
                        println("\uD83D\uDE0E Neo elimina a Smith ${smith.nombre} de la posicion [${newFil + 1},${newCol + 1}]")
                        eliminadosRepository.add(smith)
                        // matrix.set(fil, col, null);
                        tempMatrix[newFil, newCol] = null
                    } else if (matrix.get(newFil, newCol) is Generico) {
                        val generico = matrix[(newFil), (newCol)] as Generico
                        println("\uD83D\uDE0E Neo intercambia posicion con: ${generico.nombre} de la posicion [${newFil + 1},${newCol + 1}]")
                        // matrix.set(fil, col, generico);
                        tempMatrix[fil, col] = generico // TODO
                        updateLocalicacion(generico, fil, col)
                    }
                    // matrix.set(newFil, newCol, neo);
                    tempMatrix[newFil, newCol] = neo
                    updateLocalicacion(neo, newFil, newCol)
                }
            }
        }
    }

    /**
     * Acción correspondiente a la lógica de Smith
     */
    private fun accionSmith() {
        for (fil in 0..<dimension) {
            for (col in 0..<dimension) {
                // Si es instancia de Smith
                if (matrix[fil, col] is Smith) {
                    // Vamos a por los de alrededor en las 8 direcciones
                    // pero solo podemos actuar las veces que nos quedan...
                    val smith = matrix[fil, col] as Smith
                    var intentos = smith.probInfectar // solos los intebntos que podemos hacer
                    println("\uD83D\uDE08 Agente Smith: ${smith.nombre} - Intentos: $intentos - Posición: [${fil + 1},${col + 1}]")
                    for (i in -1..1) {
                        for (j in -1..1) {
                            if ((fil + i >= 0) && (fil + i < dimension) && (col + j >= 0) && (col + j < dimension) && (intentos > 0)) {
                                if (matrix[(fil + i), (col + j)] is Generico) {
                                    intentos--
                                    println("\uD83D\uDE08 Agente Smith: ${smith.nombre} infecta: [${fil + i + 1},${col + j + 1}] le quedan: $intentos intentos")
                                    val newSmith = SmithFactory.createRandom()
                                    tempMatrix[(fil + i), (col + j)] = newSmith
                                    usadosRepository.add(newSmith)
                                    virusRepository.add(newSmith)
                                    updateLocalicacion(newSmith, fil, col)
                                    totalPersonajes++
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Acción correspondiente a la lógica de Genérico
     */
    private fun accionGenericos() {
        // Recorremos la matriz en busca de genéricos
        for (fil in 0..<dimension) {
            for (col in 0..<dimension) {
                // Si es instancia de Generico
                if (matrix[fil, col] is Generico) {
                    val generico = matrix[fil, col] as Generico
                    if (generico.probMorir < 30) {
                        println(
                            "☠️ Muere generico: ${generico.id} en posición: [${fil + 1},${col + 1}] su probabilid de muerte era: ${
                                String.format(
                                    "%.2f",
                                    generico.probMorir
                                )
                            }"
                        )
                        // sacamos un nuevo personaje, solo si tenemos...
                        if (almacenRepository.size() > 0) {
                            val newGenerico = almacenRepository.dequeue()
                            // Lo ponemos en su lugar
                            // matrix.set(fil, col, newGenerico);
                            tempMatrix[fil, col] = newGenerico
                            // Lo añadimos a la lista de usados
                            usadosRepository.add(newGenerico)
                            // Actualizamos su localizacion
                            updateLocalicacion(newGenerico, fil, col)
                            // totalPersonajes++;
                            println(("\uD83E\uDD28 Aparece un nuevo Generico: ${newGenerico.id}") + " en posición: [" + (fil + 1) + "," + (col + 1) + "]")
                        } else {
                            println("\uD83D\uDEA9 No hay personajes en el almacen")
                            tempMatrix.set(fil, col, null)
                        }
                    } else {
                        // Disminuimos la probabilidad de muerte
                        generico.probMorir -= 10
                    }
                }
            }
        }
    }

    companion object {
        // Constantes
        private val ALAMCEN_INIT_SIZE = 200
        private var instance: TheMatrixController? = null // para singleton

        /**
         * Instancia única de TheMatrixController por Singleton
         *
         * @param config configuración de la simulación
         * - dimension: dimension de la matriz
         * - time: tiempo máximo de la simulación
         * @return instancia única de TheMatrixController
         */
        fun getInstance(config: Map<String, Int>): TheMatrixController {
            if (instance == null) {
                instance = TheMatrixController(config)
            }
            return instance!!
        }
    }
}
