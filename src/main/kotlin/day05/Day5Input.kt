package day05

import input.Input

object Day5Input {

    fun read(path: String): Pair<Map<Int, List<Int>>, List<List<Int>>> {
        val lines = Input.read(path)

        val emptyLineIndex = lines.indexOfFirst { it.isBlank() }

        val ruleStrings = lines.subList(0, emptyLineIndex)
        val updateStrings = lines.subList(emptyLineIndex + 1, lines.size)

        val rules = ruleStrings.map { ruleString ->
            val split = ruleString.split("|")
            Pair(split[0].toInt(), split[1].toInt())
        }

        val ruleMap = mutableMapOf<Int, MutableList<Int>>()
        for (rule in rules) {
            ruleMap.putIfAbsent(rule.first, mutableListOf())
            ruleMap[rule.first]!! += rule.second
        }

        val updates = updateStrings.map { updateString -> updateString.split(",").map(String::toInt) }

        return Pair(ruleMap, updates)
    }
}