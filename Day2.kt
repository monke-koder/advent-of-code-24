import java.io.File

fun main() {
    // File path to the text file containing the reports
    val filePath = "C:\\Users\\Talha\\Downloads\\advent-of-code-24\\Day2Input.txt"

    // Read the data from the file and process each line as an array of integers
    val reports = File(filePath).readLines().map { line ->
        line.split(" ").map { it.toInt() }.toTypedArray()
    }

    // Function to check if a report is safe
    fun isSafe(report: Array<Int>): Boolean {
        var isIncreasing = false
        var isDecreasing = false

        // Check differences and determine if it's increasing or decreasing
        for (i in 0 until report.size - 1) {
            val diff = report[i + 1] - report[i]
            if (diff > 0) isIncreasing = true
            if (diff < 0) isDecreasing = true
            if (diff !in -3..-1 && diff !in 1..3) {
                // If difference is not between -3 and -1, or 1 and 3, it's unsafe
                return false
            }
        }

        // For it to be safe, it must be either entirely increasing or decreasing
        return (isIncreasing xor isDecreasing) // xor ensures it's only one of the two
    }

    // Day 1 Part 1 Solution
    // Count the number of safe reports
    val safeReportsCount1 = reports.count { isSafe(it) }

    // Output the result
    println("Number of safe reports: $safeReportsCount1")


    // Day 1 Part 2 Solution
    // Function to check if removing one element makes the report safe
    fun isSafeWithOneRemoval(report: Array<Int>): Boolean {
        // Try removing each level one by one and check if the resulting report is safe
        for (i in report.indices) {
            val newReport = report.filterIndexed { index, _ -> index != i }.toTypedArray()
            if (isSafe(newReport)) {
                return true
            }
        }
        return false
    }

    // Count the number of safe reports, including those that become safe by removing one level
    val safeReportsCount2 = reports.count {
        isSafe(it) || isSafeWithOneRemoval(it)
    }

    // Output the result
    println("Number of safe reports after problem damper: $safeReportsCount2")
}
