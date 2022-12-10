data class FileSystem(val name: String, val isDirectory: Boolean, var size: Int, val subFileSystem: ArrayList<FileSystem>)

fun main() {
    Day7.part1()
}

object Day7 {
    fun transform(input: List<String>): FileSystem {
        var root: FileSystem? = null
        val tracking: ArrayDeque<FileSystem> = ArrayDeque(0)

        for (i in input) {
            val sp = i.split(" ")
            if (sp[0] == "$") {
                if (sp[1] == "cd") {
                    if (sp[2] == "/") {
                        val tRoot = FileSystem("/", true, 0, arrayListOf())

                        if (root == null) {
                            root = tRoot
                            tracking.add(root)
                        } else {
                            while(tracking.last().name != "/") {
                                tracking.removeLast()
                            }
                        }
                    } else if (sp[2] == "..") {
                        tracking.removeLast()
                    } else {
                        val t = tracking.last().subFileSystem.filter { it.isDirectory == true && it.name == sp[2] }.first()
                        tracking.add(t)
                    }
                }
            } else if (sp[0] == "dir") {
                tracking.last().subFileSystem.add(FileSystem(sp[1], true, 0, arrayListOf()))
            } else {
                tracking.last().subFileSystem.add(FileSystem(sp[1], false, sp[0].toInt(), arrayListOf()))
            }
        }

        val calTemp = ArrayDeque<FileSystem>(0)
        calTemp.add(root ?: FileSystem("", false, 0, arrayListOf()))

        while (calTemp.size != 0) {
            val last = calTemp.last()
            val nonCalDirs = last.subFileSystem.filter { it.isDirectory && it.size == 0 }
            if (nonCalDirs.isNotEmpty() && last.size == 0) {
                calTemp.addAll(nonCalDirs)
            } else {
                last.size = last.subFileSystem.sumOf { it.size }
                calTemp.removeLast()
            }
        }

        return root ?: FileSystem("", false, 0, arrayListOf())
    }

    fun getAllDirectories(root: FileSystem): List<FileSystem> {
        val toReturn = ArrayList<FileSystem>(0)
        val temp = ArrayDeque<FileSystem>(0)

        temp.add(root)

        while (temp.size != 0) {
            val first = temp.first()
            val dirs = first.subFileSystem.filter { it.isDirectory  }

            toReturn.add(first)

            if (dirs.isNotEmpty()) {
                temp.addAll(dirs)
            }

            temp.removeFirst()
        }

        return toReturn.toList()
    }
    fun part1() {
        val input = object{}::class.java.getResource("day7_1.txt")?.readText(Charsets.UTF_8).orEmpty().split("\n")
        val fileSystem = transform(input)
        val result = getAllDirectories(fileSystem).map { it.size }.filter { it <= 100000 }.sum()

        println(result)
    }
}