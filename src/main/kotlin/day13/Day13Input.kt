package day13

import general.Input

object Day13Input {

    fun read(path: String): List<Machine> {
        val input = Input.read(path)

        val machineVectors = input
            .filter { it.isNotBlank() }
            .chunked(3)
            .map { machine -> machine.map { parseLine(it) }}

        return machineVectors.map { Machine(it[0], it[1], it[2]) }
    }

    private fun parseLine(line: String): Vector {
        return line.replace("[^\\d,]".toRegex(), "")
            .split(",")
            .let { Vector(it[0].toLong(), it[1].toLong()) }
    }
}