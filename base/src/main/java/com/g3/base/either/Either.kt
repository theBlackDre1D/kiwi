package com.g3.base.either

sealed class Either<out T> {
    data class Error(val message: String?) : Either<Nothing>()
    data class Success<T>(val value: T) : Either<T>()
}