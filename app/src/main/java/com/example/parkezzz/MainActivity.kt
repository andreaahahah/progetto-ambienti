package com.example.parkezzz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import android.content.Intent
import android.widget.Toast
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.CheckBox




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textUsername = findViewById<TextView>(R.id.texemail)
        val textPassword = findViewById<TextView>(R.id.textPassword)
        val loginButton = findViewById<Button>(R.id.button)
        val registerButton = findViewById<Button>(R.id.registrazione)
        val infoButton = findViewById<Button>(R.id.buttonInfo)
        var showPass: CheckBox? = null

        loginButton.setOnClickListener {
            val email = textUsername.text.toString()
            val passkey = textPassword.text.toString()


            // Creazione di un oggetto UtenteDAO
            val utenteDAO = UtenteDAO(this)

            val credenzialiCorrette = utenteDAO.verificaCredenziali(email, passkey)

            if (credenzialiCorrette) {
                val intent = Intent(this, Homeapp::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Credenziali errate. Riprova!", Toast.LENGTH_SHORT).show()
            }
        }

        registerButton.setOnClickListener {
            val intent = Intent(this, registrazione::class.java)
            startActivity(intent)
        }
        infoButton.setOnClickListener {
            val intent = Intent(this, info::class.java)
            startActivity(intent)
        }
        showPass = findViewById(R.id.checkBox)

        //funzione che gestisce il click all'interno della CheckBox
        showPass.setOnCheckedChangeListener { _, isChecked ->
            //Se la CheckBox è selezionata
            if (isChecked) {
                // mostra password
                textPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                // nascondi password
                textPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            }



        }

    }
}