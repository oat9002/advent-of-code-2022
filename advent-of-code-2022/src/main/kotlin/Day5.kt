import java.util.Stack

fun main() {
    Day5.part1()
}

object Day5 {
    fun move(stacks: Array<ArrayDeque<String>>, commands: Array<Array<Int>>) {
        commands.forEach {
            val (times, from, to) = it

            for (i in 1 .. times) {
                val last = stacks[from].last()

                stacks[from].removeLast()
                stacks[to].add(last)
            }
        }
    }

    fun part1() {
        val input = object{}::class.java.getResource("day5_1.txt")?.readText(Charsets.UTF_8).orEmpty().split("\n")
        val separator = input.indexOf("")
        val stackInput = input.slice(0 until separator)
        val commandInput = input.slice(separator + 1 until input.size)
        val numberOfStack = stackInput[stackInput.size - 1].last().toString().toInt()
        val stacks = Array<ArrayDeque<String>>(numberOfStack) { _ -> ArrayDeque() }

        stackInput.slice(0 until stackInput.size - 1).reversed().forEach {
            var count = 1
            var stackIndex = 0
            while(count < it.length) {
                val value = it[count].toString()

                if (value != " ") {
                    stacks[stackIndex].add(value)
                }

                stackIndex++
                count += 4
            }
        }

        val commands = commandInput.map {
            val temp = it.split(" ")
            arrayOf(temp[1].toInt(), temp[3].toInt() - 1, temp[5].toInt() - 1)
        }.toTypedArray()

        move(stacks, commands)

        val result = stacks.map { it.last() }.reduceRight {s, acc -> s + acc}

        println("$result")
    }
}