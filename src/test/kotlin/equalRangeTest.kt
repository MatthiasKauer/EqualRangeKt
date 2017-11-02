
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class EqualRangeTest {

    private val xs = listOf(10, 10, 10, 20, 30, 30, 30, 50, 50, 60, 80, 80)

    @Before
    fun setUp() {
        assertEquals(xs.sorted(), xs)
    }

    @Test
    fun simple() {
        assertEquals(4, 2 * 2)
    }

    @Test
    fun lowerBoundForMissingInMiddle() {
        val i0 = lowerBound(xs, 25)
        assertEquals(4, i0)
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

    @Test
    fun lowerBoundForExistingInMiddle() {
        val i0 = lowerBound(xs, 30)
        assertEquals(4, i0)
    }

    @Test
    fun lowerBoundForExistingAtTail() {
        val i0 = lowerBound(xs, 80)
        assertEquals(xs.size - 2, i0)
    }

    @Test
    fun lowerBoundForExistingAtHead() {
        val i0 = lowerBound(xs, 10)
        assertEquals(0, i0)
    }

}