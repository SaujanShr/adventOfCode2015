package day8

import getInput

val INPUT = getInput("day8")

fun run1(): String = INPUT
    .split('\n')
    .sumOf(String::getStringValue)
    .toString()

fun run2(): String = INPUT
    .split('\n')
    .sumOf(String::getEncodedStringValue)
    .toString()

fun String.getStringValue(): Int = this
    .length
    .minus(this.getStringMemoryLength())

fun String.getStringMemoryLength(): Int = this
    .drop(1)
    .dropLast(1)
    .recN(0)

fun String.recN(pos: Int): Int {
    if (pos == this.length) {
        return 0
    }

    return when (this[pos]) {
        '\\' -> 1 + this.recE(pos+1)
        else -> 1 + this.recN(pos+1)
    }
}

fun String.recE(pos: Int): Int = when (this[pos]) {
    'x' -> this.recN(pos+3)
    else -> this.recN(pos+1)
}

fun String.getEncodedStringValue(): Int = this
        .getEncodedLength()
        .minus(this.length)

fun String.getEncodedLength(): Int = this.sumOf {
    when (it) {
        '\\' -> 2
        '"' -> 2
        else -> 1
    } as Int
}.plus(2)