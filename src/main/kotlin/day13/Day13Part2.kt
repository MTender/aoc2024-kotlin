package day13

fun main() {
    val machines = Day13Input.read("input.txt")
        .map { Machine(it.a, it.b, it.target + Vector(10_000_000_000_000, 10_000_000_000_000)) }

    var totalCost = 0L

    for (machine in machines) {
        val winCost = testMachineWithMath(machine)
        if (winCost != null) {
            totalCost += winCost
        }
    }

    println(totalCost)
}

fun testMachineWithMath(machine: Machine): Long? {
    val bNumerator = machine.a.y * machine.target.x - machine.a.x * machine.target.y
    val bDenominator = machine.a.y * machine.b.x - machine.a.x * machine.b.y
    if (bNumerator % bDenominator != 0L) return null

    val bPresses = bNumerator / bDenominator

    val aNumerator = machine.target.x - machine.b.x * bPresses
    val aDenominator = machine.a.x
    if (aNumerator % aDenominator != 0L) return null

    val aPresses = aNumerator / aDenominator

    return bPresses + aPresses * 3
}