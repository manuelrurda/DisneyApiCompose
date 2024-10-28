package com.manuelrurda.ejercicio2cm.network

import com.manuelrurda.ejercicio2cm.models.CharacterModel
import com.manuelrurda.ejercicio2cm.models.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET

interface DisneyAPI {
    @GET("character?pageSize=7438")
    suspend fun getCharacters(): Response<CharacterResponse>
}