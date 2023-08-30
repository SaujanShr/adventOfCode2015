package day10

import getInput

val INPUT = getInput("day10")

fun run1(): String {
    return INPUT
        .getNext(40)
        .length
        .toString()
}

fun run2(): String {
    return INPUT
        .getNext(50)
        .length
        .toString()
}

fun String.getNext(n: Int): String {
    var str = this
    for (i in 1..n) {
        str = str.getNext()
    }
    return str
}

fun String.getNext(): String {
    val counters: MutableList<Pair<Char,Int>> = this
        .removeAdjacent()
        .map { Pair(it, 0) }
        .toMutableList()

    var prev = 0
    var curr = 1
    for (i in counters.indices) {
        while (curr < this.length && this[curr] == counters[i].first) {
            curr++
        }
        counters[i] = Pair(counters[i].first, curr-prev)
        prev = curr
    }

    return counters.joinToString("") { "${it.second}${it.first}" }
}

fun String.removeAdjacent(): String = this
    .zipWithNext()
    .filter{ it.first != it.second }
    .map{ it.first }
    .plus(this.last())
    .joinToString("")