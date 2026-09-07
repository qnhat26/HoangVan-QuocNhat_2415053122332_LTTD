fun main() {

    print("Math score = ")
    val mathScore = readln().toDouble()

    print("Programming score = ")
    val programmingScore = readln().toDouble()

    print("Database score = ")
    val databaseScore = readln().toDouble()

    val total =mathScore + programmingScore + databaseScore
    val average = total / 3
    val highest = maxOf(mathScore, programmingScore, databaseScore)

    val result = if (average >= 5.0) {
        "Đạt"
    } else {
        "Không đạt"
    }

    println()
    println("Hoàng Văn Quốc Nhật\n" +
            "241503122332")
    println("===== KẾT QUẢ =====")
    println("Tổng điểm: $total")
    println("Điểm trung bình: $average")
    println("Điểm cao nhất: $highest")
    println("Kết quả: $result")
}