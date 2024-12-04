package day04

import input.Input

fun main() {
    val lines = Input.read("input.txt")

    var masXCount = 0

    for (i in 1..<lines.lastIndex) {
        for (j in 1..<lines[i].lastIndex) {
            if (isMasX(lines, Pair(i, j))) {
                masXCount++
            }
        }
    }

    println(masXCount)
}

fun isMasX(lines: List<String>, loc: Pair<Int, Int>): Boolean {
    return lines[loc.first][loc.second] == 'A' && Pair(setOf('M', 'S'), setOf('M', 'S')) == getDiagonals(lines, loc)
}

fun getDiagonals(lines: List<String>, loc: Pair<Int, Int>): Pair<Set<Char>, Set<Char>> {
    return Pair(
        setOf(lines[loc.first + 1][loc.second + 1], lines[loc.first - 1][loc.second - 1]),
        setOf(lines[loc.first + 1][loc.second - 1], lines[loc.first - 1][loc.second + 1])
    )
}