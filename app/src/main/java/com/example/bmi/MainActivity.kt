package com.example.bmi

import BMIViewModel
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: BMIViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(BMIViewModel::class.java)


        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        val weightTextEdit = findViewById<EditText>(R.id.weightEditText)
        val heightTextEdit = findViewById<EditText>(R.id.heightEditText)

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

        viewModel.metricUnits.observe(this){metricUnits ->
            weightTextEdit.text.clear()
            heightTextEdit.text.clear()

            if(metricUnits)
            {
                weightTextEdit.hint = getString(R.string.weight)
                heightTextEdit.hint = getString(R.string.height)
            }
            else
            {
                weightTextEdit.hint = getString(R.string.weightImp)
                heightTextEdit.hint = getString(R.string.heightImp)
            }


        }

        calculateButton.setOnClickListener {
            val weightString = weightTextEdit.text.toString()
            val heightString = heightTextEdit.text.toString()

            runBMICalc(weightString, heightString,sharedPreferences)


        }

        Utility.setupMenuButton(this@MainActivity, findViewById(R.id.menuButton), viewModel::changeUnits)
    }

    private fun runBMICalc(weightString: String, heightString: String, sharedPreferences: SharedPreferences){
        if (weightString.isNotEmpty() && heightString.isNotEmpty()) {
            val weight = weightString.toDouble()
            val height = heightString.toDouble()

            if (weight > 0 && height > 0) {
                viewModel.calculateBMI(weight.toFloat(), height.toFloat())
                findViewById<EditText>(R.id.weightEditText).text.clear()
                findViewById<EditText>(R.id.heightEditText).text.clear()
                sharedPreferences.edit().putString("history", viewModel.history.value?.joinToString(",")).apply()
                Utility.changeActivity(this, DescActivity::class.java, "BMI" to viewModel.bmiValue.value.toString())
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
