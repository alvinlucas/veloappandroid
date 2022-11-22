package com.example.velonantes.ui.park

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.velonantes.R
import com.example.velonantes.model.currentLocation
import com.example.velonantes.model.parkSelected

class ParkDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_park_detail)
        val park = intent.getStringExtra("park")
        val parkName = findViewById<TextView>(R.id.parkName)
        val buttonPark = findViewById<Button>(R.id.buttonPark)
        val disponible = findViewById<TextView>(R.id.disponible)
        val distance = findViewById<TextView>(R.id.distance)

        parkSelected?.let { park ->
            parkName.text = park.grpNom
            disponible.text = park.grpDisponible.toString() + " places restantes"

            if (currentLocation != null){
                distance.text = "A seulement ${String.format("%.2f", currentLocation!!.distanceTo(park.toLocation())/1000)} killomètres de vous !"
            }else{
                distance.text = " - KM"
            }
            buttonPark.setOnClickListener {
                // Display a label at the location of Google's Sydney office
                val gmmIntentUri =
                    Uri.parse("geo:0,0?q=${park.latitude},${park.longitude}(${park.grpNom}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)

            }
        }
    }
}