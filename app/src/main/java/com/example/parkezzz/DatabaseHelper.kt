package com.example.parkezzz
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE Utenti (id INTEGER PRIMARY KEY AUTOINCREMENT, nome_utente TEXT,email TEXT, password TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, versioneVecchia: Int, nuovaVersione: Int) {
        // Aggiorna il database se necessario
    }

    fun inserisciUtente(nomeUtente: String, email:String, password: String): Long {
        val values = ContentValues().apply {
            put("nome_utente", nomeUtente)
            put("email", email)
            put("password", password)

        }

        return writableDatabase.insert("Utenti", null, values)
    }

    fun verificaCredenziali(email: String, password: String): Boolean {
        val proiezione = arrayOf("id")
        val selectione = "email = ? AND password = ?"
        val selectionArgs = arrayOf(email, password)

        val cursore: Cursor = readableDatabase.query("Utenti", proiezione, selectione, selectionArgs, null, null, null)

        val credenzialiCorrette = cursore.count > 0

        cursore.close()

        return credenzialiCorrette
    }

    companion object {
        private const val DATABASE_NAME = "Utenti.db"
        private const val DATABASE_VERSION = 1
    }
}
