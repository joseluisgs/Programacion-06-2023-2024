package dev.joseluisgs.types

class Box<T>(var item: T) {
    fun getItem(): T {
        return item
    }

    fun setItem(item: T) {
        this.item = item
    }
}