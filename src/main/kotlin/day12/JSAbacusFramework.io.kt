package day12

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonNull
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.JsonPrimitive
import getInput

val INPUT = getInput("day12")

fun run1(): String {
    return INPUT
        .toJSONObject()
        .getAllNumbers()
        .sum()
        .toString()
}

fun run2(): String {
    return INPUT
        .toJSONObject()
        .getAllNumbersDiscountRed()
        .sum()
        .toString()
}

fun String.toJSONObject(): JsonObject {
    return JsonParser
            .parseString(this)
            .asJsonObject
}

fun JsonElement.getAllNumbers(): List<Int> {
    return when (this) {
        is JsonPrimitive -> when (this.isNumber) {
            true -> listOf(this.asInt)
            false -> listOf()
        }
        is JsonArray -> this
            .asJsonArray
            .flatMap { it.getAllNumbers() }
        is JsonObject -> this
            .asJsonObject
            .entrySet()
            .flatMap { it.value.getAllNumbers() }
        else -> listOf()
    }
}

fun JsonElement.getAllNumbersDiscountRed(): List<Int> {
    return when (this) {
        is JsonPrimitive -> when (this.isNumber) {
            true -> listOf(this.asInt)
            false -> listOf()
        }
        is JsonArray -> this
            .asJsonArray
            .flatMap { it.getAllNumbersDiscountRed() }
        is JsonObject -> when (this.hasRed()) {
            true -> listOf()
            false -> this
                .asJsonObject
                .entrySet()
                .flatMap { it.value.getAllNumbersDiscountRed() }
        }
        else -> listOf()
    }
}

fun JsonObject.hasRed(): Boolean {
    return this
        .entrySet()
        .map { it.value }
        .any {
            it.isJsonPrimitive && it.asString.equals("red")
        }
}