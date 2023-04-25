package com.ketansa.themoviedb.api

sealed class Response<out T> {
    data class Error(val errorCode: Int, val message: String) : Response<Nothing>()
    data class Success<T>(val data: T) : Response<T>()
    object Loading : Response<Nothing>()
}
