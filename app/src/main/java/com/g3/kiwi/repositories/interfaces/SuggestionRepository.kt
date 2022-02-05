package com.g3.kiwi.repositories.interfaces

import com.g3.base.either.Either
import com.g3.kiwi.models.Suggestion
import kotlinx.coroutines.flow.Flow

interface SuggestionRepository {
    suspend fun getRandomSuggestion(): Flow<Either<Suggestion>>
}