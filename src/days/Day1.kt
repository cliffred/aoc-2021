package days

import model.Day

fun main() {
    Day1().solve()
}

class Day1 : Day() {
    override fun part1() = input().map { it.toInt() }
        .zipWithNext { a, b -> b > a }
        .count { it }
        .toLong()

    override fun part2() = input().map { it.toInt() }
        .zipWithNext { a, b -> listOf(a, b) }
        .zipWithNext { a, b -> a + b[1] }
        .map { it.sum() }
        .zipWithNext { a, b -> b > a }
        .count { it }
        .toLong()
}
