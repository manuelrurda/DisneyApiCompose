package com.manuelrurda.ejercicio2cm.network

import com.manuelrurda.ejercicio2cm.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
    val retrofit: DisneyAPI by lazy {
        Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DisneyAPI::class.java)
    }
}