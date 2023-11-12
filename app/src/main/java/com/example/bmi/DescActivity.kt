package com.example.bmi


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.PopupMenu


class DescActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desc)

        val bmiValue = intent.getDoubleExtra("BMI", 0.0)

        val bmiTextView = findViewById<TextView>(R.id.bmiTextView)

        bmiTextView.text = bmiValue.toString()

        Utility.setupBackButton(this@DescActivity, findViewById(R.id.backButton))

        Utility.setupMenuButton(this@DescActivity, findViewById(R.id.menuButton))
    }
}