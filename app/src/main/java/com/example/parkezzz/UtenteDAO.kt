package com.example.parkezzz
import android.content.Context

class UtenteDAO(context: Context) {

    private val dbHelper = DatabaseHelper(context)

    fun inserisciUtente(nomeUtente: String, email:String, password: String): Long {
        return dbHelper.inserisciUtente(nomeUtente, email, password, )
    }

    fun verificaCredenziali(email: String, password: String): Boolean {
        return dbHelper.verificaCredenziali(email, password)
    }
}
