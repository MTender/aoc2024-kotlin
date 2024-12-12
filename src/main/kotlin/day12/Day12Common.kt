package day12

import general.Direction
import general.Loc
import general.get
import general.set

fun findNextRegionStart(map: List<List<Char>>): Loc? {
    for ((rowIndex, row) in map.withIndex()) {
        for ((colIndex, c) in row.withIndex()) {
            if (c != '.') return Loc(rowIndex, colIndex)
        }
    }
    return null
}

fun getRegion(map: List<MutableList<Char>>, startingPlot: Loc): Region {
    val type = map.get(startingPlot)

    val regionPlots = mutableSetOf<Loc>()

    val plotQueue = ArrayDeque<Loc>()
    plotQueue.add(startingPlot)
    map.set(startingPlot, '.')

    while (plotQueue.isNotEmpty()) {
        val plot = plotQueue.removeFirst()
        regionPlots.add(plot)

        plot.edges()
            .filter { it.isValid(map) }
            .filter { map.get(it) == type }
            .forEach {
                plotQueue.add(it)
                map.set(it, '.')
            }
    }

    return Region(regionPlots)
}

data class Region(
    val plots: Set<Loc>
) {
    fun getPerimeterLength(): Int {
        return plots.sumOf { it.edges().count { edge -> !plots.contains(edge) } }
    }

    fun getSidesCount(): Int {
        val perimeterPlots = plots.flatMap { getPerimeterPlotsAt(it) }.toMutableSet()

        var sidesCount = 0

        while (perimeterPlots.isNotEmpty()) {
            val startingPlot = perimeterPlots.first()

            var currentPlot = startingPlot
            do {
                perimeterPlots.remove(currentPlot)

                val nextPossiblePerimeterPlots = getNextPossiblePerimeterPlots(currentPlot)
                val (nextPerimeterPlotIndex, nextPerimeterPlot) = determineCorrectPerimeterPlot(nextPossiblePerimeterPlots)

                if (nextPerimeterPlotIndex != 1) {
                    sidesCount++
                }
                currentPlot = nextPerimeterPlot

            } while (currentPlot != startingPlot)
        }

        return sidesCount
    }

    private fun getNextPossiblePerimeterPlots(current: PerimeterPlot): List<PerimeterPlot> {
        return when (current.fencedSide) {
            Direction.UP -> listOf(
                PerimeterPlot(current.plot.right().up(), Direction.LEFT),
                PerimeterPlot(current.plot.right(), Direction.UP),
                PerimeterPlot(current.plot, Direction.RIGHT),
            )
            Direction.DOWN -> listOf(
                PerimeterPlot(current.plot.left().down(), Direction.RIGHT),
                PerimeterPlot(current.plot.left(), Direction.DOWN),
                PerimeterPlot(current.plot, Direction.LEFT),
            )
            Direction.LEFT -> listOf(
                PerimeterPlot(current.plot.up().left(), Direction.DOWN),
                PerimeterPlot(current.plot.up(), Direction.LEFT),
                PerimeterPlot(current.plot, Direction.UP),
            )
            Direction.RIGHT -> listOf(
                PerimeterPlot(current.plot.down().right(), Direction.UP),
                PerimeterPlot(current.plot.down(), Direction.RIGHT),
                PerimeterPlot(current.plot, Direction.DOWN)
            )
        }
    }

    private fun determineCorrectPerimeterPlot(possiblePerimeterPlots: List<PerimeterPlot>): Pair<Int, PerimeterPlot> {
        for ((i, perimeterPlot) in possiblePerimeterPlots.withIndex()) {
            if (getPerimeterPlotsAt(perimeterPlot.plot).contains(perimeterPlot)) return Pair(i, perimeterPlot)
        }
        throw RuntimeException("None of the provided perimeter plots are correct")
    }

    private fun getPerimeterPlotsAt(plot: Loc): List<PerimeterPlot> {
        if (!plots.contains(plot)) return listOf()

        val perimeterPlots = mutableListOf<PerimeterPlot>()

        if (!plots.contains(plot.up())) perimeterPlots.add(PerimeterPlot(plot, Direction.UP))
        if (!plots.contains(plot.down())) perimeterPlots.add(PerimeterPlot(plot, Direction.DOWN))
        if (!plots.contains(plot.left())) perimeterPlots.add(PerimeterPlot(plot, Direction.LEFT))
        if (!plots.contains(plot.right())) perimeterPlots.add(PerimeterPlot(plot, Direction.RIGHT))

        return perimeterPlots
    }

    companion object {
        data class PerimeterPlot(
            val plot: Loc,
            val fencedSide: Direction
        )
    }
}