package day5

import getInput
val INPUT = getInput("day5")

fun run1(): String {
    return getNiceStrings1(INPUT).size
        .toString()
}

fun run2(): String {
    return getNiceStrings2(INPUT).size
        .toString()
}

fun getNiceStrings1(list: String): List<String> {
    return list
        .split('\n')
        .filter {
            containsThreeVowels(it) &&
            containsRepeatedCharacters(it) &&
            !containsBadStrings(it)
        }
}

fun getNiceStrings2(list: String): List<String> {
    return list
        .split('\n')
        .filter {
            containsRepeatedPair(it) &&
            containsRepeatedSplitCharacter(it)
        }
}

fun containsThreeVowels(str: String): Boolean {
    return """[aeiou]"""
        .toRegex()
        .findAll(str).count() >= 3
}

fun containsRepeatedCharacters(str: String): Boolean {
    return """(\w)\1"""
        .toRegex()
        .find(str) != null
}

fun containsBadStrings(str: String): Boolean {
    return """ab|cd|pq|xy"""
        .toRegex()
        .find(str) != null
}

fun containsRepeatedPair(str: String): Boolean {
    return """(..).*\1"""
        .toRegex()
        .find(str) != null
}

fun containsRepeatedSplitCharacter(str: String): Boolean {
    return """(.).\1"""
        .toRegex()
        .find(str) != null
}