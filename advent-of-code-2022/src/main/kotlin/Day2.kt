fun main(args: Array<String>) {
    val allPossibilities = hashMapOf(
        "A X" to 4, "A Y" to 8, "A Z" to 3,
        "B X" to 1, "B Y" to 5, "B Z" to 9,
        "C X" to 7, "C Y" to 2, "C Z" to 6
    )

    part1(allPossibilities)
}

fun getScore(input: String): Int {
    val sp = input.split(" ")

    return 1
}

fun part1(allPossibilities: HashMap<String, Int>) {
    val input = object{}::class.java.getResource("day2_1.txt")?.readText(Charsets.UTF_8).orEmpty().split("\n")
    val result = input.sumOf { allPossibilities.getOrDefault(it, 0) }

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