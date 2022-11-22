package com.example.velonantes.model

import android.location.Location
import kotlinx.serialization.*

var currentLocation: Location? = null
var stationSelected:Station? = null
var parkSelected:Park? = null
var allStations:List<Station>? = null

@Serializable
data class Station (
    val id: Long,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val status: String,
    val bikeStands: Long,
    val availableBikes: Long,
    val availableBikeStands: Long,
    val recordId: String
) {
    fun toLocation(): Location {
        val location = Location("")

        location.latitude = latitude

        location.longitude = longitude
        return location
    }

    fun showDetails(): CharSequence? {
        return "⛷️${availableBikes} 🏴‍${availableBikeStands} ✅$bikeStands"
    }
}

    @Serializable
    data class Park (
        val id: Long,
        val grpNom: String,
        val grpDisponible: Int,
        val latitude: Double,
        val longitude: Double) {
        fun toLocation(): Location {
            val location = Location("")

            location.latitude = latitude

            location.longitude = longitude
            return location
        }


    }
