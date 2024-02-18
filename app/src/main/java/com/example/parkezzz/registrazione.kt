package com.example.parkezzz

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class registrazione : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrazione)

        val textmail = findViewById<TextView>(R.id.texemail)
        val registerButton = findViewById<Button>(R.id.registrazione)

        val textNomeCognome = findViewById<TextView>(R.id.editTextText)
        val textPassword = findViewById<TextView>(R.id.textPassword)
        val indietrobutton = findViewById<ImageButton>(R.id.indietro)
        var showPass: CheckBox? = null
        var good = false


        indietrobutton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
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


        registerButton.setOnClickListener {
            var i = 1
            val mail = textmail.text.toString()
            val passkk=textPassword.text.toString()
            val regex = Regex("(?=.*[A-Za-z])(?=.*\\d).{4,9}")


            val nome = textNomeCognome.text.toString()
            while (!good && i>0) {
                i=0
                if (regex.matches(passkk)) {

                    good = true
                } else {
                    Toast.makeText(this, "password debole", Toast.LENGTH_SHORT).show()

                }
            }
            if(good) {
                // Creazione di un oggetto UtenteDAO
                val utenteDAO = UtenteDAO(this)

                utenteDAO.inserisciUtente(nome, mail, passkk)
                val intent = Intent(this, MainActivity::class.java)
                Toast.makeText(this, "Registrazione completata!", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }

        }
    }
}
