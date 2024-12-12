package day12

fun main() {
    val map = Day12Input.read("input.txt")
        .map { it.toMutableList() }

    var totalPrice = 0

    while (true) {
        val plotStart = findNextPlotStart(map) ?: break

        val plot = getPlot(map, plotStart)

        val plotArea = plot.locs.size
        val plotSidesCount = plot.getSidesCount()

        totalPrice += plotSidesCount * plotArea
    }

    println(totalPrice)
}