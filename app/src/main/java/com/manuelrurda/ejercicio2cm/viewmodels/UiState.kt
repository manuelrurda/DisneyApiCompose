package com.manuelrurda.ejercicio2cm.viewmodels

sealed class UiState<out T>{
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T): UiState<T>()
    sealed class Error: UiState<Nothing>() {
        object ServerError : Error()
        object NetworkError : Error()
        object UnexpectedError : Error()
    }
}