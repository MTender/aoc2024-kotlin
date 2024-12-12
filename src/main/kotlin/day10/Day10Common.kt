package day10

import general.Loc
import general.get

sealed class Day10Common(
    protected val map: List<String>
) {
    private val reachableEndingsFrom = mutableMapOf<Loc, List<Loc>>()

    protected fun findReachableEndings(loc: Loc): List<Loc> {
        val cachedReachableEndings = reachableEndingsFrom[loc]
        if (cachedReachableEndings != null) {
            return cachedReachableEndings
        }

        if (map.get(loc).digitToInt() == 9) {
            return listOf(loc)
        }

        val nextLocations = findNextLocations(loc)

        val reachableEndings = mutableListOf<Loc>()
        for (nextLoc in nextLocations) {
            reachableEndings += findReachableEndings(nextLoc)
        }
        reachableEndingsFrom[loc] = reachableEndings

        return reachableEndings
    }

    protected fun findTrailheads(): List<Loc> {
        val trailheads = mutableListOf<Loc>()

        for ((rowIndex, row) in map.withIndex()) {
            for ((colIndex, c) in row.withIndex()) {
                if (c == '0') trailheads.add(Loc(rowIndex, colIndex))
            }
        }

        return trailheads
    }

    private fun findNextLocations(loc: Loc): List<Loc> {
        val currentHeight = map.get(loc).digitToInt()

        return loc.edges()
            .filter { it.isValid(map.size, map.first().length) }
            .filter { map.get(it).digitToInt() == currentHeight + 1 }
    }
}