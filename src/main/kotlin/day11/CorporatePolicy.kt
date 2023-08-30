package day11

import getInput

val Input = getInput("day11")

fun run1(): String {
    return Input.incrementUntilValid()
}

fun run2(): String {
    return Input
        .incrementUntilValid()
        .incrementUntilValid()
}

fun String.incrementUntilValid(): String {
    var str = this.increment()
    while (!str.isValid()) {
        str = str.increment()
    }
    return str
}

fun String.increment(): String {
    val arr = this.toCharArray()

    for (i in this.indices.reversed()) {
        if (this[i] < 'z') {
            arr[i] = this[i] + 1
            break
        }
        arr[i] = 'a'
    }

    return arr.concatToString()
}

fun String.isValid(): Boolean {
    return this.containsIncreasingStraight(3)
        .and(this.contains("i|o|u".toRegex()).not())
        .and(this.containsOverlappingPairs(2))
}

fun String.containsIncreasingStraight(n: Int): Boolean {
    var prev = 0
    for (curr in 1 until this.length) {
        if (this[curr] != this[curr-1]+1) {
            prev = curr
        }
        if (curr - prev == n - 1) {
            return true
        }
    }

    return false
}

fun String.containsOverlappingPairs(n: Int): Boolean {
    return "abcdefghijklmnopqrstuvwxyz"
        .count { this.contains("$it$it") } >= n

}