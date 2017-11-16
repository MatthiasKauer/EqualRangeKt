fun <T: Comparable<T>> lowerBound(xs: List<T>, x0: T) : Int {
    if (xs is RandomAccess) {
        return lowerBound1(xs, x0)
    } else {
        throw NotImplementedError("sorry")
    }
}

fun <T: Comparable<T>> upperBound(xs: List<T>, x0: T) : Int {
    if (xs is RandomAccess) {
        return upperBound0(xs, x0)
    } else {
        throw NotImplementedError("sorry")
    }
}

fun <T: Comparable<T>> equalRange(xs: List<T>, x0: T) : Pair<Int, Int> {
    if (xs is RandomAccess) {
        return equalRange0(xs, x0)
    } else {
        throw NotImplementedError("sorry")
    }
}


// modeled after C++ STL
fun <T: Comparable<T>> lowerBound0(xs: List<T>, x0: T): Int {
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


// taken from C++ STL
fun <T: Comparable<T>> upperBound0(xs: List<T>, x0: T) : Int {
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


// taken from C++ STL
fun <T: Comparable<T>> equalRange0(xs: List<T>, x0: T) : Pair<Int, Int> {
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
            return Pair(first + lowerBound0(xs.subList(fromIndex = first, toIndex = m), x0),
                        mpl + upperBound(xs.subList(fromIndex = mpl, toIndex = last),x0))
        }
    }
    return Pair(first, first)
}


data class State(var len: Int, var first: Int, var last: Int) {
    fun m(): Int { return first + l2()}
    fun l2(): Int { return len / 2}
}
fun <T: Comparable<T>> boundBase(xs: List<T>, x0: T, func : (State) -> Unit) : Int {
    var state = State(len = xs.size, first = 0, last = xs.size);

    while (state.len != 0) {
        func(state)
    }
    return state.first
}
fun <T: Comparable<T>> lowerBound1(xs: List<T>, x0: T): Int {
    val func : (State) -> Unit = { s: State ->
        if (xs[s.m()] < x0) {
            s.first = s.m() + 1; s.len -= s.l2() + 1
        } else {
            s.len = s.l2()
        }
    }
    return boundBase(xs, x0, func)
}
