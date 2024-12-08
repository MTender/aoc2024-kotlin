package day06

import general.Input

fun main() {
    val map = Input.read("input.txt")

    val start = find('^', map)
    val startPos = Position(start.first, start.second, Direction.UP)

    var obstacleOptions = 0

    for (rowIndex in map.indices) {
        val row = map[rowIndex]
        for (colIndex in row.indices) {
            if (listOf('#', '^').contains(map[rowIndex][colIndex])) continue

            val newObstacle = Pair(rowIndex, colIndex)
            val newMap = putObstacle(newObstacle, map)

            val turningPositions = mutableSetOf(startPos)

            var pos: Position? = startPos
            while (pos != null) {
                val moveResult = move(pos, newMap)
                pos = moveResult.first

                if (turningPositions.contains(pos)) {
                    obstacleOptions++
                    break
                }

                if (pos != null) {
                    turningPositions.add(pos)
                }
            }
        }
    }

    println(obstacleOptions)
}

fun putObstacle(obstacle: Pair<Int, Int>, map: List<String>): List<String> {
    return map.mapIndexed { index, row ->
        if (index != obstacle.first) {
            return@mapIndexed row
        }

        row.substring(0, obstacle.second) + '#' + row.substring(obstacle.second + 1)
    }
}