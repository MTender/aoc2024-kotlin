package day02

fun main() {
    val reports = Day2Input.read("input.txt")

    var safeCount = 0

    for (report in reports) {
        if (reportIsSafe(report)) {
            safeCount++
        }
    }

    println(safeCount)
}