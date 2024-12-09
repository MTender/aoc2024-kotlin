package day09

fun main() {
    val input = Day9Input.read("input.txt")
    val memory = constructBlockMemory(input).toMutableList()

    while (true) {
        val (fileBlockIndex, fileBlock) = findLastActiveFileBlock(memory)
        if (fileBlock == null) break
        fileBlock.active = false

        val (emptyBlockIndex, emptyBlock) = findFirstEmptyBlock(memory, fileBlock.length, fileBlockIndex)
        if (emptyBlock == null) continue

        memory.add(emptyBlockIndex, fileBlock.copy())
        fileBlock.empty = true
        emptyBlock.length -= fileBlock.length
    }

    println(calculateChecksum(memory))
}

fun constructBlockMemory(input: String): List<Block> {
    return input.mapIndexed { index, c -> Block(c.digitToInt(), index % 2 != 0, index / 2) }.filter { it.length != 0 }
}

fun findLastActiveFileBlock(memory: List<Block>): Pair<Int, Block?> {
    for ((index, block) in memory.withIndex().reversed()) {
        if (block.active && !block.empty) return Pair(index, block)
    }
    return Pair(-1, null)
}

fun findFirstEmptyBlock(memory: List<Block>, minLength: Int, beforeIndex: Int): Pair<Int, Block?> {
    for ((index, block) in memory.withIndex()) {
        if (index >= beforeIndex) break

        if (block.empty && block.length >= minLength) return Pair(index, block)
    }
    return Pair(-1, null)
}

fun calculateChecksum(memory: List<Block>): Long {
    var checksum = 0L
    var index = 0

    for (block in memory) {
        Array(block.length) { if (block.empty) 0 else block.id }.forEach {
            checksum += it * index++
        }
    }

    return checksum
}

data class Block(
    var length: Int,
    var empty: Boolean,
    val id: Int,
    var active: Boolean = true
)