package day02

import java.io.File

fun main(){
    val input = "src/main/resources/day02/day02input"
    var safeReportsPartOne = 0
    var safeReportsPartTwo = 0

    File(input).forEachLine { line ->
        val array = line.split(" ").map { it.toInt() }.toMutableList()
        if (partOne(array)) {
            safeReportsPartOne++
        }
    }

    File(input).forEachLine { line ->
        val array = line.split(" ").map { it.toInt() }.toMutableList()
        if (partTwo(array)) {
            safeReportsPartTwo++
        }
    }

    println("Safe reports part one: $safeReportsPartOne")
    println("Safe reports part two: $safeReportsPartTwo")
}

fun partOne(array: MutableList<Int>): Boolean {
    val isIncreasing = array[1] > array[0]

    for (i in 0 until array.size - 1) {
        val currentValue = array[i]
        val nextValue = array[i + 1]
        val difference = nextValue - currentValue

        if (difference !in 1..3 && difference !in -3..-1) {
            return false
        }

        if ((isIncreasing && nextValue < currentValue) || (!isIncreasing && nextValue > currentValue)) {
            return false
        }
    }
    return true
}

fun partTwo(array: MutableList<Int>): Boolean {
    if (partOne(array)) return true

    for (i in array.indices) {
        val newArray = array.toMutableList()
        newArray.removeAt(i)
        if (partOne(newArray)) {
            return true
        }
    }
    return false
}