package day07

import general.Input

object Day7Input {

    fun read(path: String): List<Pair<Long, List<Int>>> {
        val lines = Input.read(path)

        return lines.map {
            val split = it.split(": ")

            Pair(split[0].toLong(), split[1].split(" ").map(String::toInt))
        }
    }
}