package com.g3.kiwi.repositories.interfaces

import androidx.lifecycle.LiveData
import com.g3.base.either.Either
import com.g3.kiwi.database.FlightEntity
import com.g3.kiwi.models.FlightResponse
import com.g3.kiwi.models.Suggestion
import kotlinx.coroutines.flow.Flow

interface FlightRepository {
    suspend fun getFlightsFromServer(flyFrom: String, flyTo: String, dateFrom: String, dateTo: String): Flow<Either<FlightResponse>>
    suspend fun saveFlights(flights: List<FlightEntity>): Either<Unit>
    suspend fun getAllSavedFlights(): List<FlightEntity>
    fun getSavedFlightsLiveData(): LiveData<List<FlightEntity>>
}