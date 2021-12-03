package days

import model.Day
import kotlin.math.ceil

fun main() {
    Day3().solve()
}

class Day3 : Day() {
    private val inputSize = input().count()

    override fun part1(): Long {
        val oneBits = numberOfOneBits(input())
        val gamma = oneBits.map { if (it > inputSize / 2) 1 else 0 }.toBinaryLong()
        val epsilon = gamma.inverseBits()
        return gamma * epsilon
    }

    override fun part2(): Long {
        val input = input().map { it.toCharArray().map { it.digitToInt() } }.toList()
        val oxygenRating = findRating(input, Find.MAJORITY)
        val scrubberRating = findRating(input, Find.MINORITY)
        return oxygenRating * scrubberRating
    }
}

fun findRating(input: List<List<Int>>, find: Find): Long {
    var numbers = input
    var pos = 0
    val majorityValue = when (find) {
        Find.MAJORITY -> 1
        Find.MINORITY -> 0
    }
    while (numbers.size != 1) {
        val oneBits = numbers.count { it[pos] == 1 }
        val keep = if (oneBits >= ceil(numbers.size / 2.0)) majorityValue else majorityValue.xor(1)
        numbers = numbers.filter { it[pos] == keep }
        pos++
    }
    return numbers.first().toBinaryLong()
}

enum class Find { MAJORITY, MINORITY }

fun numberOfOneBits(input: Sequence<String>): List<Int> {
    return input.map { it.toCharArray().map { it.digitToInt() } }
        .reduce { acc, bits -> acc.zip(bits) { a, b -> a + b } }
}

fun Long.inverseBits(): Long {
    val x = this.toString(2).length.let { "1".repeat(it) }.toLong(2)
    return this xor x
}

fun List<Int>.toBinaryLong() = joinToString("").toLong(2)
fun Sequence<Int>.toBinaryLong() = joinToString("").toLong(2)
