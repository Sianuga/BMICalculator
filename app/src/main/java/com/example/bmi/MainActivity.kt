package com.example.bmi

import BMIViewModel
import android.content.Context
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

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: BMIViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(BMIViewModel::class.java)


        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        var sharedPreferences = getSharedPreferences("BMI_HISTORY", Context.MODE_PRIVATE)
        var historyString = sharedPreferences.getString("history", "") ?: ""
        var historyList = historyString.split(",")
        viewModel.updateHistory(historyList)


        resultTextView.setTextColor(getColor(R.color.underweight))

        viewModel.bmiValue.observe(this, Observer { bmi ->
            resultTextView.text = bmi.toString()
        })

        viewModel.bmiColor.observe(this) { color ->
            resultTextView.setTextColor(getColor(color))
        }

        calculateButton.setOnClickListener {
            val weightString = findViewById<EditText>(R.id.weightEditText).text.toString()
            val heightString = findViewById<EditText>(R.id.heightEditText).text.toString()

            runBMICalc(weightString, heightString)
            sharedPreferences.edit().putString("history", viewModel.history.value?.joinToString(",")).apply()
        }

        Utility.setupMenuButton(this@MainActivity, findViewById(R.id.menuButton))
    }

    private fun runBMICalc(weightString: String, heightString: String) {
        if (weightString.isNotEmpty() && heightString.isNotEmpty()) {
            val weight = weightString.toDouble()
            val height = heightString.toDouble()

            if (weight > 0 && height > 0) {
                viewModel.calculateBMI(weight.toFloat(), height.toFloat())
            } else {
                Toast.makeText(
                    this,
                    "Weight and height must be greater than zero",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(this, "Please enter both weight and height", Toast.LENGTH_SHORT).show()
        }
    }
}
