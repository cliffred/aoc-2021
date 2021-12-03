package days

import model.Day

fun main() {
    Day2().solve()
}

class Day2 : Day() {
    override fun part1() = input().fold(Position()) { position, instruction ->
        val (d, unit) = instruction.split(" ")
        val direction = Direction.valueOf(d.uppercase())
        position.changeCourse1(direction, unit.toLong())
    }.let { it.horizontal * it.depth }

    override fun part2() = input().fold(Position()) { position, instruction ->
        val (d, unit) = instruction.split(" ")
        val direction = Direction.valueOf(d.uppercase())
        position.changeCourse2(direction, unit.toLong())
    }.let { it.horizontal * it.depth }
}

enum class Direction { FORWARD, UP, DOWN }

data class Position(val horizontal: Long = 0, val depth: Long = 0, val aim: Long = 0) {
    fun changeCourse1(direction: Direction, units: Long): Position = when (direction) {
        Direction.FORWARD -> copy(horizontal = horizontal + units)
        Direction.UP -> copy(depth = depth - units)
        Direction.DOWN -> copy(depth = depth + units)
    }

    fun changeCourse2(direction: Direction, units: Long): Position = when (direction) {
        Direction.FORWARD -> copy(horizontal = horizontal + units, depth = depth + aim * units)
        Direction.UP -> copy(aim = aim - units)
        Direction.DOWN -> copy(aim = aim + units)
    }
}
