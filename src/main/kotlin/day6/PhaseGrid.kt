package day6

class PhaseGrid: Grid {
    private val grid = MutableList(1000) { MutableList(1000) { 0 } }

    override fun getNumberOfLightsOn(): Int {
        return grid.sumOf { it.sum() }
    }

    override fun turnOn(start: Pair<Int, Int>, end: Pair<Int, Int>) {
        for (i in start.first .. end.first) {
            for (j in start.second .. end.second) {
                grid[i][j] += 1
            }
        }
    }

    override fun turnOff(start: Pair<Int, Int>, end: Pair<Int, Int>) {
        for (i in start.first .. end.first) {
            for (j in start.second .. end.second) {
                grid[i][j] -= if (grid[i][j] > 0) 1 else 0
            }
        }
    }

    override fun toggle(start: Pair<Int, Int>, end: Pair<Int, Int>) {
        for (i in start.first .. end.first) {
            for (j in start.second .. end.second) {
                grid[i][j] += 2
            }
        }
    }
}