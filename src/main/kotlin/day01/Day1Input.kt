package day01

import general.Input

object Day1Input {

    fun read(input: String): Pair<List<Int>, List<Int>> {
        val lines = Input.read(input)

        val list1 = mutableListOf<Int>()
        val list2 = mutableListOf<Int>()

        for (line in lines) {
            val split = line.split("\\s+".toRegex())

            list1.add(split[0].toInt())
            list2.add(split[1].toInt())
        }

        return Pair(list1, list2)
    }
}