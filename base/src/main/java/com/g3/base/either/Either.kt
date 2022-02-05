package com.g3.base.either

sealed class Either<out T> {

    var isLoading = false

    data class Error(val message: String?) : Either<Nothing>() {
        constructor(exception: Exception) : this(exception.localizedMessage)
    }
    data class Success<T>(val value: T) : Either<T>()
    object Initial : Either<Nothing>()
    object Loading : Either<Nothing>()

    fun getValueOrNull(): T? {
        return when (this) {
            is Error, Initial, Loading -> null
            is Success -> this.value
        }
    }
}