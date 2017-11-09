fun lowerBound(xs: List<Int>, x0: Int) : Int {
    if (xs is RandomAccess) {
        return lowerBound1(xs, x0)
    } else {
        throw NotImplementedError("sorry")
    }
}

// modeled after C++ STL
fun lowerBound1(xs: List<Int>, x0: Int): Int {
    var len = xs.size
    var first = 0

    while (len != 0) {
        val l2: Int = len / 2
        val m = first + l2
        val mv = xs[m]

        if (mv.compareTo(x0) < 0) {
            first = m + 1
            len -= l2 + 1
        } else {
            len = l2
        }
    }
    return first
}

fun lowerBound0(xs: List<Int>, x0: Int): Int {
    val i0 = xs.binarySearch(x0)
    if (i0 < 0) return (i0 + 1) * (-1)

//    return trivialWalkSearch(xs, x0, iRet)
    return binaryDiffSearch(xs, fromIndex = 0, toIndex = i0 + 1, x0=x0)
}

fun trivialWalkSearch(xs: List<Int>, x0: Int, i0: Int): Int {
    var iRet = i0
    while (iRet > 0 && xs[iRet - 1].compareTo(x0) == 0) iRet -= 1
    return iRet
}

fun binaryDiffSearch(xs: List<Int>, fromIndex: Int = 0, toIndex: Int = xs.size,  x0: Int): Int {
    var low = fromIndex
    var high = toIndex - 1

    fun comparison(i: Int): Int {
        val thisEqual = xs[i].compareTo(x0) == 0
        if (i == 0) {
            if (thisEqual) {
                return 0 // left border is the transition we're looking for
            } else {
                return -1
            }
        }

        val prevEqual = xs[i-1].compareTo(x0) == 0
        if (thisEqual && !prevEqual) {
            return 0 // found the transition
        }
        if (!thisEqual) {
            return -1
        } else {
            return 1
        }
    }

    while (low <= high) {
        val mid = (low + high).ushr(1) // safe from overflows
//        val midVal = xs[mid]
        val cmp = comparison(mid)

        if (cmp < 0)
            low = mid + 1
        else if (cmp > 0)
            high = mid - 1
        else
            return mid // key found
    }
    return -(low + 1)  // key not found
}

fun exponentialSearch(xs: List<Int>, x0: Int, i0: Int): Int {
    var step = 1

//    while (iRet > 0 && xs[iRet -  1].compareTo(x0) == 0) iRet -= 1
    while(true) {
        if (i0 - step < 0) {
            step = i0
        }

        val iX = i0 - step
        val nextEqual = xs[iX + 1].compareTo(x0) == 0
        val thisEqual = xs[iX].compareTo(x0) == 0

//        val isTransition = { i: Int -> xs[i + 1].compareTo(x0) == 0 && (xs[i].compareTo(x0) != 0 || i ==0) }

        if (nextEqual && (!thisEqual || iX == 0)) return iX

        if (thisEqual) {
            step *= 2
        } else {
            step /= 2
        }
    }

    return 0
}

fun upperBound(xs: List<Int>, x0: Int) : Int {
    if (xs is RandomAccess) {
        return upperBound1(xs, x0)
    } else {
        throw NotImplementedError("sorry")
    }
}

// taken from C++ STL
fun upperBound1(xs: List<Int>, x0: Int) : Int {
    var len = xs.size
    var first = 0

    while (len != 0) {
        val l2: Int = len / 2
        val m = first + l2
        val mv = xs[m]

//        if (mv.compareTo(x0) >= 0) {
        if (x0.compareTo(mv) < 0) {
            len = l2
        } else {
            first = m + 1
            len -= l2 + 1
        }
    }
    return first
}


// taken from C++ STL
fun equalRange1(xs: List<Int>, x0: Int) : Pair<Int, Int> {
    var len = xs.size
    var first = 0
    var last = xs.size

    while (len != 0) {
        val l2 = len / 2
        val m = first + l2
        val mv = xs[m]
        if (mv.compareTo(x0) < 0 ) {
            first = m + 1
            len -= l2 + 1
        } else if(x0.compareTo(mv) < 0) {
            last = m
            len = l2
        } else {
            val mpl = m + 1
            return Pair(first + lowerBound1(xs.subList(fromIndex = first, toIndex = m), x0),
                        mpl + upperBound(xs.subList(fromIndex = mpl, toIndex = last),x0))
        }
    }
    // TODO: empty range could throw or return null?
    return Pair(first, first)
}
