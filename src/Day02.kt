fun main() {
    var horizontalPosition = 0
    var aim = 0
    var depth = 0

    readInput("Day02").forEach {
        val (direction, unitString) = it.split(" ")
        val unit = unitString.toInt()

        when(direction) {
            "forward" -> {
                horizontalPosition += unit
                depth += aim * unit
            }
            "down" -> aim += unit
            "up" -> aim -= unit
        }
    }

    println(horizontalPosition * depth)
}
