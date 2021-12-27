package days

import model.Day
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor

fun main() {
    Day7().solve()
}

class Day7 : Day() {

    private val crabs = input().first().split(",").map { it.toInt() }

    override fun part1(): Long {
        val median = crabs.median()
        return crabs.sumOf {
            abs(median - it)
        }.toLong()
    }

    override fun part2(): Long {
        val min = floor(crabs.average()).toInt()
        val max = ceil(crabs.average()).toInt()
        val distances = (min..max).map { position ->
            crabs.sumOf {
                fuel(abs(position - it))
            }
        }
        return distances.minOf { it }.toLong()
    }
}

private fun Iterable<Int>.median(): Int {
    val sorted = this.sorted()
    return if (sorted.size % 2 == 0) {
        (sorted[sorted.size / 2] + sorted[sorted.size / 2 - 1]) / 2
    } else {
        sorted[sorted.size / 2]
    }
}

private fun fuel(x: Int) = (1..x).sum()
