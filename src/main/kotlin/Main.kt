import java.io.File

fun main(args: Array<String>) {
//    println("${day1.run1()}\n${day1.run2()}")
//    println("${day2.run1()}\n${day2.run2()}")
//    println("${day3.run1()}\n${day3.run2()}")
//    println("${day4.run1()}\n${day4.run2()}")
//    println("${day5.run1()}\n${day5.run2()}")
//    println("${day6.run1()}\n${day6.run2()}")
//    println("${day7.run1()}\n${day7.run2()}")
//    println("${day8.run1()}\n${day8.run2()}")
//    println("${day9.run1()}\n${day9.run2()}")
//    println("${day10.run1()}\n${day10.run2()}")
//    println("${day11.run1()}\n${day11.run2()}")
    println("${day12.run1()}\n${day12.run2()}")
}

fun getInput(folderName: String): String {
    return File("src/main/kotlin/$folderName/input.txt")
        .inputStream()
        .bufferedReader()
        .use { it.readText() }
}