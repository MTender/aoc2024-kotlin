package day07

import general.digitsCount
import kotlin.math.log10
import kotlin.math.pow

fun testEquation(equation: Pair<Long, List<Int>>, includeJoinOperator: Boolean): Boolean {
    val target = equation.first
    val values = equation.second

    val queue = ArrayDeque<List<Char>>()
    queue.addLast(listOf())

    while (queue.isNotEmpty()) {
        val operators = queue.removeFirst()

        val result = calculate(values, operators)
        if (result > target) continue

        if (operators.size + 1 == values.size) {
            if (result == target) return true
        } else {
            queue.addLast(operators + '+')
            queue.addLast(operators + '*')
            if (includeJoinOperator) queue.addLast(operators + '|')
        }
    }

    return false
}

fun calculate(values: List<Int>, operators: List<Char>): Long {
    var result = values.first().toLong()

    for (i in operators.indices) {
        val operator = operators[i]
        val nextValue = values[i + 1]

        when (operator) {
            '+' -> result += nextValue
            '*' -> result *= nextValue
            '|' -> result = result * 10.pow(nextValue.digitsCount()) + nextValue
        }
    }

    return result
}

fun Int.pow(exponent: Int): Int {
    return toDouble().pow(exponent).toInt()
}