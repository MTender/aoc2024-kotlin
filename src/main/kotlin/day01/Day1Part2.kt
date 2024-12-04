package day01

fun main() {
    val input = Day1Input.read("input.txt")

    val list1 = input.first
    val list2 = input.second

    var simScore = 0

    for (id in list1) {
        simScore += id * list2.count { it == id }
    }

    println(simScore)
}