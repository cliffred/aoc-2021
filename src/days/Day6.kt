package days

import model.Day

fun main() {
    Day6().solve()
}

class Day6 : Day() {
    private val fishPerSpawnDaysAtStart =
        input().first().split(",")
            .map { it.toInt() }
            .groupingBy { it }
            .eachCount()
            .mapValues { it.value.toLong() }

    override fun part1(): Long {
        val fishPerSpawnDays = procreate(80)
        return fishPerSpawnDays.values.sum()
    }

    override fun part2(): Long {
        val fishPerSpawnDays = procreate(256)
        return fishPerSpawnDays.values.sum()
    }

    private fun procreate(days: Int): Map<Int, Long> {
        var fpsd = fishPerSpawnDaysAtStart
        repeat(days) {
            fpsd = fpsd.flatMap { (days, num) ->
                if (days == 0) {
                    listOf(6 to num, 8 to num)
                } else {
                    listOf(days - 1 to num)
                }
            }.groupBy({ it.first }, { it.second })
                .mapValues { it.value.sum() }
        }
        return fpsd
    }
}
