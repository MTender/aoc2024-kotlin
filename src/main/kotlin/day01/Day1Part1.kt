package day01

import kotlin.math.abs

fun main() {
    val input = Day1Input.read("input.txt")

    val list1 = input.first.toMutableList()
    val list2 = input.second.toMutableList()

    list1.sort()
    list2.sort()

    var diffSum = 0

    for (i in 0..<list1.size) {
        diffSum += abs(list1[i] - list2[i])
    }

    println(diffSum)
}