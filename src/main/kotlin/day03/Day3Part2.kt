package day03

import input.Input

fun main() {
    val lines = Input.read("input.txt")

    var sumOfInstructions = 0

    var enabled = true

    for (line in lines) {
        val matches = Regex("mul\\((\\d{1,3}),(\\d{1,3})\\)|do\\(\\)|don't\\(\\)").findAll(line)

        matches.forEach { match ->
            when (match.value) {
                "do()" -> enabled = true
                "don't()" -> enabled = false
                else -> {
                    if (enabled) {
                        val factors = match.groupValues.subList(1, match.groupValues.size).map(String::toInt)
                        sumOfInstructions += factors[0] * factors[1]
                    }
                }
            }
        }
    }

    println(sumOfInstructions)
}