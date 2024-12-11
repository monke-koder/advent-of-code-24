import java.lang.Math
import java.io.File

fun main() {
    // Defining file path
    val filePath = "C:\\Users\\Talha\\Downloads\\Advent of Code\\Day1Input.txt"

    // Initializing mutable lists to store the values
    val leftColumn = mutableListOf<Int>()
    val rightColumn = mutableListOf<Int>()

    // Reading the file line by line
    File(filePath).forEachLine { line ->
        // Spliting each line into two parts (assuming the columns are separated by space or tab)
        val columns = line.split("\\s+".toRegex())

        // Checking if the line has exactly two columns
        if (columns.size == 2) {
            // Parse the left and right column values and add to respective lists
            leftColumn.add(columns[0].toInt())
            rightColumn.add(columns[1].toInt())
        }
    }

    // Converting the lists to arrays
    val array1 = leftColumn.toIntArray()
    val array2 = rightColumn.toIntArray()

    // Sorting the arrays
    val sarray1 = array1.sorted()
    val sarray2 = array2.sorted()

    // Day 1 Part 1 Solution
    var totalDistance = 0

    // Finding the distance between the corresponding elements of both arrays and adding into total distance
    for (i in sarray1.indices) {
        val distance = Math.abs(sarray1[i] - sarray2[i])
        totalDistance += distance
    }

    println(totalDistance)

    // Day 1 Part 2 Solution
    // Converting the rightColumn list to a map (number -> count) to track occurrences
    val rightColumnFrequency = rightColumn.groupingBy { it }.eachCount()

    var similarityScore = 0

    // Iterate through each number in the leftColumn
    for (number in leftColumn) {
        val countInRightColumn = rightColumnFrequency[number] ?: 0
        similarityScore += number * countInRightColumn
    }

    println(similarityScore)
}
