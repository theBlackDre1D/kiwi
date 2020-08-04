package com.g3.kiwi.screens.home.fragments

import android.content.Context
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import com.g3.kiwi.R
import com.g3.kiwi.base.BaseFragment
import com.g3.kiwi.base.BaseFragmentHandler
import com.g3.kiwi.base.Either
import com.g3.kiwi.database.FlightEntity
import com.g3.kiwi.databinding.HomeFragmentBinding
import com.g3.kiwi.models.Flight
import com.g3.kiwi.utils.DateUtils
import org.koin.androidx.viewmodel.ext.android.stateViewModel

const val FLIGHTS_COUNT = 5

class HomeFragment : BaseFragment<HomeFragmentBinding, HomeFragmentHandler>() {

    private val homeFragmentViewModel: HomeFragmentViewModel by stateViewModel()
    override fun setBinding(layoutInflater: LayoutInflater): HomeFragmentBinding = HomeFragmentBinding.inflate(layoutInflater)
    override fun onFragmentLoadingFinished(binding: HomeFragmentBinding, context: Context) {
        setupFlightsObservers()
    }

    private fun getFlights() {
        val todayDate = DateUtils.getTodayDate()
        val tomorrowDate = DateUtils.getDateDaysFromNow(1)

        homeFragmentViewModel.getFlights(todayDate, tomorrowDate)
    }

    private fun setupFlightsObservers() {
        homeFragmentViewModel.getSavedFlightsLiveData().observe(this, Observer { savedFlights ->
            handleSavedFlights(savedFlights)
        })

        homeFragmentViewModel.flights.observe(this, Observer { savedFlights ->
            when (savedFlights) {
                is Either.Error -> showSnackBar(binding.root, R.string.error__loading_flights)
                is Either.Success -> handleFlightsFromServer(savedFlights.value.flights)
            }
        })
    }

    private fun handleFlightsFromServer(newFlights: List<Flight>) {
        val flightsToShow = newFlights.subList(0, homeFragmentViewModel.flightsCountToSave)
        homeFragmentViewModel.saveFlights(flightsToShow)
    }

    private fun handleSavedFlights(savedFlights: List<FlightEntity>) {
        val todayDate = DateUtils.getTodayDate()
        val todayFlights = savedFlights.filter { flight -> flight.showDate == todayDate }
        val flights = mutableListOf<Flight>()
        todayFlights.forEach { flightEntity ->
            flights.add(Flight(flightEntity.id, flightEntity.cityTo, flightEntity.price))
        }

        homeFragmentViewModel.flightsCountToSave = FLIGHTS_COUNT - flights.size
        if (flights.size < 5) {
            getFlights()
        }
        else {
            binding.loadingPB.isVisible = false
            binding.indicatorsV.isVisible = true
            setupViewPager(flights.subList(0, FLIGHTS_COUNT))
        }
    }

    private fun setupViewPager(flights: List<Flight>) {
        val fragments = mutableListOf<FlightPagerFragment>()
        flights.forEach { flight ->
            fragments.add(FlightPagerFragment.newInstance(flight))
        }

        val adapter: FragmentStatePagerAdapter by lazy {
            object : FragmentStatePagerAdapter(childFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
                override fun getItem(position: Int) = fragments[position]
                override fun getCount() = FLIGHTS_COUNT
            }
        }

        binding.homeVP.adapter = adapter
        binding.indicatorsV.setViewPager(binding.homeVP)
    }
}

interface HomeFragmentHandler : BaseFragmentHandler