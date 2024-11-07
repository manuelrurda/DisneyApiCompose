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
import java.io.IOException

class CharactersViewModel: ViewModel() {

    private val _charactersUiState = MutableStateFlow<UiState<List<CharacterModel>>>(UiState.Loading)
    val charactersUiState = _charactersUiState.asStateFlow()

    private val _characterUiState = MutableStateFlow<UiState<CharacterModel>>(UiState.Loading)
    val characterUiState = _characterUiState.asStateFlow()

    init {
        getCharacters()
    }

    fun getCharacters() {
        _charactersUiState.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.retrofit.getCharacters()
                withContext(Dispatchers.Main){
                    if (response.isSuccessful){
                        _charactersUiState.value = UiState.Success(response.body()?.data ?: emptyList())
                    }else{
                        _charactersUiState.value = UiState.Error.ServerError
                    }
                }
            }catch (e: IOException){
                _charactersUiState.value = UiState.Error.NetworkError
            }catch (e: Exception){
                _charactersUiState.value = UiState.Error.UnexpectedError
            }
        }
    }

    fun getCharacterById(id:Int){
        _characterUiState.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.retrofit.getCharacterById(id)
                withContext(Dispatchers.Main){
                    if(response.isSuccessful){
                        _characterUiState.value = UiState.Success(response.body()?.data ?: getEmptyCharacterModel())
                    }else{
                        _characterUiState.value = UiState.Error.ServerError
                    }
                }
            }catch (e: IOException){
                _characterUiState.value = UiState.Error.NetworkError
            }catch (e: Exception){
                _characterUiState.value = UiState.Error.UnexpectedError
            }
        }
    }
}