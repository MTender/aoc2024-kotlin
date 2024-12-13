package day13

data class Vector(
    val x: Long,
    val y: Long
) {
    operator fun plus(other: Vector) = Vector(x + other.x, y + other.y)

    operator fun minus(other: Vector) = Vector(x - other.x, y - other.y)
}

data class Machine(
    val a: Vector,
    val b: Vector,
    val target: Vector
)