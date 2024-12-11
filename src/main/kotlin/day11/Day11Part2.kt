package day11

fun main() {
    Day11Part2().run(Day11Input.read("input.txt"))
}

class Day11Part2 {
    private val cache = mutableMapOf<Stone, Long>()

    fun run(stoneValues: List<Long>) {
        println(stoneValues.sumOf { blink(Stone(it, 0)) })
    }

    private fun blink(stone: Stone): Long {
        val cachedCount = cache[stone]
        if (cachedCount != null) {
            return cachedCount
        }

        if (stone.depth == 75) {
            return 1
        }

        val newStoneValues = blinkValue(stone.value)

        var stoneCount = 0L
        for (newStoneValue in newStoneValues) {
            stoneCount += blink(Stone(newStoneValue, stone.depth + 1))
        }
        cache[stone] = stoneCount

        return stoneCount
    }

    private data class Stone(
        val value: Long,
        val depth: Int
    )
}

