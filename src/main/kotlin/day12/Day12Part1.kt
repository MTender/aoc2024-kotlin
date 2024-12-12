package day12

fun main() {
    val map = Day12Input.read("input.txt")
        .map { it.toMutableList() }

    var totalPrice = 0

    while (true) {
        val regionStartingPlot = findNextRegionStart(map) ?: break

        val region = getRegion(map, regionStartingPlot)

        val regionArea = region.plots.size
        val regionPerimeterLength = region.getPerimeterLength()

        totalPrice += regionPerimeterLength * regionArea
    }

    println(totalPrice)
}