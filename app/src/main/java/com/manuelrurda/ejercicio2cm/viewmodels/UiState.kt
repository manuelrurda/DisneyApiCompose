package com.manuelrurda.ejercicio2cm.viewmodels

sealed class UiState<out T>{
    data object Loading : UiState<Nothing>()
    data class Success<T>(val data: T): UiState<T>()
    sealed class Error: UiState<Nothing>() {
        data object ServerError : Error()
        data object NetworkError : Error()
        data object UnexpectedError : Error()
    }
}