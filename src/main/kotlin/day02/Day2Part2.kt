package day02

fun main() {
    val reports = Day2Input.read("input.txt")

    var safeCount = 0

    for (report in reports) {
        if (reportIsSafe(report)) {
            safeCount++
            continue
        }

        for (i in report.indices) {
            val dampenedReport = report.toMutableList()
            dampenedReport.removeAt(i)

            if (reportIsSafe(dampenedReport)) {
                safeCount++
                break
            }
        }
    }

    println(safeCount)
}