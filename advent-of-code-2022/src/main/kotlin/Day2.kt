fun main(args: Array<String>) {
    part1()
    part2()
}

fun getScore(input: String): Int {
    val scoreMap = hashMapOf("A" to 1, "B" to 2, "C" to 3, "X" to 1, "Y" to 2, "Z" to 3)
    val sp = input.split(" ")
    val opponent = scoreMap.getOrDefault(sp[0], 0)
    val me = scoreMap.getOrDefault(sp[1], 0)
    val result = Math.abs(if (me - opponent > 0) me - opponent - 3 else me - opponent)

    return me + when(result) {
        2 -> 6
        1 -> 0
        0 -> 3
        else -> 0
    }
}

fun part1() {
    val input = object{}::class.java.getResource("day2_1.txt")?.readText(Charsets.UTF_8).orEmpty().split("\n")
    val result = input.sumOf { getScore(it) }

    println(result)
}

fun getScorePart2(input: String): Int {
    val scoreMap = hashMapOf("X" to 1, "Y" to 2, "Z" to 3)
    val sp = input.split(" ")
    val opponent = sp[0]
    val me = scoreMap.getOrDefault(sp[1], 0)
    val newValue = when(opponent) {
        "A" -> if (me + 2 > 3) me - 1 else me + 2
        "B" -> me
        "C" -> if (me + 1 > 3) me - 2 else me + 1
        else -> 0
    }
    val newChar = scoreMap.entries.find { it.value == newValue }?.key ?: ""
    val newInput = "$opponent $newChar"

    return getScore(newInput)
}

fun part2() {
    val input = object{}::class.java.getResource("day2_2.txt")?.readText(Charsets.UTF_8).orEmpty().split("\n")
    val result = input.sumOf { getScorePart2(it) }

    println(result)
}