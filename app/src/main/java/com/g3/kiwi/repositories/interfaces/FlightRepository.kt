package com.g3.kiwi.repositories.interfaces

import androidx.lifecycle.LiveData
import com.g3.kiwi.base.Either
import com.g3.kiwi.database.FlightEntity
import com.g3.kiwi.models.FlightResponse

interface FlightRepository {
    suspend fun getFlightsFromServer(flyFrom: String, flyTo: String, dateFrom: String, dateTo: String): Either<FlightResponse>
    suspend fun saveFlights(flights: List<FlightEntity>): Either<Unit>
    suspend fun getAllSavedFlights(): List<FlightEntity>
    fun getSavedFlightsLiveData(): LiveData<List<FlightEntity>>
}