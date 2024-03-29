package dev.joseluisgs.repositories.matriz

interface Matriz<T> {
    operator fun get(row: Int, col: Int): T?

    operator fun set(row: Int, col: Int, value: T?)

    val rows: Int

    val cols: Int

    fun clear()

    fun addRows(numberOfRows: Int)

    fun addCols(numberOfCols: Int)

    fun removeRows(numberOfRows: Int)

    fun removeCols(numberOfCols: Int)

    override fun toString(): String

    fun clone(): Matriz<T>
}
