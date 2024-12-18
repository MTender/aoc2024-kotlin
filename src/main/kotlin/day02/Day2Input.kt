package day02

import general.Input

object Day2Input {

    fun read(input: String): List<List<Int>> {
        val lines = Input.read(input)

        return lines.map { it.split(" ").map(String::toInt) }
    }
}