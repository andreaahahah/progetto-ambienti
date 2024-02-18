package com.example.parkezzz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class Homeapp : AppCompatActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private val PERMISSION_REQUEST_CODE = 1
    var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homeapp)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val findbutton = findViewById<Button>(R.id.findbutton)
        val paybutton = findViewById<Button>(R.id.paybutton)
        val yourbutton = findViewById<Button>(R.id.yourbutton)

        findbutton.setOnClickListener {
            i=0
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
                // Richiedi il permesso
                ActivityCompat.requestPermissions(
                    this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_REQUEST_CODE)
            } else {
                // Se il permesso è già concesso, ottieni la posizione corrente
                startLocationUpdates()
            }
        }

        paybutton.setOnClickListener {
            val intent = Intent(this, parcheggia::class.java)
            startActivity(intent)
        }
        yourbutton.setOnClickListener {
            val intent = Intent(this, pagamenti::class.java)
            startActivity(intent)
        }
        val logoutbutton = findViewById<Button>(R.id.outlog)

        logoutbutton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)


        }


        fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)

            if (requestCode == PERMISSION_REQUEST_CODE) {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permesso di posizione ottenuto, avvia gli aggiornamenti della posizione
                    startLocationUpdates()
                } else {
                    // Permesso negato, gestisci di conseguenza (mostra un messaggio, disabilita funzionalità, ecc.)
                    Toast.makeText(this, "Permesso di posizione negato", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun startLocationUpdates() {
        val locationRequest = LocationRequest.create().apply {
            interval = 10000 // Intervallo di aggiornamento della posizione in millisecondi (10 secondi)
            fastestInterval = 5000 // Intervallo più veloce di aggiornamento della posizione in millisecondi (5 secondi)
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult?.lastLocation?.let { location ->


                    // Apri Google Maps con la posizione corrente
                    if(i<1) {
                        openGoogleMaps(39.3539, 16.2107)
                        i++
                    }
                    //openGoogleMaps(location.latitude, location.longitude)
                }
            }
        }

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }
    private  fun openGoogleMaps(latitude: Double, longitude: Double) {
        val uri = "geo:$latitude,$longitude"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))

            startActivity(intent)

    }

    //https://www.openstreetmap.org/?mlat=37.4220936&mlon=-122.083922#map=15/37.4221/-122.0839
}
