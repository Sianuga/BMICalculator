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





            if (weightString.isNotEmpty() && heightString.isNotEmpty()) {
                val weight = weightString.toDouble()
                val height = heightString.toDouble()

                if (weight > 0 && height > 0) {

                    val bmi = calculateBMI(weight, height)

                    changeActivity(DescActivity::class.java, "BMI" to bmi)

                } else {

                    Toast.makeText(this, "Weight and height must be greater than zero", Toast.LENGTH_SHORT).show()
                }
            } else {

                Toast.makeText(this, "Please enter both weight and height", Toast.LENGTH_SHORT).show()
            }

        }

        val menuButton = findViewById<Button>(R.id.menuButton)
        menuButton.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(this, menuButton)
            popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_crick ->
                        Toast.makeText(
                            this@MainActivity,
                            "You Clicked : " + item.title,
                            Toast.LENGTH_SHORT
                        ).show()

                    R.id.action_ftbal ->
                        Toast.makeText(
                            this@MainActivity,
                            "You Clicked : " + item.title,
                            Toast.LENGTH_SHORT
                        ).show()

                    R.id.action_hockey ->
                        Toast.makeText(
                            this@MainActivity,
                            "You Clicked : " + item.title,
                            Toast.LENGTH_SHORT
                        ).show()
                }
                true
            })
            popupMenu.show()
        }
    }



    private fun calculateBMI(weight: Double, height: Double): Double {
        return weight / (height * height)
    }

    private fun changeActivity(activity: Class<*>, vararg extras: Pair<String, Any>) {
        val intent = Intent(this, activity)

        // Add extras to the intent
        for (extra in extras) {
            val key = extra.first

            when (val value = extra.second) {
                is Int -> intent.putExtra(key, value)
                is Double -> intent.putExtra(key, value)
                is String -> intent.putExtra(key, value)

                else -> throw IllegalArgumentException("Unsupported data type: ${value.javaClass.simpleName}")
            }
        }

        startActivity(intent)
    }
}