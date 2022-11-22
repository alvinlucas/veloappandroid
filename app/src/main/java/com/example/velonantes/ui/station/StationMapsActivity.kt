package com.example.velonantes.ui.station

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.velonantes.R
import com.example.velonantes.databinding.ActivityStationMapsBinding
import com.example.velonantes.model.allStations
import com.example.velonantes.model.currentLocation
import com.example.velonantes.model.stationSelected
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class StationMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityStationMapsBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStationMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        stationSelected?.let { station ->


            // Add a marker in Sydney and move the camera
            allStations?.let {
                for (stationSt in it) {
                    val stationLatLng = LatLng(stationSt.latitude, stationSt.longitude)
                    if(stationSt.id == stationSelected!!.id) {
                        mMap.addMarker(
                            MarkerOptions().position(stationLatLng).title(stationSt.name).icon(BitmapFromVector(this, R.drawable.ic_baseline_directions_bike_24))
                        )
                    }else{
                        mMap.addMarker(
                            MarkerOptions().position(stationLatLng).title(stationSt.name))

                    }
                }
            }
            if(currentLocation != null){
                val location = LatLng(currentLocation!!.latitude, currentLocation!!.longitude)
                mMap.addMarker(
                        MarkerOptions().position(location).title("My position").icon(BitmapFromVector(this, R.drawable.ic_baseline_my_location_24)))
            }
            val current = LatLng(station.latitude, station.longitude)
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(current,18f))

        }
    }
        private fun BitmapFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
            // below line is use to generate a drawable.
            val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)

            // below line is use to set bounds to our vector drawable.
            vectorDrawable!!.setBounds(
                0,
                0,
                vectorDrawable.intrinsicWidth,
                vectorDrawable.intrinsicHeight
            )

            // below line is use to create a bitmap for our
            // drawable which we have added.
            val bitmap = Bitmap.createBitmap(
                vectorDrawable.intrinsicWidth,
                vectorDrawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )

            // below line is use to add bitmap in our canvas.
            val canvas = Canvas(bitmap)

            // below line is use to draw our
            // vector drawable in canvas.
            vectorDrawable.draw(canvas)

            // after generating our bitmap we are returning our bitmap.
            return BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }
