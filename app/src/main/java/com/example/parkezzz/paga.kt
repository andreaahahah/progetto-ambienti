package com.example.parkezzz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class paga : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paga)

        val indietrobutton = findViewById<ImageButton>(R.id.indietro)

        indietrobutton.setOnClickListener {
            val intent = Intent(this, parcheggia::class.java)
            startActivity(intent)
        }
    }
}

