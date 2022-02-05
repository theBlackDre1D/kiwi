package com.g3.kiwi.api

import com.g3.kiwi.models.Suggestion
import retrofit2.http.GET

interface SuggestionRequests {


    @GET("activity")
    suspend fun getSuggestedActivity(): Suggestion
}