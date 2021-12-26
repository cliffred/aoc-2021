package days

import model.Day

fun main() {
    Day4().solve()
}

class Day4 : Day() {
    private val numbers = input().first().split(",").map { it.toInt() }

    private val bingoCards = input()
        .drop(1)
        .filter { it.isNotBlank() }
        .map { it.split(" ").filter { it.isNotBlank() }.map { it.toInt() } }
        .chunked(5)
        .map { BingoCard(it) }
        .toList()

    override fun part1(): Long {
        numbers.forEach { num ->
            bingoCards.forEach { card ->
                if (card.newNumber(num)) {
                    return card.remaining().sum().toLong() * num
                }
            }
        }
        throw IllegalStateException("No winner")
    }

    override fun part2(): Long {
        val bcs = bingoCards.toMutableList()
        numbers.forEach { num ->
            val it = bcs.iterator()
            it.forEach { card ->
                if (card.newNumber(num)) {
                    it.remove()
                    if (bcs.isEmpty()) {
                        return card.remaining().sum().toLong() * num
                    }
                }
            }
        }
        throw IllegalStateException("No winner")
    }
}

class BingoCard(grid: List<List<Int>>) {

    private val sets: MutableList<MutableSet<Int>> = mutableListOf()

    init {
        for (i in grid.indices) {
            sets += grid[i].toMutableSet() // add row
            val col = mutableSetOf<Int>()
            for (j in grid[i].indices) {
                col += grid[j][i]
            }
            sets += col
        }
    }

    fun newNumber(num: Int): Boolean {
        sets.forEach {
            it.remove(num)
        }
        return sets.any { it.isEmpty() }
    }

    fun remaining() = sets.flatten().distinct()
}
