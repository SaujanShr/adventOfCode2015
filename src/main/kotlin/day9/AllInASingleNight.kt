package day9

import getInput

val INPUT = getInput("day9")

fun run1(): String {
    return INPUT
        .getRoutes()
        .getLocations()
        .getOptimalDistance()
        .toString()
}

fun run2(): String {
    return INPUT
        .getRoutes()
        .getLocations()
        .getLeastOptimalDistance()
        .toString()
}

fun String.getRoutes() = this
    .split('\n')
    .map {
        val path = it.split(' ')

        listOf(path[0], path[2], path[4])
    }

fun List<List<String>>.getLocations(): Map<String,Location> {
    val locations = this
        .flatMap { listOf(it[0], it[1]) }
        .distinct()
        .associateWith { Location(it) }

    this
        .forEach { (f, t, d) ->
            val from = locations[f]!!
            val to = locations[t]!!
            val distance = d.toInt()

            from.addRoute(to, distance)
            to.addRoute(from, distance)
        }

    return locations
}

fun Map<String,Location>.getOptimalDistance(): Int = this.values
    .minOf {
        this.values
            .toSet()
            .minus(it)
            .getOptimalDistance(it)
    }

fun Set<Location>.getOptimalDistance(prev: Location): Int {
    if (this.isEmpty()) {
        return 0
    }

    return this
        .minOf {
            this
                .minus(it)
                .getOptimalDistance(it)
                .plus(prev.getDistanceToLocation(it))
        }
}

fun Map<String,Location>.getLeastOptimalDistance(): Int = this.values
    .maxOf {
        this.values
            .toSet()
            .minus(it)
            .getLeastOptimalDistance(it)
    }

fun Set<Location>.getLeastOptimalDistance(prev: Location): Int {
    if (this.isEmpty()) {
        return 0
    }

    return this
        .maxOf {
            this
                .minus(it)
                .getLeastOptimalDistance(it)
                .plus(prev.getDistanceToLocation(it))
        }
}