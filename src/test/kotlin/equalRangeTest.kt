import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class EqualRangeTest {
    private val xs = listOf(10, 10, 10, 20, 30, 30, 30, 50, 50, 60, 80, 80)

    fun <T> checkEqualRange(range: Pair<T, T>, x0: Int) {
        assertEquals(range.first, lowerBound(xs, x0))
        assertEquals(range.second, upperBound(xs, x0))
        assertEquals(range, equalRange1(xs, x0))
    }

    @Before
    fun setUp() {
        assertEquals(xs.sorted(), xs)
    }

    @Test
    fun simple() {
        assertEquals(4, 2 * 2)
    }

    @Test
    fun missingInMiddle() {
        checkEqualRange(Pair(4,4), 25)
    }

    @Test
    fun missingAtHead() {
        checkEqualRange(Pair(0, 0), 5)
    }

    @Test
    fun missingAtTail() {
        val x0 = 95
        assertEquals(xs.size, lowerBound(xs, x0))
        assertEquals(xs.size, upperBound(xs, x0))
        assertEquals(Pair(xs.size, xs.size), equalRange1(xs, x0))
    }

    @Test
    fun existingInMiddle() {
        val x0 = 30
        assertEquals(4, lowerBound(xs, x0))
        assertEquals(7, upperBound(xs, x0))
        assertEquals(Pair(4, 7), equalRange1(xs, x0))
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