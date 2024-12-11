package day11

import day07.pow
import general.digitsCount

fun blinkValue(stone: Long): List<Long> {
    if (stone == 0L) {
        return listOf(1L)
    }

    val digitsCount = stone.digitsCount()
    if (digitsCount % 2 == 0) {
        return listOf(
            stone / 10.pow(digitsCount / 2),
            stone % 10.pow(digitsCount / 2)
        )
    }

    return listOf(stone * 2024)
}