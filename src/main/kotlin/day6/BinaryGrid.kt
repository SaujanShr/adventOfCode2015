package day6

class BinaryGrid: Grid {
    private val grid = MutableList(1000) { MutableList(1000) { false } }

    override fun getNumberOfLightsOn(): Int {
        return grid.sumOf { row ->
            row.count { it }
        }
    }

    override fun turnOn(start: Pair<Int, Int>, end: Pair<Int, Int>) {
        for (i in start.first .. end.first) {
            for (j in start.second .. end.second) {
                grid[i][j] = true
            }
        }
    }

    override fun turnOff(start: Pair<Int, Int>, end: Pair<Int, Int>) {
        for (i in start.first .. end.first) {
            for (j in start.second .. end.second) {
                grid[i][j] = false
            }
        }
    }

    override fun toggle(start: Pair<Int, Int>, end: Pair<Int, Int>) {
        for (i in start.first .. end.first) {
            for (j in start.second .. end.second) {
                grid[i][j] = !grid[i][j]
            }
        }
    }
}