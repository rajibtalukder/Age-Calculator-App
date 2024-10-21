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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    var tvSelectedDate: TextView? = null
    var tvAgeInMinutes: TextView? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val datePickerBtn = findViewById<Button>(R.id.btnDatePicker)
        tvSelectedDate = findViewById<TextView>(R.id.tvSelectDate)
        tvAgeInMinutes = findViewById<TextView>(R.id.tvAgeInMinutes)

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
        var dpd = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDateOfMonth ->
                val selectedDate = "$selectedYear/${selectedMonth+1}/$selectedDateOfMonth"
                tvSelectedDate?.text = selectedDate

                Toast.makeText(this, "$selectedDate", Toast.LENGTH_LONG).show()

//                val dateText = findViewById<TextView>(R.id.selectDateText)
//                dateText.text = "$selectedYear / ${selectedMonth + 1}/ $dateOfMonth"

                var sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                var theDate = sdf.parse(selectedDate)
                var selectedDateInMinutes = theDate.time/60000
                var currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDate.time/60000

                val differentMinutes = currentDateInMinutes -selectedDateInMinutes
                tvAgeInMinutes?.text= differentMinutes.toString()
            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() -86400000
        dpd.show()
    }


}