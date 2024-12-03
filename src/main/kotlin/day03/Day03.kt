package day03

import java.io.File

fun main(){
    val input = "src/main/resources/day03/day03input"
    val regexOne = Regex("mul\\(\\d{1,3},\\d{1,3}\\)")
    val regexTwo = Regex("do\\(\\)|don't\\(\\)|mul\\(\\d+,\\d+\\)")
    val matchingSumsPartOne = mutableListOf<String>()
    val matchingSumsPartTwo = mutableListOf<String>()

    File(input).useLines { lines ->
        lines.forEach { line ->
            val matches = regexOne.findAll(line)
            matches.forEach { match ->
                matchingSumsPartOne.add(match.value)
            }
        }
    }

    var doFound = true
    File(input).useLines { lines ->
        lines.forEach { line ->
            val matches = regexTwo.findAll(line)
            matches.forEach { match ->
                when {
                    match.value == "do()" -> doFound = true
                    match.value == "don't()" -> doFound = false
                    doFound && regexOne.matches(match.value) -> matchingSumsPartTwo.add(match.value)
                }
            }
        }
    }

    println(matchSums(matchingSumsPartOne))
    println(matchSums(matchingSumsPartTwo))
}

fun matchSums(matchingSums: MutableList<String>): Int {
    var sum = 0

    for(i in matchingSums){
        val split = i.split(",")
        val a = split[0].substring(4).toInt()
        val b = split[1].substring(0, split[1].length - 1).toInt()
        sum += a * b
    }

    return sum
}