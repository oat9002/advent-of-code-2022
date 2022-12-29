fun main() {
    Day10.part1()
    Day10.part2()
}

object Day10 {

    // return Pair(cycle, number-to-add)
    fun parseCommand(input: String): Pair<Int, Int> {
        val sp = input.split(" ")

        when(sp[0]) {
            "noop" -> return Pair(1, 0)
            "addx" -> return Pair(2, sp[1].toInt())
            else -> return Pair(0, 0)
        }
    }

    fun getSumOfSignalStrength(outputList: ArrayList<Int>): Int {
        var position = 20
        var toReturn = 0

        while(position <= 220) {
            toReturn += position * outputList[position - 1]
            position += 40
        }

        return toReturn
    }

    fun part1() {
        val input = object{}::class.java.getResource("day10_1.txt")?.readText(Charsets.UTF_8).orEmpty().split("\n")
        val outputList = arrayListOf<Int>()
        var x = 1

        for (i in input) {
            val (usedCycle, number) = parseCommand(i)
            for (t in 0 until usedCycle) {
                outputList.add(x)
            }
            x += number
        }

        println(getSumOfSignalStrength(outputList))
    }

    fun part2() {
        val input = object{}::class.java.getResource("day10_2.txt")?.readText(Charsets.UTF_8).orEmpty().split("\n")
        val outputList = arrayListOf<String>()
        var spritePos = 0
        var crtPos = 0
        val cycleSprit = 40

        for (i in input) {
            val (usedCycle, number) = parseCommand(i)

            for (t in 0 until usedCycle) {
                if (crtPos >= spritePos && crtPos <=spritePos + 2) {
                    outputList.add("#")
                } else {
                    outputList.add(".")
                }

                crtPos++

                if (crtPos == cycleSprit) {
                    crtPos = 0
                }
            }

            spritePos += number
        }

        val width = 40
        var pos = 0
        while(pos < 240) {

            for (i in pos until pos + width) {
                print(outputList[i])
            }
            println()
            pos += width
        }
    }
}