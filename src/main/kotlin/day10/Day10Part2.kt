package day10

import general.Input

fun main() {
    Day10Part2(Input.read("input.txt")).run()
}

class Day10Part2(
    map: List<String>
) : Day10Common(map) {

    fun run() {
        val trailheads = findTrailheads()

        val reachableEndings = trailheads.map { findReachableEndings(it).size }

        println(reachableEndings.sum())
    }
}