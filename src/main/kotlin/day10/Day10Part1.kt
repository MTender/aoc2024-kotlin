package day10

import general.Input

fun main() {
    Day10Part1(Input.read("input.txt")).run()
}

class Day10Part1(
    map: List<String>
) : Day10Common(map) {

    fun run() {
        val trailheads = findTrailheads()

        val reachableEndings = trailheads.map { findReachableEndings(it).distinct().size }

        println(reachableEndings.sum())
    }
}