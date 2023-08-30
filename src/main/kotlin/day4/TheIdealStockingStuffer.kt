package day4

import java.security.MessageDigest
import kotlin.text.Charsets.UTF_8

import getInput

val INPUT = getInput("day4")

fun run1(): String {
    return getAdventCoinFive(INPUT)
        .toString()
}

fun run2(): String {
    return getAdventCoinSix(INPUT)
        .toString()
}

fun getAdventCoinFive(secret: String): Int {
    var number = 0
    while (!hashStartsWithZeroes(md5(secret, ++number), 5)) {}

    return number
}

fun getAdventCoinSix(secret: String): Int {
    var number = 0
    while (!hashStartsWithZeroes(md5(secret, ++number), 6)) {}

    return number
}

fun md5(secret: String, number: Int): ByteArray {
    return MessageDigest
        .getInstance("MD5")
        .digest("$secret${number.toString()}".toByteArray(UTF_8))
}

fun hashStartsWithZeroes(hash: ByteArray, n: Int): Boolean {
    for (i in 0 until n/2) {
        if (hash[i] != 0x0.toByte()) {
            return false
        }
    }
    return hash[n/2] <= 0xF.toByte()
}