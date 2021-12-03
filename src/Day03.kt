import COMMON.LEAST
import COMMON.MOST
import org.junit.Assert.assertEquals
import org.junit.Test

fun main() {
    val lines: List<String> = readInput("Day03")

//    partOne(lines)
    partTwo(lines)
}

fun partOne(lines: List<String>) {
    var mcb = ""
    var lcb = ""

    // iterate over each index of strings 0..4
    for (i in 0 until lines.first().length) {
        val m = findCommonBit(lines, i, '0', MOST)
        val l = findCommonBit(lines, i, '0', LEAST)
        mcb += m
        lcb += l
    }

    // Once we have MCB and LCB...
    // convert both to decimal
    val gammaRate = mcb.binToInt()
    val epsilonRate = lcb.binToInt()

    // multiply decimals
    println(gammaRate * epsilonRate)
}

fun partTwo(lines: List<String>) {
    val oxygenGeneratorRating = dfs(lines, 0, '1', MOST)
    val co2ScrubberRating = dfs(lines, 0, '0', LEAST)
    val lifeSupportRating = oxygenGeneratorRating.binToInt() * co2ScrubberRating.binToInt()

    println(lifeSupportRating)
}

enum class COMMON {
    MOST,
    LEAST,
}

fun dfs(lines: List<String>, index: Int, tieBreakerChar: Char, common: COMMON): String {
    // recurse on binary string collection
    // keep filtering collection down based on most common bit
    if (lines.size == 1 || lines.first().length == index) {
        return lines.first()
    }

    val bit = findCommonBit(lines, index, tieBreakerChar, common)

    // filter lines to only contain entries that have this bit at that index.
    val newLines = lines.filter {
        it[index] == bit
    }

    return dfs(newLines, index + 1, tieBreakerChar, common)
}

fun findCommonBit(lines: List<String>, index: Int, tieBreakerChar: Char, common: COMMON): Char {
    var count = 0
    // iterate over each entry and do count to find most common bit (MCB) for this index.
    lines.forEach {
        when (it[index]) {
            '0' -> count--
            '1' -> count++
        }
    }

    if (count == 0) {
        return tieBreakerChar
    }

    val mostCommon = if (count < 0) {
        0
    } else {
        1
    }

    val bit = when (common) {
        MOST -> mostCommon
        LEAST -> (if (mostCommon == 0) 1 else 0)
    }

    return bit.toString()[0]
}

class TestClass {
    @Test
    fun thing() {
        val lines: List<String> = arrayListOf(
            "00100",
            "11110",
            "10110",
            "10111",
            "10101",
            "01111",
            "00111",
            "11100",
            "10000",
            "11001",
            "00010",
            "01010",
        )
        val co2ScrubberRating = dfs(lines, 0, '0', LEAST)
        assertEquals("01010", co2ScrubberRating)
    }
}