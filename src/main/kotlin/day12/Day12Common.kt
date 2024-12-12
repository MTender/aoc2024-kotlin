package day12

import general.Direction
import general.Loc
import general.get
import general.set

fun findNextPlotStart(map: List<List<Char>>): Loc? {
    for ((rowIndex, row) in map.withIndex()) {
        for ((colIndex, c) in row.withIndex()) {
            if (c != '.') return Loc(rowIndex, colIndex)
        }
    }
    return null
}

fun getPlot(map: List<MutableList<Char>>, plotStart: Loc): Plot {
    val type = map.get(plotStart)

    val plotLocs = mutableSetOf<Loc>()

    val locs = ArrayDeque<Loc>()
    locs.add(plotStart)
    map.set(plotStart, '.')

    while (locs.isNotEmpty()) {
        val loc = locs.removeFirst()
        plotLocs.add(loc)

        loc.edges()
            .filter { it.isValid(map) }
            .filter { map.get(it) == type }
            .forEach {
                locs.add(it)
                map.set(it, '.')
            }
    }

    return Plot(plotLocs)
}

data class Plot(
    val locs: Set<Loc>
) {
    fun getPerimeterLength(): Int {
        return locs.sumOf { it.edges().count { edge -> !locs.contains(edge) } }
    }

    fun getSidesCount(): Int {
        val getAllPerimeterLocs = locs.flatMap { getPerimeterLocsAt(it) }.toMutableSet()

        var sidesCount = 0

        while (getAllPerimeterLocs.isNotEmpty()) {
            val startingLoc = getAllPerimeterLocs.first()

            var currentLoc = startingLoc
            do {
                getAllPerimeterLocs.remove(currentLoc)

                val nextPossibleLocs = getNextPossiblePerimeterLocs(currentLoc)
                val (correctPerimeterLocIndex, correctPerimeterLoc) = determineCorrectPerimeterLoc(nextPossibleLocs)

                if (correctPerimeterLocIndex != 1) {
                    sidesCount++
                }
                currentLoc = correctPerimeterLoc

            } while (currentLoc != startingLoc)
        }

        return sidesCount
    }

    private fun getNextPossiblePerimeterLocs(current: PerimeterLoc): List<PerimeterLoc> {
        return when (current.perimeterSide) {
            Direction.UP -> listOf(
                PerimeterLoc(current.loc.right().up(), Direction.LEFT),
                PerimeterLoc(current.loc.right(), Direction.UP),
                PerimeterLoc(current.loc, Direction.RIGHT),
            )
            Direction.DOWN -> listOf(
                PerimeterLoc(current.loc.left().down(), Direction.RIGHT),
                PerimeterLoc(current.loc.left(), Direction.DOWN),
                PerimeterLoc(current.loc, Direction.LEFT),
            )
            Direction.LEFT -> listOf(
                PerimeterLoc(current.loc.up().left(), Direction.DOWN),
                PerimeterLoc(current.loc.up(), Direction.LEFT),
                PerimeterLoc(current.loc, Direction.UP),
            )
            Direction.RIGHT -> listOf(
                PerimeterLoc(current.loc.down().right(), Direction.UP),
                PerimeterLoc(current.loc.down(), Direction.RIGHT),
                PerimeterLoc(current.loc, Direction.DOWN)
            )
        }
    }

    private fun determineCorrectPerimeterLoc(possiblePerimeterLocs: List<PerimeterLoc>): Pair<Int, PerimeterLoc> {
        for ((i, perimeterLoc) in possiblePerimeterLocs.withIndex()) {
            if (getPerimeterLocsAt(perimeterLoc.loc).contains(perimeterLoc)) return Pair(i, perimeterLoc)
        }
        throw RuntimeException("None of the provided perimeter locs are correct")
    }

    private fun getPerimeterLocsAt(loc: Loc): List<PerimeterLoc> {
        if (!locs.contains(loc)) return listOf()

        val perimeterLocs = mutableListOf<PerimeterLoc>()

        if (!locs.contains(loc.up())) perimeterLocs.add(PerimeterLoc(loc, Direction.UP))
        if (!locs.contains(loc.down())) perimeterLocs.add(PerimeterLoc(loc, Direction.DOWN))
        if (!locs.contains(loc.left())) perimeterLocs.add(PerimeterLoc(loc, Direction.LEFT))
        if (!locs.contains(loc.right())) perimeterLocs.add(PerimeterLoc(loc, Direction.RIGHT))

        return perimeterLocs
    }

    companion object {
        data class PerimeterLoc(
            val loc: Loc,
            val perimeterSide: Direction
        )
    }
}