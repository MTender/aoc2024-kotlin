package day07

fun main() {
    val equations = Day7Input.read("input.txt")

    var sum = 0L

    for (equation in equations) {
        if (testEquation(equation, true)) {
            sum += equation.first
        }
    }

    println(sum)
}