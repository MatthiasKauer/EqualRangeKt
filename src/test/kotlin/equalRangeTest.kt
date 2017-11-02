
import org.junit.Test
import org.junit.Assert.*

class EqualRangeTest {

    private val xs = listOf(10,20,30,30,50,50,60,80,30).sorted()

    @Test
    fun simple() {
        assertEquals(4, 2 * 2)
    }

    @Test
    fun lowerBoundForMissingInMiddle() {
        val i0 = lowerBound(xs, 25)
        assertEquals(2, i0)
    }

    @Test
    fun lowerBoundForMissingAtHead() {
        val i0 = lowerBound(xs, 5)
        assertEquals(0, i0)
    }

    @Test
    fun lowerBoundForMissingAtTail() {
        val i0 = lowerBound(xs, 95)
        assertEquals(xs.size, i0)
    }

}