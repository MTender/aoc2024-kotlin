package general

data class Loc(
    val rowIndex: Int,
    val colIndex: Int,
) {
    fun up(): Loc {
        return Loc(rowIndex - 1, colIndex)
    }

    fun down(): Loc {
        return Loc(rowIndex + 1, colIndex)
    }

    fun left(): Loc {
        return Loc(rowIndex, colIndex - 1)
    }

    fun right(): Loc {
        return Loc(rowIndex, colIndex + 1)
    }

    fun edges(): List<Loc> {
        return listOf(up(), down(), left(), right())
    }

    fun isValid(map: Collection<String>): Boolean {
        return isValid(Pair(map.size, map.first().length))
    }

    fun isValid(map: Collection<Collection<Any>>): Boolean {
        return isValid(Pair(map.size, map.first().size))
    }

    fun isValid(mapSize: Pair<Int, Int>): Boolean {
        return rowIndex >= 0 &&
                colIndex >= 0 &&
                rowIndex < mapSize.first &&
                colIndex < mapSize.second
    }
}

fun List<String>.get(loc: Loc): Char {
    return this[loc.rowIndex][loc.colIndex]
}