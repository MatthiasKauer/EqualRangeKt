
fun lowerBound(xs: List<Int>, x0: Int): Int {
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