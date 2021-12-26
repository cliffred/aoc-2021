package days

import model.Day
import kotlin.math.abs
import kotlin.math.max

fun main() {
    Day5().solve()
}

class Day5 : Day() {
    private val lines = input().map {
        val (start, end) = it.split(" -> ")
        val (xStart, yStart) = start.split(",").map { it.toInt() }
        val (xEnd, yEnd) = end.split(",").map { it.toInt() }
        Line(Point(xStart, yStart), Point(xEnd, yEnd))
    }.toList()

    override fun part1() = lines
        .filter { it.start.x == it.end.x || it.start.y == it.end.y }
        .flatMap { it.points }
        .groupingBy { it }
        .eachCount()
        .count { it.value >= 2 }
        .toLong()

    override fun part2() = lines
        .flatMap { it.points }
        .groupingBy { it }
        .eachCount()
        .count { it.value >= 2 }
        .toLong()
}

data class Point(val x: Int, val y: Int)

data class Line(val start: Point, val end: Point) {
    val points: List<Point> by lazy {
        val xMove = end.x - start.x
        val yMove = end.y - start.y
        require(xMove == 0 || yMove == 0 || abs(xMove) == abs(yMove))
        val num = max(abs(xMove), abs(yMove))
        val points = mutableListOf<Point>()
        val xAcc = acc(xMove)
        val yAcc = acc(yMove)
        repeat(num) {
            points += Point(start.x + it * xAcc, start.y + it * yAcc)
        }
        points += end
        points
    }

    private fun acc(i: Int) = when {
        i > 0 -> 1
        i < 0 -> -1
        else -> 0
    }
}
