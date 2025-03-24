import java.io.BufferedReader
import java.io.InputStreamReader

data class Student(
    val name: String,
    val liveDays: Int,
) {
    companion object {
        fun parseFrom(input: String): Student {
            return input.split(" ").run {
                val name = this[0]
                val day = this[1].toInt()
                val month = this[2].toInt()
                val year = this[3].toInt()
                val liveDays = ((2010 - year) * 10000) + (12 - month) * 100 + (31 - day)

                Student(name, liveDays)
            }
        }
    }
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = this.readLine().toInt()
    val students = mutableListOf<Student>()

    repeat(n) {
        Student.parseFrom(readLine()).let(students::add)
    }

    println(students.minBy { it.liveDays }.name)
    println(students.maxBy { it.liveDays }.name)
}