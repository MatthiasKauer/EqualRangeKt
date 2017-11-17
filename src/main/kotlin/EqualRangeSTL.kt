fun <T: Comparable<T>> lowerBound(xs: List<T>, x0: T) : Int {
    if (xs is RandomAccess) {
        return lowerBoundSTL(xs, x0)
    } else {
        throw NotImplementedError("sorry")
    }
}

fun <T: Comparable<T>> upperBound(xs: List<T>, x0: T) : Int {
    if (xs is RandomAccess) {
        return upperBoundSTL(xs, x0)
    } else {
        throw NotImplementedError("sorry")
    }
}

fun <T: Comparable<T>> equalRange(xs: List<T>, x0: T) : Pair<Int, Int> {
    if (xs is RandomAccess) {
        return equalRangeSTL(xs, x0)
    } else {
        throw NotImplementedError("sorry")
    }
}


// modeled after C++ STL
fun <T: Comparable<T>> lowerBoundSTL(xs: List<T>, x0: T): Int {
    var len = xs.size
    var first = 0

    while (len != 0) {
        val l2: Int = len / 2
        val m = first + l2
        val mv = xs[m]

        if (mv < x0) {
            first = m + 1
            len -= l2 + 1
        } else {
            len = l2
        }
    }
    return first
}


// modeled after C++ STL
fun <T: Comparable<T>> upperBoundSTL(xs: List<T>, x0: T) : Int {
    var len = xs.size
    var first = 0

    while (len != 0) {
        val l2: Int = len / 2
        val m = first + l2
        val mv = xs[m]

        if (x0 < mv) {
            len = l2
        } else {
            first = m + 1
            len -= l2 + 1
        }
    }
    return first
}


// modeled after C++ STL
fun <T: Comparable<T>> equalRangeSTL(xs: List<T>, x0: T) : Pair<Int, Int> {
    var len = xs.size
    var first = 0
    var last = xs.size

    while (len != 0) {
        val l2 = len / 2
        val m = first + l2
        val mv = xs[m]
        if (mv < x0) {
            first = m + 1
            len -= l2 + 1
        } else if(x0 < mv) {
            last = m
            len = l2
        } else {
            val mpl = m + 1
            return Pair(first + lowerBoundSTL(xs.subList(fromIndex = first, toIndex = m), x0),
                    mpl + upperBoundSTL(xs.subList(fromIndex = mpl, toIndex = last),x0))
        }
    }
    return Pair(first, first)
}