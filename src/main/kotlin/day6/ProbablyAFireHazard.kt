package day6

import getInput

val INPUT = getInput("day6")

fun run1(): String {
    val grid = BinaryGrid()
    return setUpLights(INPUT, grid)
        .toString()
}

fun run2(): String {
    val grid = PhaseGrid()
    return setUpLights(INPUT, grid)
        .toString()
}

fun setUpLights(instructions: String, grid: Grid): Int {
    instructions
        .split('\n')
        .map { it.split(' ') }
        .forEach { processInstruction(it, grid) }

    return grid.getNumberOfLightsOn()
}

fun processInstruction(instruction: List<String>, grid: Grid) {
    when (instruction[0]) {
        "toggle" -> grid.toggle(
            getCoordinate(instruction[1]),
            getCoordinate(instruction[3])
        )
        else -> when (instruction[1]) {
            "on" -> grid.turnOn(
                getCoordinate(instruction[2]),
                getCoordinate(instruction[4])
            )
            "off" -> grid.turnOff(
                getCoordinate(instruction[2]),
                getCoordinate(instruction[4])
            )
        }
    }
}

fun getCoordinate(coordinate: String): Pair<Int, Int> {
    val xy = coordinate
        .split(',')
        .map { it.toInt() }

    return Pair(xy[0], xy[1])
}