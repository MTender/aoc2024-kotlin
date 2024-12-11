package day11

fun main() {
    var stoneValues = Day11Input.read("input.txt")

    for (i in 0..24) {
        stoneValues = blinkValues(stoneValues)
    }

    println(stoneValues.size)
}

fun blinkValues(stones: List<Long>): List<Long> {
    return stones.flatMap { blinkValue(it) }
}