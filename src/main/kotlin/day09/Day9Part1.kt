package day09

fun main() {
    val input = Day9Input.read("input.txt")
    val memory = constructIntegerMemory(input).toMutableList()

    while (true) {
        val firstEmpty = memory.indexOfFirst { it == null }
        if (firstEmpty == -1) break

        moveLast(memory, firstEmpty)

        while (memory.last() == null) memory.removeLast()
    }

    println(calculateChecksum(memory))
}

fun constructIntegerMemory(input: String): List<Int?> {
    val memory = mutableListOf<Int?>()

    for ((index, c) in input.withIndex()) {
        val repeatValue = if (index % 2 == 0) {
            index / 2
        } else {
            null
        }

        memory.addAll(Array(c.digitToInt()) { repeatValue })
    }

    return memory
}

fun moveLast(memory: MutableList<Int?>, index: Int) {
    memory[index] = memory.removeLast()
}

fun calculateChecksum(memory: List<Int?>): Long {
    return memory.mapIndexed { index, value ->
        if (value == null) {
            0
        } else {
            index.toLong() * value
        }
    }.sum()
}