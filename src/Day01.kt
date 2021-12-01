fun main() {
    val depths: List<Int> = readInput("Day01").map { it.toInt() }
    var count = 0

    fun part2() {
        var currentThree = arrayOf(depths[0], depths[1], depths[2])
        for (i in 1..depths.size - 3) {
            // compare groups of 3 until the end.
            val nextThree = arrayOf(depths[i], depths[i + 1], depths[i + 2])
            if (currentThree.sum() < nextThree.sum()) {
                count++
            }
            currentThree = nextThree
        }
    }

    fun part1() {
        val i = depths.iterator()
        var current = i.next()
        while (i.hasNext()) {
            val next = i.next()
            if (next > current) {
                count++
            }
            current = next
        }
    }

    part2()

    println(count)
}
