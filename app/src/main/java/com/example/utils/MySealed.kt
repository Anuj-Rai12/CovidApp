package com.example.utils

sealed class MySealed<T>(
    val data: T? = null,
    val throwable: Throwable? = null
) {
    class Success<T>(data: T) : MySealed<T>(data)
    class Loading<T>(data: T? = null) : MySealed<T>(data)
    class Error<T>(data: T? = null, throwable: Throwable) : MySealed<T>(data, throwable)
}
