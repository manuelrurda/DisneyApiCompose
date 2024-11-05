package com.manuelrurda.ejercicio2cm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuelrurda.ejercicio2cm.models.CharacterModel
import com.manuelrurda.ejercicio2cm.models.getEmptyCharacterModel
import com.manuelrurda.ejercicio2cm.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharactersViewModel: ViewModel() {

    private val _charactersUiState = MutableStateFlow<UiState<List<CharacterModel>>>(UiState.Loading)
    val charactersUiState = _charactersUiState.asStateFlow()

    private val _characterUiState = MutableStateFlow<UiState<CharacterModel>>(UiState.Loading)
    val characterUiState = _characterUiState.asStateFlow()

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.retrofit.getCharacters()
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    _charactersUiState.value = UiState.Success(response.body()?.data ?: emptyList())
                }else{
                    _charactersUiState.value = UiState.Error("")
                }
            }
        }
    }

    fun getCharacterById(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.retrofit.getCharacterById(id)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    _characterUiState.value = UiState.Success(response.body()?.data ?: getEmptyCharacterModel())
                }else{
                    _characterUiState.value = UiState.Error("")
                }
            }
        }
    }
}