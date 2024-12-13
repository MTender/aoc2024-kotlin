package day13

fun main() {
    val machines = Day13Input.read("input.txt")

    var totalCost = 0L

    for (machine in machines) {
        val winCost = testMachine(machine)
        if (winCost != null) {
            totalCost += winCost
        }
    }

    println(totalCost)
}

fun testMachine(machine: Machine): Long? {
    if (canCompleteWith(machine.target, machine.b)) {
        return machine.target.x / machine.b.x
    }

    var x = 0L
    var y = 0L
    var cost = 0L

    for (i in 0..99) {
        x += machine.a.x
        y += machine.a.y
        cost += 3

        val remainder = machine.target - Vector(x, y)
        if (canCompleteWith(remainder, machine.b)) {
            return cost + remainder.x / machine.b.x
        }
    }

    return null
}

fun canCompleteWith(remainder: Vector, button: Vector): Boolean {
    return remainder.x % button.x == 0L &&
            remainder.y % button.y == 0L &&
            remainder.x / button.x == remainder.y / button.y
}