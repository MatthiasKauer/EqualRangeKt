data class State(var len: Int, var first: Int, var last: Int) {
    fun m(): Int { return first + l2()}
    fun l2(): Int { return len / 2}
}
fun <T: Comparable<T>> bound0(xs: List<T>, func : (State) -> Unit) : Int {
    var state = State(len = xs.size, first = 0, last = xs.size);
    while (state.len != 0) { func(state) }
    return state.first
}
fun <T: Comparable<T>> bound1(xs: List<T>, pred: (T) -> Boolean): Int {
    val func : (State) -> Unit = { s: State ->
        if (pred(xs[s.m()])) {
            s.first = s.m() + 1; s.len -= s.l2() + 1
        } else {
            s.len = s.l2()
        }
    }
    return bound0(xs, func)
}
fun <T: Comparable<T>> lowerBoundShort(xs: List<T>, x0: T) = bound1(xs, { t: T -> t < x0 })
fun <T: Comparable<T>> upperBoundShort(xs: List<T>, x0: T) = bound1(xs, { t: T -> t <= x0 })
fun <T: Comparable<T>> equalRangeShort(xs: List<T>, x0: T) = Pair(lowerBoundShort(xs, x0), upperBoundShort(xs, x0))