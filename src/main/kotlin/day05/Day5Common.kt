package day05

fun isOrderedUpdate(update: List<Int>, ruleMap: Map<Int, List<Int>>): Boolean {
    for (pageIndex in update.indices) {
        val page = update[pageIndex]
        val laterPages = ruleMap[page] ?: continue

        for (laterPage in laterPages) {
            val laterPageIndex = update.indexOf(laterPage)
            if (laterPageIndex == -1) continue

            if (laterPageIndex < pageIndex) {
                return false
            }
        }
    }

    return true
}

fun sumOfMiddlePageNumbers(updates: List<List<Int>>): Int {
    return updates.sumOf { it[it.size / 2] }
}