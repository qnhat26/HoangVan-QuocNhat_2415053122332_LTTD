package com.example.studentprofilecard

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentprofilecard.databinding.ActivityMainBinding
import com.example.studentprofilecard.model.Student
import com.example.studentprofilecard.utils.gone
import com.example.studentprofilecard.utils.showConfirmDialog
import com.example.studentprofilecard.utils.toAcademicRanking
import com.example.studentprofilecard.utils.toast

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentStudent = Student(
        id = "2415053122332",
        name = "Hoang Van Quoc Nhat",
        className = "24T3",
        email = "2415053122332@ute.udn.vn",
        phone = "0901234567",
        gpa = 3.8
    )
    private fun bindStudentData(student: Student) {
        with(binding) {
            tvName.text = student.name
            tvStudentId.text = "MSSV: ${student.id} Lớp: ${student.className}"
            tvPhone.text = "SĐT: ${student.phone}"
            tvGpaBadge.text = "${student.gpa} GPA (${student.gpa.toAcademicRanking()})"
            edtNewGpa.setText(student.gpa.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindStudentData(currentStudent)

        binding.btnUpdateGpa.setOnClickListener {
            val inputStr = binding.edtNewGpa.text.toString().trim()
            val newGpa = inputStr.toDoubleOrNull()

            if (newGpa == null || newGpa !in 0.0..4.0) {
                binding.edtNewGpa.error = "Vui lòng nhập GPA hợp lệ (0.0 - 4.0)"
                toast("Điểm GPA không hợp lệ!")
                return@setOnClickListener
            }

            currentStudent = currentStudent.copy(gpa = newGpa)
            bindStudentData(currentStudent)
            toast("Cập nhật điểm thành công!")
        }

        binding.btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${currentStudent.phone}")
            }
            startActivity(intent)
        }

        binding.btnDelete.setOnClickListener {
            showConfirmDialog(
                title = "Xác nhận xóa",
                message = "Bạn có chắc chắn muốn xóa hồ sơ của ${currentStudent.name}?"
            ) {
                binding.cardProfile.gone()
                toast("Đã xóa hồ sơ thành công!")
            }
        }
    }
}