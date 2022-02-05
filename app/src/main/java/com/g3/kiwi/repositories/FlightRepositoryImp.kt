package com.g3.kiwi.repositories

import androidx.lifecycle.LiveData
import com.g3.base.either.Either
import com.g3.base.repository.BaseRepository
import com.g3.kiwi.api.FlightRequests
import com.g3.kiwi.database.FlightDao
import com.g3.kiwi.database.FlightEntity
import com.g3.kiwi.models.FlightResponse
import com.g3.kiwi.repositories.interfaces.FlightRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FlightRepositoryImp(
    private val flightRequests: FlightRequests,
    private val flightDao: FlightDao
) : BaseRepository(), FlightRepository {

    override suspend fun getFlightsFromServer(flyFrom: String, flyTo: String, dateFrom: String, dateTo: String): Flow<Either<FlightResponse>> = flow {
        emit(Either.Loading)
        try {
            val result = flightRequests.getFlights(flyFrom, flyTo, dateFrom, dateTo)
            emit(Either.Success(result))
        } catch (e: Exception) {
            emit(Either.Error(e.localizedMessage))
        }
    }

    override fun getSavedFlightsLiveData(): LiveData<List<FlightEntity>> = flightDao.getAllSavedFlightsLiveData()
    override suspend fun saveFlights(flights: List<FlightEntity>) = runTask { flightDao.saveFlights(flights) }
    override suspend fun getAllSavedFlights(): List<FlightEntity> = flightDao.getAllSavedFlights()

}