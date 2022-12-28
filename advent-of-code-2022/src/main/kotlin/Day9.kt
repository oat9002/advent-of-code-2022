fun main() {
//    Day9.part1()
    Day9.part2()
}
object Day9 {
    data class Rope(val name: String, var xAxis: Int, var yAxis: Int, var next: Rope? = null) {
        fun isTail(): Boolean {
            return next == null
        }

        fun isNextToParent(previous: Rope): Boolean {
            return Math.abs(previous.xAxis - xAxis) <= 1 && Math.abs(previous.yAxis - yAxis) <= 1
        }

        fun move(x: Int, y: Int) {
            xAxis = x
            yAxis = y
            moveTail(next)
        }

        fun moveTail(n: Rope?) {
            if (n == null) {
                return
            }

            if (!n.isNextToParent(this)) {
                val diffX = Math.abs(xAxis - n.xAxis)
                val diffY = Math.abs(yAxis - n.yAxis)
                if (diffX == diffY) {
                    n.xAxis = xAxis
                    n.yAxis = if (yAxis > n.yAxis)  yAxis - 1 else yAxis + 1
                } else if (diffX > diffY) {
                    n.yAxis = yAxis

                    if (xAxis > n.xAxis) {
                        n.xAxis = xAxis - 1
                        n.yAxis = yAxis
                    } else {
                        n.xAxis = xAxis + 1
                        n.yAxis = yAxis
                    }
                } else {
                    if (yAxis > n.yAxis) {
                        n.xAxis = xAxis
                        n.yAxis = yAxis - 1
                    } else {
                        n.xAxis = xAxis
                        n.yAxis = yAxis + 1
                    }
                }
            }

            n.moveTail(n.next)
        }

        fun getTail(): Rope {
            var t = next
            var toReturn = next
            while(t != null) {
                toReturn = t
                t = t.next
            }

            return toReturn!!
        }
    }

    class Grid(startX: Int, startY: Int, numberOftail: Int = 1) {
        val head = Rope("H", startX, startY)
        val tailMovementAtLeastOnce = hashSetOf(Rope(numberOftail.toString(), startX, startY))

        init {
            var tHead = head

            for (i in 1 until numberOftail + 1) {
                val tail = Rope(i.toString(), startX, startY)
                tHead.next = tail
                tHead = tHead.next!!
            }
        }

        fun move(input: String) {
            val (command, timesStr) =  input.split(" ")
            val times = timesStr.toInt()
            val handler = { t: Int, func: () -> Unit ->
                for (i in 0 until t) {
                    func()
                }
            }

            handler(times) {
                when(command) {
                    "U" -> head.move(head.xAxis, head.yAxis + 1)
                    "R" -> head.move(head.xAxis + 1, head.yAxis)
                    "D" -> head.move(head.xAxis, head.yAxis - 1)
                    "L" -> head.move(head.xAxis - 1, head.yAxis)
                    else -> println("Unknown command, $command")
                }

                val tail = head.getTail()
                tailMovementAtLeastOnce.add(tail.copy())
            }
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

    fun part2() {
        val input = object{}::class.java.getResource("day9_2.txt")?.readText(Charsets.UTF_8).orEmpty().split("\n")
        val grid = Grid(0, 0, 9)

        for (i in input) {
            grid.move(i)
        }


        for (i in -26 until 26) {
            for (j in -26 until 26) {
                if (i == 0 && j == 0) {
                    print("s")
                }
                else if (grid.tailMovementAtLeastOnce.map { Pair(it.xAxis, it.yAxis) }.contains(Pair(i, j))) {
                    print("#")
                } else {
                    print(".")
                }
            }
            println()
        }

        println(grid.tailMovementAtLeastOnce.size)
    }
}