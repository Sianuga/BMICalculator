package com.example.bmi

import BMIViewModel
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.bmi.R

class HistoryActivity : AppCompatActivity() {

    private lateinit var historyListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        historyListView = findViewById(R.id.listView)

        val historyList = loadHistoryFromSharedPreferences()
        updateHistoryUI(historyList)
    }

    private fun loadHistoryFromSharedPreferences(): List<String> {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("BMI_HISTORY", Context.MODE_PRIVATE)
        val historyString = sharedPreferences.getString("history", "") ?: ""
        return historyString.split(",")
    }

    private fun updateHistoryUI(historyList: List<String>) {
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, historyList)
        historyListView.adapter = adapter
    }
}
