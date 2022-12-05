fun main() {
    Day4.part1()
}

object Day4 {
    fun isFullyDuplicate(input: String): Boolean {
        val elfs = input.split(",")
        val firstElf = elfs[0].split("-").map { it.toInt() }
        val secondElf = elfs[1].split("-").map { it.toInt() }
        val compare = { x1: Int, x2: Int, y1: Int, y2: Int -> x1 >= y1 && x2 <= y2 }

        if (compare(firstElf[0], firstElf[1], secondElf[0], secondElf[1]) || compare(secondElf[0], secondElf[1], firstElf[0], firstElf[1])) {
            return true
        }

        return false
    }

    fun part1() {
        val input = object{}::class.java.getResource("day4_1.txt")?.readText(Charsets.UTF_8).orEmpty().split("\n")
        val result = input.map { isFullyDuplicate(it) }.foldRight(0) { isFullyDup, count -> if (isFullyDup)  count + 1 else count }

        println(result)
    }
}