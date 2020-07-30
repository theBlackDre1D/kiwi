package com.g3.kiwi.screens.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.g3.kiwi.base.Either
import com.g3.kiwi.extensions.flowTask
import com.g3.kiwi.models.FlightResponse
import com.g3.kiwi.repositories.interfaces.FlightRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val FLIGHTS__BUNDLE_KEY = "FLIGHTS__BUNDLE_KEY"

class HomeFragmentViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val flightRepository: FlightRepository) : ViewModel() {

    var flights: MutableLiveData<Either<FlightResponse>>
        get() = savedStateHandle.getLiveData(FLIGHTS__BUNDLE_KEY)
        set(value) = savedStateHandle.set(FLIGHTS__BUNDLE_KEY, value)

    fun getFlights(dateFrom: String, dateTo: String) {
        val flow = flowTask {
            flightRepository.getFlights("PRG", "STN", dateFrom, dateTo)
        }

        viewModelScope.launch {
            flow.collect { flightResponse ->
                flights.postValue(flightResponse)
            }
        }
    }
}