fun main() {
    Day5.part1()
    Day5.part2()
}

object Day5 {
    fun move2(stacks: Array<ArrayDeque<String>>, commands: Array<Array<Int>>) {
        commands.forEach {
            val (times, from, to) = it
            val t = stacks[from].slice(stacks[from].size - times  until stacks[from].size)

            for (item in t) {
                stacks[to].add(item)
                stacks[from].removeLast()
            }
        }
    }

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

    fun transformInput(input: String): Pair<Array<ArrayDeque<String>>, Array<Array<Int>>> {
        val arrayOfInput = input.split("\n")
        val separator = arrayOfInput.indexOf("")
        val stackInput = arrayOfInput.slice(0 until separator)
        val commandInput = arrayOfInput.slice(separator + 1 until arrayOfInput.size)
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

        return stacks to commands
    }

    fun part1() {
        val input = object{}::class.java.getResource("day5_1.txt")?.readText(Charsets.UTF_8).orEmpty()
        val (stacks, commands) = transformInput(input)

        move(stacks, commands)

        val result = stacks.map { it.last() }.reduceRight {s, acc -> s + acc}

        println(result)
    }

    fun part2() {
        val input = object{}::class.java.getResource("day5_2.txt")?.readText(Charsets.UTF_8).orEmpty()
        val (stacks, commands) = transformInput(input)

        move2(stacks, commands)

        val result = stacks.map { it.last() }.reduceRight {s, acc -> s + acc}

        println(result)
    }
}