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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("BMI_HISTORY", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val calculateButton = findViewById<Button>(R.id.calculateButton)

        calculateButton.setOnClickListener {
            val weightString = findViewById<EditText>(R.id.weightEditText).text.toString()
            val heightString = findViewById<EditText>(R.id.heightEditText).text.toString()

            runBMICalc(weightString, heightString)
        }

        Utility.setupMenuButton(this@MainActivity, findViewById(R.id.menuButton))
    }

    private fun runBMICalc(weightString: String, heightString: String)
    {

        if (weightString.isNotEmpty() && heightString.isNotEmpty()) {
            val weight = weightString.toDouble()
            val height = heightString.toDouble()

            if (weight > 0 && height > 0) {

                val bmi = calculateBMI(weight, height)

                Utility.changeActivity(this,DescActivity::class.java, "BMI" to bmi)

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


    private fun calculateBMI(weight: Double, height: Double): Double {
        return weight / (height * height)
    }




}