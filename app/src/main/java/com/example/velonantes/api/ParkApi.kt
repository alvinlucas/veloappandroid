package com.example.velonantes.api

import com.example.velonantes.model.Park
import com.example.velonantes.model.Station
import retrofit2.Response
import retrofit2.http.GET

interface ParkApi {

    @GET("api/parks")
   suspend fun getPark(): Response<List<Park>>
}