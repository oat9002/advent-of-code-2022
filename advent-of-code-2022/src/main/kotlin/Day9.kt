fun main() {
//    Day9.part1()
    Day9.part2()
}
object Day9 {
    data class Rope(val name: String, var column: Int, var row: Int, var next: Rope? = null) {
        fun isTail(): Boolean {
            return next == null
        }

        fun isNextToParent(previous: Rope): Boolean {
            return Math.abs(previous.column - column) <= 1 && Math.abs(previous.row - row) <= 1
        }

        fun move(column: Int, row: Int) {
            this.column = column
            this.row = row
            moveTail(next)
        }

        fun moveTail(n: Rope?) {
            if (n == null) {
                return
            }

            if (!n.isNextToParent(this)) {
                val diffCol = Math.abs(column - n.column)
                val diffRow = Math.abs(row - n.row)

                if (diffCol > diffRow) {
                    if (diffCol <= 1 || diffRow <= 1) {
                        n.column = if (column > n.column) column - 1 else column + 1
                        n.row = row
                    } else {
                        n.column = if (column > n.column) column - 1 else column + 1
                        n.row = if (row > n.row) row - 1 else row + 1
                    }
                } else {
                    if (diffCol <= 1) {
                        n.column = column
                        n.row =  if (row > n.row) row - 1 else row + 1
                    } else {
                        n.column = if (column > n.column) column - 1 else column + 1
                        n.row =  if (row > n.row) row - 1 else row + 1
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
                    "U" -> head.move(head.column, head.row + 1)
                    "R" -> head.move(head.column + 1, head.row)
                    "D" -> head.move(head.column, head.row - 1)
                    "L" -> head.move(head.column - 1, head.row)
                    else -> println("Unknown command, $command")
                }

                val tail = head.getTail()
                tailMovementAtLeastOnce.add(tail.copy())
            }
        }

        fun printTail() {
            for (i in 26 downTo -25) {
                for (j in -26 until 26) {
                    if (i == 0 && j == 0) {
                        print("s")
                    }
                    else if (tailMovementAtLeastOnce.map { Pair(it.row, it.column) }.contains(Pair(i, j))) {
                        print("#")
                    } else {
                        print(".")
                    }
                }
                println()
            }

            println("\n\n\n\n")
        }

        fun print() {
            val listOfRopes = arrayListOf<Rope>()
            var temp: Rope? = head

            while(temp != null) {
                listOfRopes.add(temp.copy())

                temp = temp.next
            }

            for (i in 26 downTo -25) {
                for (j in -26 until 26) {
                    val rope = listOfRopes.find { it.column == j && it.row == i }

                    if (rope != null) {
                        print(rope.name)
                    }
                    else {
                        print(".")
                    }
                }
                println()
            }

            println("\n\n\n\n")
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

        println(grid.tailMovementAtLeastOnce.size)
    }
}