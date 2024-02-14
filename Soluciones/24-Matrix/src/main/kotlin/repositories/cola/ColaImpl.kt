package dev.joseluisgs.repositories.cola

class ColaImpl<T> : Cola<T> {
    private val cola: ArrayDeque<T> = ArrayDeque()
    override fun queue(elem: T) {
        cola.addLast(elem)
    }

    override fun dequeue(): T {
        return cola.removeFirst()
    }

    override fun isEmpty(): Boolean {
        return cola.isEmpty()
    }

    override fun size(): Int {
        return cola.size
    }

    override fun first(): T {
        return cola.first()
    }

    override fun get(index: Int): T {
        return cola.elementAt(index)
    }

    override fun toString(): String {
        return "ColaImp(cola=$cola)"
    }
}
