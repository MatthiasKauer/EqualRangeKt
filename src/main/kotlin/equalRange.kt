
fun lowerBound(xs: List<Int>, x0: Int): Int {
    val i0 = xs.binarySearch(x0)
    var iRet = if (i0 < 0) (i0 + 1) * (-1) else i0

    while(iRet > 0 && xs[iRet - 1].compareTo(x0) == 0) iRet -= 1

    return iRet
}