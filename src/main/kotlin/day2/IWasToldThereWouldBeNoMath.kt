package day2

import getInput
val INPUT = getInput("day2")

fun run1(): String {
    return getWrappingSurfaceArea(INPUT)
        .toString()
}

fun run2(): String {
    return getBowLength(INPUT)
        .toString()
}

fun getWrappingSurfaceArea(list: String): Int {
    return list
        .split('\n')
        .sumOf { dimensions ->
            val (l, w, h) = getDimensions(dimensions)

            val faces = listOf(l*w, l*h, w*h)
            (faces.sum() * 2) + faces.min()
        }
}

fun getBowLength(list: String): Int {
    return list
        .split('\n')
        .sumOf { dimensions ->
            val (l, w, h) = getDimensions(dimensions)

            minOf(l+l+w+w, l+l+h+h, w+w+h+h) + l*w*h
        }
}

fun getDimensions(dimensions: String): List<Int> {
    return dimensions
        .split('x')
        .map { it.toInt() }
}