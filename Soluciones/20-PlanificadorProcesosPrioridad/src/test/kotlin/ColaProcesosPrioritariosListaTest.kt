import dev.joseluisgs.ColaProcesosPrioritariosLista
import dev.joseluisgs.Proceso
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ColaProcesosPrioritariosListaTest {

    var cola = ColaProcesosPrioritariosLista()

    @BeforeEach
    fun setUp() {
        cola = ColaProcesosPrioritariosLista()
        cola.enqueue(Proceso(1, 1, 3))
        cola.enqueue(Proceso(2, 2, 1))
        cola.enqueue(Proceso(3, 3, 2))
    }

    @Test
    fun enqueue() {
        cola.enqueue(Proceso(4, 4, 4))
        // Cuidado con los test que dependen del orden si esta puesto que se ordene en insercción o en salida
        org.junit.jupiter.api.assertAll(
            { assertEquals(4, cola.size()) },
            { assertEquals(1, cola.first().id) },
            { assertEquals(3, cola.first().prioridad) },
            { assertEquals(1, cola.first().tiempo) }
        )
    }

    @Test
    fun dequeue() {
        val p = cola.dequeue()
        org.junit.jupiter.api.assertAll(
            { assertEquals(2, cola.size()) },
            { assertEquals(2, p?.id) },
            { assertEquals(1, p?.prioridad) },
            { assertEquals(2, p?.tiempo) }
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