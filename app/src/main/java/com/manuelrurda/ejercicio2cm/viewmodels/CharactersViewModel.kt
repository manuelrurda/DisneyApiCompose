package com.manuelrurda.ejercicio2cm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuelrurda.ejercicio2cm.models.CharacterModel
import com.manuelrurda.ejercicio2cm.models.getEmptyCharacterModel
import com.manuelrurda.ejercicio2cm.network.RetrofitClient
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharactersViewModel: ViewModel() {

    private val _characters = MutableStateFlow<List<CharacterModel>>(emptyList())
    val characters = _characters.asStateFlow()

    private val _character = MutableStateFlow<CharacterModel>(getEmptyCharacterModel())
    val character = _character.asStateFlow()

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.retrofit.getCharacters()
            withContext(Dispatchers.Main){
                _characters.value = response.body()?.data ?: emptyList()
            }
        }
    }

    fun getCharacterById(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.retrofit.getCharacterById(id)
            withContext(Dispatchers.Main){
                _character.value = response.body()?.data ?: getEmptyCharacterModel()
            }
        }
    }

    fun clearCharacter() {
        _character.value = getEmptyCharacterModel()
    }

}