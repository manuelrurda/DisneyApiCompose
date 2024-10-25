package com.manuelrurda.ejercicio2cm.network

import com.manuelrurda.ejercicio2cm.models.CharacterModel
import retrofit2.Response
import retrofit2.http.GET

interface DisneyAPI {
    @GET("character")
    suspend fun getCharacters(): Response<CharacterModel>
}