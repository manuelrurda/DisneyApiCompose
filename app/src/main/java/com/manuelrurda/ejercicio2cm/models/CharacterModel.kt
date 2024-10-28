package com.manuelrurda.ejercicio2cm.models

data class CharacterResponse(
    val data: List<CharacterModel>
)
data class CharacterModel(
    val name:String,
    val imageUrl:String?,
    val createdAt:String
)
