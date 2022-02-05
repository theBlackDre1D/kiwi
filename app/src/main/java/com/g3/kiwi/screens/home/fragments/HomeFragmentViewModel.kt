package com.g3.kiwi.screens.home.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.g3.base.either.Either
import com.g3.kiwi.extensions.doInCoroutine
import com.g3.kiwi.models.Suggestion
import com.g3.kiwi.repositories.interfaces.FlightRepository
import com.g3.kiwi.repositories.interfaces.SuggestionRepository
import kotlinx.coroutines.flow.collect


class HomeFragmentViewModel(
//    private val flightRepository: FlightRepository,
    private val suggestionRepository: SuggestionRepository
) : ViewModel() {

    val suggestions = MutableLiveData<Either<Suggestion>>()

    fun getSuggestion() {
        doInCoroutine {
            suggestionRepository.getRandomSuggestion().collect {
                suggestions.postValue(it)
            }
        }
    }
}