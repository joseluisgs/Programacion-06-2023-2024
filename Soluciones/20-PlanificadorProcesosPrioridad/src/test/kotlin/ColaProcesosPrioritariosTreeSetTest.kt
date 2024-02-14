import dev.joseluisgs.ColaProcesosPrioritariosTreeSet
import dev.joseluisgs.Proceso
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class ColaProcesosPrioritariosTreeSetTest {
    var cola = ColaProcesosPrioritariosTreeSet()

    @BeforeEach
    fun setUp() {
        cola = ColaProcesosPrioritariosTreeSet()
        cola.enqueue(Proceso(1, 1, 3))
        cola.enqueue(Proceso(2, 2, 1))
        cola.enqueue(Proceso(3, 3, 2))
    }

    @Test
    fun enqueue() {
        cola.enqueue(Proceso(4, 4, 4))
        assertAll(
            { assertEquals(4, cola.size()) },
            { assertEquals(2, cola.first()?.id) },
            { assertEquals(1, cola.first()?.prioridad) },
            { assertEquals(2, cola.first()?.tiempo) }
        )
    }

    @Test
    fun dequeue() {
        val p = cola.dequeue()
        assertAll(
            { assertEquals(2, cola.size()) },
            { assertEquals(2, p?.id) },
            { assertEquals(1, p?.prioridad) },
            { assertEquals(2, p?.tiempo) },
            { assertEquals(3, cola.first()?.id) },
            { assertEquals(3, cola.first()?.tiempo) },
            { assertEquals(2, cola.first()?.prioridad) },
        )
    }

    @Test
    fun isEmpty() {
        cola.dequeue()
        cola.dequeue()
        cola.dequeue()
        assertAll(
            { assertEquals(true, cola.isEmpty()) }
        )
    }

    @Test
    fun first() {
        assertAll(
            { assertEquals(2, cola.first()?.id) },
            { assertEquals(1, cola.first()?.prioridad) },
            { assertEquals(2, cola.first()?.tiempo) }
        )
    }

    @Test
    fun size() {
        assertAll(
            { assertEquals(3, cola.size()) }
        )
    }
}