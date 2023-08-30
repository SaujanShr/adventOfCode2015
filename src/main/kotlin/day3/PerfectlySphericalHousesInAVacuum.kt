package day3

import getInput
val INPUT = getInput("day3")

fun run1(): String {
    return getHousesVisitedNormal(INPUT).size
        .toString()
}

fun run2(): String {
    return getHousesVisitedRobot(INPUT).size
        .toString()
}

fun getHousesVisitedNormal(directions: String): Set<Pair<Int, Int>> {
    var location = Pair(0, 0)
    val houses = mutableSetOf(location)

    directions
        .toCharArray()
        .forEach {
            location = nextLocation(location, it)
            houses.add(location)
        }

    return houses.toSet()
}

fun getHousesVisitedRobot(directions: String): Set<Pair<Int, Int>> {
    var santaLocation = Pair(0, 0)
    var robotLocation = Pair(0, 0)
    val houses = mutableSetOf(santaLocation)

    directions
        .chunked(2)
        .map { it.toCharArray() }
        .forEach { (santaDir, robotDir) ->
            santaLocation = nextLocation(santaLocation, santaDir)
            robotLocation = nextLocation(robotLocation, robotDir)
            houses.add(santaLocation)
            houses.add(robotLocation)
        }

    return houses.toSet()
}

fun nextLocation(location: Pair<Int, Int>, direction: Char): Pair<Int, Int> {
    return when (direction) {
        '^' -> Pair(location.first, location.second+1)
        'v' -> Pair(location.first, location.second-1)
        '<' -> Pair(location.first-1, location.second)
        '>' -> Pair(location.first+1, location.second)
        else -> Pair(0, 0)
    }
}