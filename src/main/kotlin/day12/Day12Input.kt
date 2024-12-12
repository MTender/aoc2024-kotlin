package day12

import general.Input

object Day12Input {

    fun read(path: String): List<List<Char>> {
        return Input.read(path).map { it.toList() }
    }
}