package day1

import getInput
val INPUT = getInput("day1")

fun run1(): String {
    return getFloor(INPUT)
        .toString()
}

fun run2(): String {
    return getFloorIndexes(INPUT)[-1]!!
        .plus(1)
        .toString()
}

fun getFloor(directions: String): Int {
    return directions
        .toCharArray()
        .sumOf { getDirection(it) }
}

fun getFloorIndexes(directions: String): Map<Int, Int> {
    val floorInd = mutableMapOf<Int, Int>()
    var floor = 0

    directions
        .toCharArray()
        .forEachIndexed { index, c ->
            floor += getDirection(c)
            floorInd.putIfAbsent(floor, index)
        }

    return floorInd.toMap()
}

fun getDirection(dir: Char): Int {
    return when (dir) {
        '(' -> 1
        ')' -> -1
        else -> 0
    }
}