package day06

enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

data class Position(
    val rowIndex: Int,
    val colIndex: Int,
    val facing: Direction
)

fun getCol(index: Int, map: List<String>): String {
    return map.map { it[index] }.joinToString("")
}

fun move(pos: Position, map: List<String>): Pair<Position?, List<Pair<Int, Int>>> {
    return when (pos.facing) {
        Direction.UP -> {
            val row = getCol(pos.colIndex, map)
            val obstacleRowIndex = row.lastIndexOf('#', startIndex = pos.rowIndex)
            val finalRowIndex = if (obstacleRowIndex == -1) 0 else obstacleRowIndex + 1

            val travelledPositions = (pos.rowIndex downTo  finalRowIndex).map { Pair(it, pos.colIndex) }

            val newPos = if (obstacleRowIndex == -1) {
                null
            } else {
                Position(finalRowIndex, pos.colIndex, Direction.RIGHT)
            }

            Pair(newPos, travelledPositions)
        }
        Direction.DOWN -> {
            val col = getCol(pos.colIndex, map)
            val obstacleRowIndex = col.indexOf('#', startIndex = pos.rowIndex)
            val finalRowIndex = if (obstacleRowIndex == -1) map.lastIndex else obstacleRowIndex - 1

            val travelledPositions = (pos.rowIndex..finalRowIndex).map { Pair(it, pos.colIndex) }

            val newPos = if (obstacleRowIndex == -1) {
                null
            } else {
                Position(finalRowIndex, pos.colIndex, Direction.LEFT)
            }

            Pair(newPos, travelledPositions)
        }
        Direction.LEFT -> {
            val row = map[pos.rowIndex]
            val obstacleColIndex = row.lastIndexOf('#', startIndex = pos.colIndex)
            val finalColIndex = if (obstacleColIndex == -1) 0 else obstacleColIndex + 1

            val travelledPositions = (pos.colIndex downTo  finalColIndex).map { Pair(pos.rowIndex, it) }

            val newPos = if (obstacleColIndex == -1) {
                null
            } else {
                Position(pos.rowIndex, finalColIndex, Direction.UP)
            }

            Pair(newPos, travelledPositions)
        }
        Direction.RIGHT -> {
            val row = map[pos.rowIndex]
            val obstacleColIndex = row.indexOf('#', startIndex = pos.colIndex)
            val finalColIndex = if (obstacleColIndex == -1) map.lastIndex else obstacleColIndex - 1

            val travelledPositions = (pos.colIndex..finalColIndex).map { Pair(pos.rowIndex, it) }

            val newPos = if (obstacleColIndex == -1) {
                null
            } else {
                Position(pos.rowIndex, finalColIndex, Direction.DOWN)
            }

            Pair(newPos, travelledPositions)
        }
    }
}

fun find(char: Char, map: List<String>): Pair<Int, Int> {
    for (i in map.indices) {
        val j = map[i].indexOf(char)
        if (j == -1) continue
        return Pair(i, j)
    }

    throw RuntimeException("Didn't find $char")
}