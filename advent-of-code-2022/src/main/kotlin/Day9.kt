fun main() {
    Day9.part1()
}
object Day9 {
    data class Rope(var xAxis: Int, var yAxis: Int)

    class Grid(startX: Int, startY: Int) {
        val head = Rope(startX, startY)
        val tail = Rope(startX, startY)
        val tailMovementAtLeastOnce = hashSetOf(Rope(startX, startY))

        fun move(input: String) {
            val (command, timesStr) =  input.split(" ")
            val times = timesStr.toInt()
            val handler = { t: Int, func: () -> Unit ->
                for (i in 0 until t) {
                    func()
                }
            }

            when(command) {
                "U" -> handler(times) {
                    head.yAxis += 1
                    moveTail()
                }
                "R" -> handler(times) {
                    head.xAxis += 1
                    moveTail()
                }
                "D" -> handler(times) {
                    head.yAxis -= 1
                    moveTail()
                }
                "L" ->handler(times) {
                    head.xAxis -= 1
                    moveTail()
                }
                else -> println("Unknown command")
            }
        }

        fun isTailNextToHead(): Boolean {
            return Math.abs(head.xAxis - tail.xAxis) <= 1 && Math.abs(head.yAxis - tail.yAxis) <= 1
        }

        private fun moveTail() {
            if (isTailNextToHead()) {
                return
            }

            val diffX = Math.abs(head.xAxis - tail.xAxis)
            val diffY = Math.abs(head.yAxis - tail.yAxis)
            if (diffX == diffY) {
                if (head.yAxis > tail.yAxis) {
                    tail.xAxis = head.xAxis
                    tail.yAxis = head.yAxis - 1
                } else {
                    tail.xAxis = head.xAxis
                    tail.yAxis = head.yAxis + 1
                }
            } else if (diffX > diffY) {
                if (head.xAxis > tail.xAxis) {
                    tail.xAxis = head.xAxis - 1
                    tail.yAxis = head.yAxis
                } else {
                    tail.xAxis = head.xAxis + 1
                    tail.yAxis = head.yAxis
                }
            } else {
                if (head.yAxis > tail.yAxis) {
                    tail.xAxis = head.xAxis
                    tail.yAxis = head.yAxis - 1
                } else {
                    tail.xAxis = head.xAxis
                    tail.yAxis = head.yAxis + 1
                }
            }
            tailMovementAtLeastOnce.add(Rope(tail.xAxis, tail.yAxis))
        }
    }
    fun part1() {
        val input = object{}::class.java.getResource("day9_1.txt")?.readText(Charsets.UTF_8).orEmpty().split("\n")
        val grid = Grid(0, 0)

        for (i in input) {
            grid.move(i)
        }

        println(grid.tailMovementAtLeastOnce.size)
    }
}