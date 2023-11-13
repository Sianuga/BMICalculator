package com.example.bmi

import BMIViewModel
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bmi.DescActivity
import com.example.bmi.R
import com.example.bmi.Utility

class DescActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desc)

        var bmi = intent.getStringExtra("BMI")?.toFloat()


        val bmiTextView = findViewById<TextView>(R.id.bmiTextView)
        val descriptionTextView = findViewById<TextView>(R.id.longDescriptionTextView)
        val nameTextView = findViewById<TextView>(R.id.shortDescriptionTextView)

        updateDescriptionAndName(bmi!!,bmiTextView,descriptionTextView,nameTextView)

    }

    private fun updateDescriptionAndName(bmi: Float,bmiText :TextView , descriptionTextView: TextView, nameTextView: TextView)
    {

        val description = when {
            bmi < 18.5 -> getString(R.string.underweight_description)
            bmi < 25 -> getString(R.string.healthy_description)
            bmi < 30 -> getString(R.string.overweight_description)
            bmi < 40 -> getString(R.string.obese_description)
            else -> getString(R.string.severe_obesity_description)
        }

        val name = when {
            bmi < 18.5 -> getString(R.string.underweight_name)
            bmi < 25 -> getString(R.string.healthy_name)
            bmi < 30 -> getString(R.string.overweight_name)
            bmi < 40 -> getString(R.string.obese_name)
            else -> getString(R.string.severe_obesity_name)
        }

        bmiText.text = bmi.toString()
        bmiText.setTextColor(getColor(Utility.chooseColor(bmi)))
        descriptionTextView.text = description
        nameTextView.text = name
    }
}