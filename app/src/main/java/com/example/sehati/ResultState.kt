package com.example.sehati

sealed class ResultState<T> {
    class Loading<T> : ResultState<T>()
    data class Success<T>(val value: T) : ResultState<T>()
    data class Error<T>(val message: String? = null) : ResultState<T>()
}