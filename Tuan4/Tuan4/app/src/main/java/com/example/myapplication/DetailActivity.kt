package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val studentId = intent.getStringExtra("studentId")
        val major = intent.getStringExtra("major")
        val gpa = intent.getDoubleExtra("gpa", 0.0)

        binding.tvName.text = "Họ tên: $name"
        binding.tvStudentId.text = "MSSV: $studentId"
        binding.tvMajor.text = "Ngành: $major"
        binding.tvGpa.text = "GPA: $gpa"
    }
}