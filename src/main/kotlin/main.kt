fun getGreeting(): String {
    val words = mutableListOf<String>()
    words.add("Hello,")
    words.add("world!")

    return words.joinToString(separator = " ")
}

fun main(args: Array<String>) {
    println(getGreeting())

    val xs = mutableListOf(1,2,3,3,5,5,6,8,3)
    xs.sort()

    println(xs)
    val iInsert = lowerBound(xs, 3)
    val iI2 = upperBound(xs, 3)
    println("iIns: $iInsert and $iI2")

    val eqRange = equalRange1(xs, 3)
    println("eqRange: $eqRange")

    xs.add(iInsert, 100)
    xs.add(iI2 + 1, 100)
    println(xs)
}
