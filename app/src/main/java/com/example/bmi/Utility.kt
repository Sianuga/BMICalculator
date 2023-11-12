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

object Utility : AppCompatActivity()
{


    fun setupMenuButton(context: Context, menuButton: Button)
    {
        menuButton.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(context, menuButton)
            popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_crick ->
                        Toast.makeText(
                            context,
                            "You Clicked : " + item.title,
                            Toast.LENGTH_SHORT
                        ).show()

                    R.id.action_ftbal ->
                        Toast.makeText(
                            context,
                            "You Clicked : " + item.title,
                            Toast.LENGTH_SHORT
                        ).show()

                    R.id.action_hockey ->
                        Toast.makeText(
                            context,
                            "You Clicked : " + item.title,
                            Toast.LENGTH_SHORT
                        ).show()
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
}