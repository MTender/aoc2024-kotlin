package day02

import kotlin.math.abs

fun reportIsSafe(report: List<Int>): Boolean {
    if (report.size < 2) {
        return true
    }

    if (report[0] == report[1]) {
        return false
    }

    val increasing = report[0] < report[1]

    for (i in 0 until report.lastIndex) {
        val value1 = report[i]
        val value2 = report[i + 1]

        val diff = abs(value1 - value2)

        if (
            diff > 3 ||
            diff == 0 ||
            increasing && value1 > value2 ||
            !increasing && value1 < value2
        ) {
            return false
        }
    }

    return true
}