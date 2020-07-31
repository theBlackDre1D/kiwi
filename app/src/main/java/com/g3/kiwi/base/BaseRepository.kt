package com.g3.kiwi.base

abstract class BaseRepository {
    protected suspend fun <T> runTask(task: suspend () -> T): Either<T> {
        return try {
            val result = task()
            return Either.Success(result)
        } catch (e: Exception) {
            Either.Error(e.localizedMessage)
        }
    }
}