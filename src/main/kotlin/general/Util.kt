package general

import kotlin.math.log10

fun Number.digitsCount(): Int {
    return log10(this.toDouble()).toInt() + 1
}

enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
}