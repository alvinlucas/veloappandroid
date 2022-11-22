package com.example.velonantes.ui.station

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.velonantes.R
import com.example.velonantes.model.currentLocation
import com.example.velonantes.model.stationSelected

class StationDetailActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_station_detail)
        val station = intent.getStringExtra("station")
        val stationName = findViewById<TextView>(R.id.stationName)
        val buttonOpen = findViewById<Button>(R.id.buttonOpen)
        val distance = findViewById<TextView>(R.id.distance)


        stationSelected?.let { station ->
            stationName.text = station.name

            if (currentLocation != null){
                distance.text = "A seulement ${String.format("%.2f", currentLocation!!.distanceTo(station.toLocation())/1000)} killomètres de vous !"
            }else{
                distance.text = " - KM"
            }


            buttonOpen.setOnClickListener {
                // Display a label at the location of Google's Sydney office
                val gmmIntentUri =
                    Uri.parse("geo:0,0?q=${station.latitude},${station.longitude}(${station.name}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)

            }
        }

    }
}