fun main(args: Array<String>) {
    part1()
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

fun part2(allPossibilities: HashMap<String, Int>) {
    val input = object{}::class.java.getResource("day2_2.txt")?.readText(Charsets.UTF_8).orEmpty().split("\n")
    val replaceMap = hashMapOf(
        "A X" to "A Y", "A Y" to "A X", "A Z" to "A Y",
        "B X" to " ", "B Y" to 5, "B Z" to 9,
        "C X" to 7, "C Y" to 2, "C Z" to 6
    )

}