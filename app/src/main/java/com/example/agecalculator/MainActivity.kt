package com.example.agecalculator

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val datePickerBtn = findViewById<Button>(R.id.btnDatePicker)
        datePickerBtn.setOnClickListener {
            datePickerFunction()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun datePickerFunction() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, dateOfMonth ->
                Toast.makeText(
                    this,
                    "$selectedYear / ${selectedMonth + 1} / $dateOfMonth",
                    Toast.LENGTH_LONG
                ).show()
                val dateText = findViewById<TextView>(R.id.selectDateText)
                dateText.text = "$selectedYear / ${selectedMonth + 1}/ $dateOfMonth"
            },
            year,
            month,
            day
        ).show()
    }


}