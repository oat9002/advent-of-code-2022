fun main() {
    day3part1()
}

fun charToInt(c: Char): Int {
    return when(c.code) {
        in 97 .. 122 -> c.code - 96
        in 65 .. 90 -> c.code - 38
        else -> 0
    }
}

fun findShareItemPriority(input: String): Int {
    val r1 = input.substring(0, input.length / 2)
    val r2 = input.substring(input.length / 2, input.length)
    val repeated = HashMap<Char, Boolean>()
    var result = 0

    r1.forEach { repeated.put(it, false) }
    r2.forEach {
        val dup = repeated.get(it)

        if (dup != null) {
            if (!dup) {
                result += charToInt(it)
                repeated.put(it, true)
            }
        }
    }

    return result
}

fun day3part1() {
    val input = object{}::class.java.getResource("day3_1.txt")?.readText(Charsets.UTF_8).orEmpty().split("\n")
    val result = input.sumOf { findShareItemPriority(it) }

    println(result)
}