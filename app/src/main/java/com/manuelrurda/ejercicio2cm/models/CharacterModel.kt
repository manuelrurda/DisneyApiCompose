package com.manuelrurda.ejercicio2cm.models

import com.google.gson.annotations.SerializedName
import com.manuelrurda.ejercicio2cm.CharacterList

data class CharactersResponse(
    val data: List<CharacterModel>
)

data class CharacterResponse(
    val data: CharacterModel
)
data class CharacterModel(
    @SerializedName("_id")
    val id:Int,
    val name:String,
    val imageUrl:String?,
    val createdAt:String,
    val films:List<String>,
    val shortFilms:List<String>,
    val tvShows:List<String>
)

fun getEmptyCharacterModel(): CharacterModel {
    return CharacterModel(
        id = 0,
        name = "",
        imageUrl = "",
        createdAt = "",
        films = emptyList(),
        shortFilms = emptyList(),
        tvShows = emptyList()
    )
}
