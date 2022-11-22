package com.example.velonantes.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {
    private val baseUrl = "https://veloappnantes.herokuapp.com/"
    fun getInstance():Retrofit{
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
    }
}