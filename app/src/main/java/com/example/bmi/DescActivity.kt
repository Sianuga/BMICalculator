package com.example.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DescActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desc)

        val bmiValue = intent.getDoubleExtra("BMI", 0.0)

        val bmiTextView = findViewById<TextView>(R.id.bmiTextView)

        bmiTextView.text = bmiValue.toString()
    }
}