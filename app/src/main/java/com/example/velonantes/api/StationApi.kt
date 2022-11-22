package com.example.velonantes.api

import com.example.velonantes.model.Station
import retrofit2.Response
import retrofit2.http.GET

interface StationApi {

    @GET("api/stations")
   suspend fun getStations(): Response<List<Station>>
}