data class Student(
    val id: String,
    val name: String,
    val age: Int,
    val major: String,
    val gpa: Double
)

class StudentManager {
    private val students = mutableListOf<Student>(
        Student("S01", "Nguyễn Hoàng", 20, "IT", 8.5),
        Student("S02", "Hoàng Văn Quốc Nhật", 22, "Marketing", 7.2),
        Student("S03", "Nguyễn Ngọc Hữu", 19, "IT", 9.1),
        Student("S04", "Dương Hoà", 21, "Finance", 4.5),
        Student("S05", "Dương Hào", 20, "Design", 6.8)
    )

    fun start() {
        while (true) {
            println("\n========== STUDENT MANAGEMENT ==========")
            println("1. Add student")
            println("2. Display all students")
            println("3. Search student (by partial name)")
            println("4. Calculate average GPA")
            println("5. Find student with highest GPA")
            println("6. Remove student")
            println("7. Advanced Requirements (Menu mở rộng)")
            println("0. Exit")
            println("========================================")
            print("Choose: ")

            when (readlnOrNull()?.trim()) {
                "1" -> addStudent()
                "2" -> displayAll(students)
                "3" -> searchStudentByName()
                "4" -> calculateAverageGPA()
                "5" -> findHighestGPA()
                "6" -> removeStudent()
                "7" -> advancedMenu()
                "0" -> return
                else -> println("Lựa chọn không hợp lệ. Vui lòng thử lại!")
            }
        }
    }

    private fun addStudent() {
        print("Nhập ID: ")
        val id = readln()
        print("Nhập họ tên: ")
        val name = readln()
        print("Nhập tuổi: ")
        val age = readln().toIntOrNull() ?: 0
        print("Nhập ngành học: ")
        val major = readln()
        print("Nhập GPA: ")
        val gpa = readln().toDoubleOrNull() ?: 0.0

        students.add(Student(id, name, age, major, gpa))
        println("Đã thêm sinh viên thành công!")
    }

    private fun displayAll(students: List<Student>) {
        if (students.isEmpty()) {
            println("Nothing to display")
        }

        students.forEach {
            println("ID: ${it.id} | Name: ${it.name} | Age: ${it.age} | Major: ${it.major} | GPA: ${it.gpa}")        }
    }
    private fun searchStudentByName() {
        print("Nhập tên sinh viên cần tìm: ")
        val keyword = readln().lowercase()
        val result = students.filter { it.name.lowercase().contains(keyword) }
        displayAll(result)
    }

    private fun calculateAverageGPA() {
        if (students.isEmpty()) return println("Không có sinh viên nào.")
        val avg = students.map { it.gpa }.average()
        println("GPA trung bình của tất cả sinh viên: $avg")
    }

    private fun findHighestGPA() {
        val maxStudent = students.maxByOrNull { it.gpa }
        if (maxStudent != null) {
            println("Sinh viên có GPA cao nhất:")
            displayAll(listOf(maxStudent))
        } else {
            println("Danh sách trống.")
        }
    }

    private fun removeStudent() {
        print("Nhập ID sinh viên cần xóa: ")
        val id = readln()
        val removed = students.removeIf { it.id == id }
        if (removed) println("Đã xóa sinh viên ID: $id") else println("Không tìm thấy sinh viên.")
    }

    private fun advancedMenu() {
        println("\n--- CÁC YÊU CẦU NÂNG CAO ---")
        println("1. Đếm số sinh viên có GPA >= 8.0")
        println("2. Đếm số sinh viên có GPA < 5.0")
        println("3. Tính GPA trung bình của sinh viên ngành được giao")
        println("4. Tìm sinh viên lớn tuổi nhất (Yêu cầu 5)")
        println("5. Tìm sinh viên có GPA nằm trong khoảng 7.0 -> 8.5 (Yêu cầu 6)")
        println("6. Tìm tất cả sinh viên thuộc một ngành (Yêu cầu 7)")
        println("7. Sắp xếp sinh viên theo GPA giảm dần (Yêu cầu 9)")
        println("8. Hiển thị 3 sinh viên có GPA cao nhất (Yêu cầu 10)")
        println("9. Sắp xếp sinh viên theo tuổi (Yêu cầu 11)")
        println("10. Sắp xếp sinh viên theo tên (Yêu cầu 12)")
        print("Chọn chức năng nâng cao (1-10): ")

        when (readlnOrNull()?.trim()) {
            "1" -> println("Số sinh viên GPA >= 8.0: ${students.count { it.gpa >= 8.0 }}")
            "2" -> println("Số sinh viên GPA < 5.0: ${students.count { it.gpa < 5.0 }}")
            "3" -> {
                print("Nhập ngành học: ")
                val major = readln()
                val majorStudents = students.filter { it.major.equals(major, ignoreCase = true) }
                if (majorStudents.isNotEmpty()) {
                    println("GPA trung bình ngành $major: ${majorStudents.map { it.gpa }.average()}")
                } else println("Không có sinh viên ngành này.")
            }
            "4" -> {
                val oldest = students.maxByOrNull { it.age }
                println("Sinh viên lớn tuổi nhất:")
                if (oldest != null) displayAll(listOf(oldest))
            }
            "5" -> {
                val result = students.filter { it.gpa in 7.0..8.5 }
                println("Sinh viên có GPA từ 7.0 - 8.5:")
                displayAll(result)
            }
            "6" -> {
                print("Nhập ngành học: ")
                val major = readln()
                val result = students.filter { it.major.equals(major, ignoreCase = true) }
                displayAll(result)
            }
            "7" -> {
                println("Danh sách sắp xếp theo GPA giảm dần:")
                displayAll(students.sortedByDescending { it.gpa })
            }
            "8" -> {
                println("Top 3 sinh viên có GPA cao nhất:")
                displayAll(students.sortedByDescending { it.gpa }.take(3))
            }
            "9" -> {
                println("Danh sách sắp xếp theo tuổi tăng dần:")
                displayAll(students.sortedBy { it.age })
            }
            "10" -> {
                println("Danh sách sắp xếp theo tên (Alphabet):")
                displayAll(students.sortedBy { it.name })
            }
            else -> println("Lựa chọn không hợp lệ.")
        }
    }
}
fun main() {
    val manager = StudentManager()
    manager.start()
}