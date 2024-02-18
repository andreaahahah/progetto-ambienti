package com.example.parkezzz

import androidx.appcompat.app.AppCompatActivity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.TimePicker

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class parcheggia : AppCompatActivity() {




    private lateinit var cittaSpinner: Spinner
    private lateinit var dataOraTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parcheggia)
        val indietrobutton = findViewById<ImageButton>(R.id.indietro)
        cittaSpinner = findViewById(R.id.cittaSpinner)


        // Imposta l'adapter per lo spinner
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.citta_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        cittaSpinner.adapter = adapter
        indietrobutton.setOnClickListener {
            val intent = Intent(this, Homeapp::class.java)
            startActivity(intent)
        }



        // Aggiungi un listener per lo spinner
        cittaSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long

            ) {
                val selectedCity = parent?.getItemAtPosition(position).toString()


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        val pagabutton = findViewById<Button>(R.id.buttonpaga)

            pagabutton.setOnClickListener {


                    val intent = Intent(this, paga::class.java)
                    startActivity(intent)
            }

        }

    }




