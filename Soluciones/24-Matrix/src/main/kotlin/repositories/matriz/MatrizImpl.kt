package dev.joseluisgs.repositories.matriz


/**
 * Clase que representa una matriz de objetos de tipo T.
 *
 * @param <T> Tipo de los objetos que se almacenan en la matriz.
</T> */
class MatrizImpl<T>(
    /**
     * Devuelve el número de filas de la matriz.
     *
     * @return Número de filas de la matriz.
     */
    override var rows: Int,
    /**
     * Devuelve el número de columnas de la matriz.
     *
     * @return Número de columnas de la matriz.
     */
    override var cols: Int
) : Matriz<T> {
    val matriz: MutableList<MutableList<T?>> = mutableListOf()

    /**
     * Constructor por defecto. Inicia todo a a null.
     *
     * @param rows    Número de filas de la matriz.
     * @param columns Número de columnas de la matriz.
     */
    init {
        for (i in 0..<rows) {
            // Creamos por cada fila, la columnas a null
            val fila = MutableList<T?>(cols) { null }
            for (j in 0..<cols) {
                fila.add(null)
            }
            matriz.add(fila)
        }
    }

    /**
     * Devuelve el valor de la posición indicada.
     *
     * @param row Fila de la posición.
     * @param col Columna de la posición.
     */
    override operator fun get(row: Int, col: Int): T? {
        return matriz[row][col]
    }

    /**
     * Establece el valor de la posición indicada.
     *
     * @param row   Fila de la posición.
     * @param col   Columna de la posición.
     * @param value Valor a establecer.
     */
    override operator fun set(row: Int, col: Int, value: T?) {
        matriz[row][col] = value
    }

    /**
     * Limpia la matriz, poniendo todo a null.
     */
    override fun clear() {
        // La inicializamos a null creando los arrayList
        for (i in 0..<rows) {
            for (j in 0..<cols) {
                this[i, j] = null
            }
        }
    }

    /**
     * Añade filas al final de la matriz.
     *
     * @param numberOfRows Número de filas a añadir.
     */
    override fun addRows(numberOfRows: Int) {
        for (i in 0 until numberOfRows) {
            // Creamos por cada fila, la columnas a null
            val fila = mutableListOf<T?>()
            for (j in 0..<cols) {
                fila.add(null)
            }
            matriz.add(fila)
        }
        this.rows += numberOfRows
    }

    /**
     * Añade columnas al final de la matriz.
     *
     * @param numberOfCols Número de columnas a añadir.
     */
    override fun addCols(numberOfCols: Int) {
        for (i in 0..<rows) {
            for (j in 0..<numberOfCols) {
                matriz[i].add(null)
            }
        }
        this.cols += numberOfCols
    }

    /**
     * Elimina las filas indicadas.
     *
     * @param numberOfRows Número de filas a eliminar desde el final
     */
    override fun removeRows(numberOfRows: Int) {
        for (i in 0..<numberOfRows) {
            matriz.removeAt(matriz.size - 1)
        }
        this.rows -= numberOfRows
    }

    /**
     * Elimina las columnas indicadas al final.
     *
     * @param numberOfCols Número de columnas a eliminar.
     */
    override fun removeCols(numberOfCols: Int) {
        for (i in 0..<rows) {
            for (j in 0..<numberOfCols) {
                matriz[i].removeAt(matriz[i].size - 1)
            }
        }
        this.cols -= numberOfCols
    }

    /**
     * Devuelve la representación en String de la matriz.
     *
     * @return Representación en String de la matriz.
     */
    override fun toString(): String {
        val sb = StringBuilder()
        for (i in 0..<rows) {
            for (j in 0..<cols) {
                if (this.get(i, j) != null) {
                    sb.append("[").append(this.get(i, j)).append("]")
                } else {
                    sb.append("[ ]")
                }
            }
            sb.append("\n")
        }
        return sb.toString()
    }

    /**
     * Devuelve una copia clone de la matriz.
     *
     * @return Copia clone de la matriz.
     */
    override fun clone(): Matriz<T> {
        val clone: Matriz<T> = MatrizImpl(this.rows, this.cols)
        for (i in 0..<this.rows) {
            for (j in 0..<this.cols) {
                clone[i, j] = this[i, j]
            }
        }
        return clone
    }
}
