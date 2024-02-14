import dev.joseluisgs.ColaProcesosLista
import dev.joseluisgs.Proceso
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ColaProcesosListaTest {

    var cola = ColaProcesosLista()

    @BeforeEach
    fun setUp() {
        cola = ColaProcesosLista()
        cola.enqueue(Proceso(1, 1, 3))
        cola.enqueue(Proceso(2, 2, 1))
        cola.enqueue(Proceso(3, 3, 2))
    }

    @Test
    fun enqueue() {
        cola.enqueue(Proceso(4, 4, 4))
        org.junit.jupiter.api.assertAll(
            { assertEquals(4, cola.size()) },
            { assertEquals(3, cola.first().prioridad) },
            { assertEquals(1, cola.first().tiempo) },
            { assertEquals(1, cola.first().id) }
        )
    }

    @Test
    fun dequeue() {
        val p = cola.dequeue()
        org.junit.jupiter.api.assertAll(
            { assertEquals(2, cola.size()) },
            { assertEquals(1, p?.id) },
            { assertEquals(3, p?.prioridad) },
            { assertEquals(1, p?.tiempo) },
            { assertEquals(2, cola.first().id) },
            { assertEquals(1, cola.first().prioridad) },
            { assertEquals(2, cola.first().tiempo) }
        )
    }

    @Test
    fun isEmpty() {
        cola.dequeue()
        cola.dequeue()
        cola.dequeue()
        org.junit.jupiter.api.assertAll(
            { assertEquals(true, cola.isEmpty()) }
        )
    }

    @Test
    fun first() {
        org.junit.jupiter.api.assertAll(
            { assertEquals(1, cola.first().id) },
            { assertEquals(3, cola.first().prioridad) },
            { assertEquals(1, cola.first().tiempo) }
        )
    }

    @Test
    fun size() {
        org.junit.jupiter.api.assertAll(
            { assertEquals(3, cola.size()) }
        )
    }
}