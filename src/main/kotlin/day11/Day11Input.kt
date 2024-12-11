package day11

import general.Input

object Day11Input {

    fun read(path: String): List<Long> {
        return Input.read(path).first().split(" ").map(String::toLong)
    }
}