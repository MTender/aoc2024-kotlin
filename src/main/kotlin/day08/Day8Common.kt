package day08

import general.Loc

fun findAntennasByType(map: List<String>): Collection<List<Loc>> {
    val antennas = mutableMapOf<Char, MutableList<Loc>>()

    for (rowIndex in map.indices) {
        for (colIndex in map[rowIndex].indices) {
            val char = map[rowIndex][colIndex]

            if (char.isLetter() || char.isDigit()) {
                antennas.putIfAbsent(char, mutableListOf())
                antennas[char]!! += Loc(rowIndex, colIndex)
            }
        }
    }

    return antennas.values
}

fun isOnMap(loc: Loc, mapSize: Pair<Int, Int>): Boolean {
    return loc.rowIndex >= 0 &&
            loc.colIndex >= 0 &&
            loc.rowIndex < mapSize.first &&
            loc.colIndex < mapSize.second
}