package com.g3.kiwi.repositories

import com.g3.kiwi.api.FlightRequests
import com.g3.kiwi.base.Either
import com.g3.kiwi.models.FlightResponse
import com.g3.kiwi.repositories.interfaces.FlightRepository

class FlightRepositoryImp(private val flightRequests: FlightRequests) : FlightRepository {

    override suspend fun getFlights(flyFrom: String, flyTo: String, dateFrom: String,dateTo: String): Either<FlightResponse> {
        return try {
            val result = flightRequests.getFlights(flyFrom, flyTo, dateFrom, dateTo)
            Either.Success(result)
        } catch (e: Exception) {
            Either.Error(e.localizedMessage)
        }
    }
}