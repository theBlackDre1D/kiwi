package com.g3.kiwi.repositories.interfaces

import com.g3.kiwi.base.Either
import com.g3.kiwi.models.FlightResponse

interface FlightRepository {
    suspend fun getFlights(flyFrom: String, flyTo: String, dateFrom: String,dateTo: String): Either<FlightResponse>
}