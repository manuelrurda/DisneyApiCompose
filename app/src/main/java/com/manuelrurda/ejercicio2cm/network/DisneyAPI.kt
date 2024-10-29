package com.manuelrurda.ejercicio2cm.network

import com.manuelrurda.ejercicio2cm.models.CharacterResponse
import com.manuelrurda.ejercicio2cm.models.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DisneyAPI {
    @GET("character?pageSize=7438")
    suspend fun getCharacters():Response<CharactersResponse>

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int):Response<CharacterResponse>
}