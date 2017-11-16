import org.junit.Assert
import org.junit.Before
import org.junit.Test


class WrappedInt(val i: Int) : Comparable<WrappedInt> {
    override fun compareTo(other: WrappedInt) : Int {
        return i - other.i
    }
}

fun <T: Comparable<T>> checkEqualRange(range: Pair<Int, Int>, xs: List<T>, x0: T) : Unit
{
    Assert.assertEquals(range.first, lowerBound(xs, x0))
    Assert.assertEquals(range.second, upperBound(xs, x0))
    Assert.assertEquals(range, equalRange(xs, x0))

    Assert.assertEquals(lowerBound(xs, x0), lowerBoundShort(xs, x0))
    Assert.assertEquals(upperBound(xs, x0), upperBoundShort(xs, x0))
    Assert.assertEquals(equalRange(xs, x0), equalRangeShort(xs, x0))
}

class GenericEqualRangeTest {

    private val xs = listOf(
            WrappedInt(10),
            WrappedInt(10),
            WrappedInt(10),
            WrappedInt(20),
            WrappedInt(30),
            WrappedInt(30),
            WrappedInt(30),
            WrappedInt(50),
            WrappedInt(50),
            WrappedInt(60),
            WrappedInt(80),
            WrappedInt(80)
    )

    @Before
    fun setUp() {
        Assert.assertEquals(xs.sorted(), xs)
    }

    @Test
    fun simple() {
        val range = Pair(0, 0)
        val x0 = WrappedInt(0)
        Assert.assertEquals(range.first, lowerBound(xs, x0))
        Assert.assertEquals(range.second, upperBound(xs, x0))
        Assert.assertEquals(range, equalRange(xs, x0))
    }

    @Test
    fun missingInMiddle() {
        checkEqualRange(Pair(4,4), xs, WrappedInt(25))
    }

    @Test
    fun missingAtHead() {
        checkEqualRange(Pair(0, 0), xs, WrappedInt(5))
    }

    @Test
    fun missingAtTail() {
        checkEqualRange(Pair(xs.size, xs.size), xs, WrappedInt(95))
    }

    @Test
    fun existingInMiddle() {
        checkEqualRange(Pair(4, 7), xs, WrappedInt(30))
    }

    @Test
    fun existingAtTail() {
        checkEqualRange(Pair(xs.size - 2, xs.size), xs, WrappedInt(80))
    }

    @Test
    fun existingAtHead() {
        checkEqualRange(Pair(0, 3), xs, WrappedInt(10))
    }
}