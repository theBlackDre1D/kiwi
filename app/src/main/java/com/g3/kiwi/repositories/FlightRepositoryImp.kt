package com.g3.kiwi.repositories

import com.g3.kiwi.api.FlightRequests
import com.g3.kiwi.base.BaseRepository
import com.g3.kiwi.base.Either
import com.g3.kiwi.models.FlightResponse
import com.g3.kiwi.repositories.interfaces.FlightRepository

class FlightRepositoryImp(private val flightRequests: FlightRequests) : BaseRepository(), FlightRepository {

    override suspend fun getFlights(flyFrom: String, flyTo: String, dateFrom: String,dateTo: String): Either<FlightResponse> {
        return runTask { flightRequests.getFlights(flyFrom, flyTo, dateFrom, dateTo) }
    }
}