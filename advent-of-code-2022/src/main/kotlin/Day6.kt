fun main() {
    Day6.part1()
    Day6.part2()
}

object Day6 {

    fun findMarkerIndex(input: String, numOfUniqueChar: Int): Int {
        val checkedStr = hashMapOf<Char, Int>()
        var start = 0
        var result = 0

        for (i in 0 until input.length) {
            if (checkedStr.contains(input[i])) {
                val t = checkedStr.getOrDefault(input[i], 0) + 1

                if (t > start) {
                    start = t
                }
            }

            checkedStr.put(input[i], i)

            if (i - start + 1 == numOfUniqueChar) {
                result = i + 1
                break
            }
        }

        return result
    }
    fun part1() {
        val input = object{}::class.java.getResource("day6_1.txt")?.readText(Charsets.UTF_8).orEmpty()
        val result = findMarkerIndex(input, 4)

        println(result)
    }

    fun part2() {
        val input = object{}::class.java.getResource("day6_2.txt")?.readText(Charsets.UTF_8).orEmpty()
        val result = findMarkerIndex(input, 14)

        println(result)
    }
}