package day04

import general.Input

fun main() {
    var xmasCount = 0

    val lines0 = Input.read("input.txt")
    xmasCount += countXmasInLinesAndDiagonals(lines0)

    val lines90 = rotate(lines0)
    xmasCount += countXmasInLinesAndDiagonals(lines90)

    val lines180 = rotate(lines90)
    xmasCount += countXmasInLinesAndDiagonals(lines180)

    val lines270 = rotate(lines180)
    xmasCount += countXmasInLinesAndDiagonals(lines270)

    println(xmasCount)
}

fun countXmasInLinesAndDiagonals(lines: List<String>): Int {
    return lines.sumOf { xmasCountInLine(it) } + getDiagonals(lines).sumOf { xmasCountInLine(it) }
}

fun xmasCountInLine(line: String): Int {
    return Regex("XMAS").findAll(line).count()
}

fun getDiagonals(lines: List<String>): List<String> {
    val mutableLines = lines.toMutableList()

    for (i in mutableLines.indices) {
        mutableLines[i] = " ".repeat(i) + mutableLines[i] + " ".repeat(mutableLines[i].lastIndex - i)
    }

    return rotate(mutableLines).map { it.trim() }
}

fun rotate(lines: List<String>): List<String> {
    val newLines = mutableListOf<String>()

    for (i in lines[0].lastIndex downTo 0) {
        var newLine = ""
        for (line in lines) {
            newLine += line[i]
        }
        newLines += newLine
    }

    return newLines
}