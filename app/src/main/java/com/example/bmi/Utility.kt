package com.example.bmi


import BMIViewModel
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
import androidx.activity.viewModels

object Utility : AppCompatActivity()
{

    fun setupMenuButton(context: Context, menuButton: Button)
    {
        menuButton.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(context, menuButton)
            popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_history ->
                    changeActivity(context,HistoryActivity::class.java)

                    R.id.action_author ->
                    changeActivity(context,AuthorActivity::class.java)

                    R.id.action_changeUnits ->
                    {
                        changeUnits()
                    }

                    else -> return@OnMenuItemClickListener false
                }
                true
            })
            popupMenu.show()
        }
    }

    fun setupBackButton(context: Context, backButton: Button)
    {
        backButton.setOnClickListener {
           changeActivity(context,MainActivity::class.java)
        }
    }

    fun changeActivity(context: Context,activity: Class<*>, vararg extras: Pair<String, Any>) {
        val intent = Intent(context, activity)

        for (extra in extras) {
            val key = extra.first

            when (val value = extra.second) {
                is Int -> intent.putExtra(key, value)
                is Double -> intent.putExtra(key, value)
                is String -> intent.putExtra(key, value)

                else -> throw IllegalArgumentException("Unsupported data type: ${value.javaClass.simpleName}")
            }
        }

        context.startActivity(intent)
    }

    fun changeUnits()
    {

    }

}