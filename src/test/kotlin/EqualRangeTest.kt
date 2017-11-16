import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class EqualRangeTest {
    private val xs = listOf(10, 10, 10, 20, 30, 30, 30, 50, 50, 60, 80, 80)

    fun checkEqualRange(range: Pair<Int, Int>, x0: Int) {
        assertEquals(range.first, lowerBound(xs, x0))
        assertEquals(range.second, upperBound(xs, x0))
        assertEquals(range, equalRange(xs, x0))

        assertEquals(lowerBound(xs, x0), lowerBoundShort(xs, x0))
        assertEquals(upperBound(xs, x0), upperBoundShort(xs, x0))
        assertEquals(equalRange(xs, x0), equalRangeShort(xs, x0))
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
        checkEqualRange(Pair(xs.size, xs.size), 95)
    }

    @Test
    fun existingInMiddle() {
        checkEqualRange(Pair(4, 7), 30)
    }

    @Test
    fun existingAtTail() {
        checkEqualRange(Pair(xs.size - 2, xs.size), 80)
    }

    @Test
    fun existingAtHead() {
        checkEqualRange(Pair(0, 3), 10)
    }
}
