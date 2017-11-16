fun lowerBound(xs: List<Int>, x0: Int) : Int {
    if (xs is RandomAccess) {
        return lowerBound0(xs, x0)
    } else {
        throw NotImplementedError("sorry")
    }
}

// modeled after C++ STL
fun lowerBound0(xs: List<Int>, x0: Int): Int {
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

fun upperBound(xs: List<Int>, x0: Int) : Int {
    if (xs is RandomAccess) {
        return upperBound0(xs, x0)
    } else {
        throw NotImplementedError("sorry")
    }
}

// taken from C++ STL
fun upperBound0(xs: List<Int>, x0: Int) : Int {
    var len = xs.size
    var first = 0

    while (len != 0) {
        val l2: Int = len / 2
        val m = first + l2
        val mv = xs[m]

        if (x0.compareTo(mv) < 0) {
            len = l2
        } else {
            first = m + 1
            len -= l2 + 1
        }
    }
    return first
}

fun equalRange(xs: List<Int>, x0: Int) : Pair<Int, Int> {
    if (xs is RandomAccess) {
        return equalRange0(xs, x0)
    } else {
        throw NotImplementedError("sorry")
    }
}

// taken from C++ STL
fun equalRange0(xs: List<Int>, x0: Int) : Pair<Int, Int> {
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
            return Pair(first + lowerBound0(xs.subList(fromIndex = first, toIndex = m), x0),
                        mpl + upperBound(xs.subList(fromIndex = mpl, toIndex = last),x0))
        }
    }
    // TODO: empty range could throw or return null?
    return Pair(first, first)
}
