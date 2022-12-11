import kotlin.math.sign

fun main() {
    Day8.part1()
}

object Day8 {
    fun getGrid(input: String): List<List<Int>> {
        return input.split("\n").map { s -> s.toCharArray().map { c -> c.toString().toInt() } }
    }

    fun part1() {
        val input = object{}::class.java.getResource("day8_1.txt")?.readText(Charsets.UTF_8).orEmpty()
        val grid = getGrid(input)
        var result = 0

        grid.forEachIndexed{ rowIdx, row ->
            row.forEachIndexed { colIdx, col ->
                if (rowIdx == 0 || rowIdx == grid.size - 1 || colIdx == 0 || colIdx == row.size - 1) {
                    result++
                } else {
                    val left = (0 until colIdx).toList().foldRight(true){ i, acc -> acc && col > grid[rowIdx][i]  }
                    val right = (colIdx + 1 until row.size).toList().foldRight(true){ i, acc -> acc && col > grid[rowIdx][i] }
                    val top = (0 until rowIdx).toList().foldRight(true){ i, acc -> acc && col > grid[i][colIdx] }
                    val bottom = (rowIdx + 1 until grid.size).toList().foldRight(true){ i, acc -> acc && col > grid[i][colIdx] }

                    if (left || right || top || bottom) {
                        result++
                    }
                }
            }
        }

        println(result)
    }
}