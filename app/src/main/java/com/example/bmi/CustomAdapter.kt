package com.example.bmi

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CustomAdapter(context: Context, private val historyList: List<String>) :
    ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, historyList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)


        val textView = view.findViewById<TextView>(android.R.id.text1)
        val textColor = when {
            historyList[position].contains("BMI 1") -> context.getColor(R.color.underweight)
            historyList[position].contains("BMI 2") -> context.getColor(R.color.healthy)
            historyList[position].contains("BMI 25") -> context.getColor(R.color.overweight)
            historyList[position].contains("BMI 26") -> context.getColor(R.color.overweight)
            historyList[position].contains("BMI 27") -> context.getColor(R.color.overweight)
            historyList[position].contains("BMI 28") -> context.getColor(R.color.overweight)
            historyList[position].contains("BMI 29") -> context.getColor(R.color.overweight)
            historyList[position].contains("BMI 3") -> context.getColor(R.color.obese)
            historyList[position].contains("BMI 4") -> context.getColor(R.color.severe_obesity)
            else -> context.getColor(R.color.black)
        }
        textView.setTextColor(textColor)

        return view
    }
}