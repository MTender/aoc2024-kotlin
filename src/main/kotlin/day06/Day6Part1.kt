package day06

import general.Input

fun main() {
    val map = Input.read("input.txt")

    val start = find('^', map)

    val travelledPositions = mutableSetOf<Pair<Int, Int>>()

    var pos: Position? = Position(start.first, start.second, Direction.UP)
    while (pos != null) {
        val moveResult = move(pos, map)
        pos = moveResult.first
        travelledPositions += moveResult.second
    }

    println(travelledPositions.size)
}