package day05

fun main() {
    val (ruleMap, updates) = Day5Input.read("input.txt")

    val orderedUpdates = updates.filter { isOrderedUpdate(it, ruleMap) }

    println(sumOfMiddlePageNumbers(orderedUpdates))
}

