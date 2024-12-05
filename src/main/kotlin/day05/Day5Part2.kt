package day05

fun main() {
    val (ruleMap, updates) = Day5Input.read("input.txt")

    val unorderedUpdates = updates.filter { !isOrderedUpdate(it, ruleMap) }

    val comparator = Comparator<Int> { value1, value2 ->
        val laterPages = ruleMap[value1]

        if (laterPages == null || !laterPages.contains(value2)) {
            1
        } else {
            -1
        }
    }

    val sortedUpdates = unorderedUpdates.map { it.sortedWith(comparator) }

    println(sumOfMiddlePageNumbers(sortedUpdates))
}