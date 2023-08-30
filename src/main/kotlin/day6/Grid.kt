package day6

interface Grid {
    fun getNumberOfLightsOn(): Int
    fun turnOn(start: Pair<Int, Int>, end: Pair<Int, Int>)
    fun turnOff(start: Pair<Int, Int>, end: Pair<Int, Int>)
    fun toggle(start: Pair<Int, Int>, end: Pair<Int, Int>)
}