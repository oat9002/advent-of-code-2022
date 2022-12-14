import kotlin.math.sign

fun main() {
    Day8.part1()
    Day8.part2()
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

    fun part2() {
        val input = object{}::class.java.getResource("day8_2.txt")?.readText(Charsets.UTF_8).orEmpty()
        val grid = getGrid(input)
        var result = 0
        var a = ""

        grid.forEachIndexed{ rowIdx, row ->
            row.forEachIndexed { colIdx, col ->
                if (rowIdx != 0 && rowIdx != grid.size - 1 && colIdx != 0 && colIdx != row.size - 1) {
                    val left = (0 until colIdx).toList().foldRight(Pair(0, false)){ i, acc ->
                        if (acc.second) {
                            acc
                        } else if (col ==  grid[rowIdx][i]) {
                            Pair(acc.first + 1, true)
                        } else if (col > grid[rowIdx][i]) {
                            Pair(acc.first + 1, acc.second)
                        } else {
                            Pair(acc.first, true)
                        }
                    }
                    val right = (colIdx + 1 until row.size).toList().fold(Pair(0, false)){ acc, i ->
                        if (acc.second) {
                            acc
                        } else if(col == grid[rowIdx][i]) {
                            Pair(acc.first + 1, true)
                        } else if (col > grid[rowIdx][i]) {
                            Pair(acc.first + 1, acc.second)
                        } else {
                            Pair(acc.first, true)
                        }
                    }
                    val top = (0 until rowIdx).toList().foldRight(Pair(0, false)){ i, acc ->
                        if (acc.second) {
                            acc
                        } else if (col == grid[i][colIdx]) {
                            Pair(acc.first + 1, true)
                        } else if (col > grid[i][colIdx]) {
                            Pair(acc.first + 1, acc.second)
                        } else {
                            Pair(acc.first, true)
                        }
                    }
                    val bottom = (rowIdx + 1 until grid.size).toList().fold(Pair(0, false)){ acc, i ->
                        if (acc.second) {
                          acc
                        } else if (col == grid[i][colIdx]) {
                            Pair(acc.first + 1, true)
                        } else if (col > grid[i][colIdx]) {
                            Pair(acc.first + 1, acc.second)
                        } else {
                            Pair(acc.first, true)
                        }
                    }
                    val sceneScore = left.first * right.first * top.first * bottom.first

                    result = if(sceneScore > result) sceneScore else result
                }
            }
        }

        println(result)
        println(a)
    }
}