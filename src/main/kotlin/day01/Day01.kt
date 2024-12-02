package org.example.day01

import java.io.File

fun main() {
    val arrayOne = mutableListOf<Int>()
    val arrayTwo = mutableListOf<Int>()

    // read input
    val input = "src/main/resources/day01/day01input"
    val lines = File(input).readLines()

    // parse input
    for (line in lines) {
        val parts = line.split("\\s{2,}".toRegex())
        arrayOne.add(parts[0].toInt())
        arrayTwo.add(parts[1].toInt())
    }

    // sort arrays
    arrayOne.sort()
    arrayTwo.sort()

    println("Part One: ${partOne(arrayOne, arrayTwo)}")
    println("Part Two: ${partTwo(arrayOne, arrayTwo)}")
}

fun partOne(arrayOne: MutableList<Int>, arrayTwo: MutableList<Int>): Int {
    var totalDistance = 0

    // calculate distance
    for (i in arrayOne.indices) {
        totalDistance += if (arrayOne[i] > arrayTwo[i])
            arrayOne[i] - arrayTwo[i]
        else {
            arrayTwo[i] - arrayOne[i]
        }
    }

    return totalDistance
}

fun partTwo(arrayOne: MutableList<Int>, arrayTwo: MutableList<Int>): Int {
    var similarityScore = 0
    val frequencyMap = mutableMapOf<Int, Int>()

    // create frequency map
    for (value in arrayTwo) {
        frequencyMap[value] = frequencyMap.getOrDefault(value, 0) + 1
    }

    // calculate similarity score
    for (value in arrayOne) {
        if (frequencyMap.containsKey(value)) {
            similarityScore += value * frequencyMap[value]!!
        }
    }

    return similarityScore
}