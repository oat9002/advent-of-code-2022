fun main() {
    day3part1()
    day3part2()
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

        if (dup != null && !dup) {
            result += charToInt(it)
            repeated.put(it, true)
        }
    }

    return result
}

fun findShareItemPriorityPart2(input: List<String>): Int {
    val repeated = HashMap<Char, Int>()
    var result = 0

    input[0].forEach { repeated.put(it, 1) }
    input[1].forEach {
        val count = repeated.get(it)

        if (count != null && count == 1) {
            repeated.put(it, 2)
        }
    }
    input[2].forEach {
        val count = repeated.get(it)

        if (count != null && count == 2) {
            result += charToInt(it)
            repeated.put(it, 3)
        }
    }

    return result
}

fun day3part1() {
    val input = object{}::class.java.getResource("day3_1.txt")?.readText(Charsets.UTF_8).orEmpty().split("\n")
    val result = input.sumOf { findShareItemPriority(it) }

    println(result)
}

fun day3part2() {
    val input = object{}::class.java.getResource("day3_2.txt")?.readText(Charsets.UTF_8).orEmpty().split("\n").chunked(3)
    val result = input.sumOf { findShareItemPriorityPart2(it) }

    println(result)
}