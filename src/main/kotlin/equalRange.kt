
fun lowerBound(xs: List<Int>, x0: Int): Int {
    val i0 = xs.binarySearch(x0)
    val iInsert = if (i0 < 0) (i0 + 1) * (-1) else i0
    return iInsert
}