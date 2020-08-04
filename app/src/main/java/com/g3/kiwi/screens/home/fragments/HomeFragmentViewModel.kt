package com.g3.kiwi.screens.home.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.g3.kiwi.base.Either
import com.g3.kiwi.database.FlightEntity
import com.g3.kiwi.extensions.coroutineTask
import com.g3.kiwi.extensions.flowTask
import com.g3.kiwi.models.Flight
import com.g3.kiwi.models.FlightResponse
import com.g3.kiwi.repositories.interfaces.FlightRepository
import com.g3.kiwi.utils.DateUtils
import kotlinx.coroutines.flow.collect

private const val FLIGHTS__BUNDLE_KEY = "FLIGHTS__BUNDLE_KEY"
private const val FLIGHTS_COUNT_TO_SAVE__BUNDLE_KEY = "FLIGHTS_COUNT_TO_SAVE__BUNDLE_KEY"

class HomeFragmentViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val flightRepository: FlightRepository) : ViewModel() {

    var flightsCountToSave: Int
        get() = savedStateHandle.get(FLIGHTS_COUNT_TO_SAVE__BUNDLE_KEY) ?: FLIGHTS_COUNT
        set(value) = savedStateHandle.set(FLIGHTS_COUNT_TO_SAVE__BUNDLE_KEY, value)

    var flights: MutableLiveData<Either<FlightResponse>>
        get() = savedStateHandle.getLiveData(FLIGHTS__BUNDLE_KEY)
        set(value) = savedStateHandle.set(FLIGHTS__BUNDLE_KEY, value)

    fun getFlights(dateFrom: String, dateTo: String) {
        val flow = flowTask {
            flightRepository.getFlightsFromServer("PRG", "STN", dateFrom, dateTo) // flying destinations are just mocked for purpose of this app
        }

        coroutineTask {
            flow.collect { flightResponse ->
                flights.postValue(flightResponse)
            }
        }
    }

    fun getSavedFlightsLiveData(): LiveData<List<FlightEntity>> = flightRepository.getSavedFlightsLiveData()

    fun saveFlights(flights: List<Flight>) {
        val flightEntities = mutableListOf<FlightEntity>()
        val todayDate = DateUtils.getTodayDate()
        flights.forEach { flight ->
            flightEntities.add(FlightEntity(flight.id, todayDate, flight.cityTo, flight.price))
        }

        coroutineTask { flightRepository.saveFlights(flightEntities) }
    }
}