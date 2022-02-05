package com.g3.kiwi.repositories

import com.g3.base.either.Either
import com.g3.kiwi.api.SuggestionRequests
import com.g3.kiwi.models.Suggestion
import com.g3.kiwi.repositories.interfaces.SuggestionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SuggestionRepositoryImpl(
    private val suggestionRequests: SuggestionRequests
) : SuggestionRepository {

    override suspend fun getRandomSuggestion(): Flow<Either<Suggestion>> = flow {
        emit(Either.Loading)
        try {
            val result = suggestionRequests.getSuggestedActivity()
            emit(Either.Success(result))
        } catch (e: Exception) {
            emit(Either.Error(e.localizedMessage))
        }
    }
}