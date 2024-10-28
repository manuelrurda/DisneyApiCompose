package com.manuelrurda.ejercicio2cm.models

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    val data: List<CharacterModel>
)
data class CharacterModel(
    @SerializedName("_id")
    val id:Int,
    val name:String,
    val imageUrl:String?,
    val createdAt:String
)
