package day08

import general.Input
import general.Loc

fun main() {
    val map = Input.read("input.txt")
    val mapSize = Pair(map.size, map.first().length)

    val antennasByType = findAntennasByType(map)

    val antinodes = mutableSetOf<Loc>()

    for (antennas in antennasByType) {
        antinodes.addAll(calculateAntinodesWithRH(antennas, mapSize))
    }

    println(antinodes.size)
}

fun calculateAntinodesWithRH(antennas: List<Loc>, mapSize: Pair<Int, Int>): List<Loc> {
    val antinodes = mutableListOf<Loc>()

    for (i1 in antennas.indices) {
        for (i2 in i1 + 1..antennas.lastIndex) {
            val antenna1 = antennas[i1]
            val antenna2 = antennas[i2]

            val rowDiff = antenna2.rowIndex - antenna1.rowIndex
            val colDiff = antenna2.colIndex - antenna1.colIndex

            antinodes.addAll(calculateAntinodes(antenna1, -rowDiff, -colDiff, mapSize))
            antinodes.addAll(calculateAntinodes(antenna2, rowDiff, colDiff, mapSize))
        }
    }

    return antinodes
}

fun calculateAntinodes(start: Loc, rowDiff: Int, colDiff: Int, mapSize: Pair<Int, Int>): List<Loc> {
    val antinodes = mutableListOf<Loc>()

    var node = start
    while (isOnMap(node, mapSize)) {
        antinodes.add(node)
        node = Loc(node.rowIndex + rowDiff, node.colIndex + colDiff)
    }

    return antinodes
}