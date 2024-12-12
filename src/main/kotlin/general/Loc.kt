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

    fun isValid(map: List<List<Any?>>): Boolean {
        return isValid(map.size, map.first().size)
    }

    fun isValid(rowCount: Int, colCount: Int): Boolean {
        return rowIndex >= 0 &&
                colIndex >= 0 &&
                rowIndex < rowCount &&
                colIndex < colCount
    }
}

fun List<String>.get(loc: Loc): Char {
    return this[loc.rowIndex][loc.colIndex]
}

fun <T> List<List<T>>.get(loc: Loc): T {
    return this[loc.rowIndex][loc.colIndex]
}

fun <T> List<MutableList<T>>.set(loc: Loc, value: T) {
    this[loc.rowIndex][loc.colIndex] = value
}