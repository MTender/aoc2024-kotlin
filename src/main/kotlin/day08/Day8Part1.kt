package day08

import general.Input
import general.Loc

fun main() {
    val map = Input.read("input.txt")
    val mapSize = Pair(map.size, map.first().length)

    val antennasByType = findAntennasByType(map)

    val antinodes = mutableSetOf<Loc>()

    for (antennas in antennasByType) {
        antinodes.addAll(calculateAntinodesWithoutRH(antennas, mapSize))
    }

    println(antinodes.size)
}

fun calculateAntinodesWithoutRH(antennas: List<Loc>, mapSize: Pair<Int, Int>): List<Loc> {
    val antinodes = mutableListOf<Loc>()

    for (i1 in antennas.indices) {
        for (i2 in i1 + 1..antennas.lastIndex) {
            val antenna1 = antennas[i1]
            val antenna2 = antennas[i2]

            val rowDiff = antenna2.rowIndex - antenna1.rowIndex
            val colDiff = antenna2.colIndex - antenna1.colIndex

            val antinode1 = Loc(antenna1.rowIndex - rowDiff, antenna1.colIndex - colDiff)
            val antinode2 = Loc(antenna2.rowIndex + rowDiff, antenna2.colIndex + colDiff)

            if (isOnMap(antinode1, mapSize)) antinodes.add(antinode1)
            if (isOnMap(antinode2, mapSize)) antinodes.add(antinode2)
        }
    }

    return antinodes
}