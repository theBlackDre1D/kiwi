package com.g3.kiwi.screens.home.fragments

import android.content.Context
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import com.g3.base.screens.fragment.BaseFragment
import com.g3.base.screens.fragment.BaseFragmentHandler
import com.g3.kiwi.R
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

    override fun onFragmentResumed() {
        getFlights()
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
            savedFlights.handleResult(
                onSuccess = { success -> homeFragmentViewModel.saveFlights(success.value.flights) },
                onError = { showSnackBar(binding.root, R.string.error__loading_flights) }
            )
        })
    }

    private fun handleSavedFlights(savedFlights: List<FlightEntity>) {
        val todayDate = DateUtils.getTodayDate()
        val todayFlights = savedFlights.filter { flight -> flight.showDate == todayDate }
        val flights = todayFlights.map { flightEntity -> Flight(flightEntity.id, flightEntity.cityTo, flightEntity.price) }

        homeFragmentViewModel.flightsCountToSave = FLIGHTS_COUNT - flights.size
        if (flights.size < FLIGHTS_COUNT) {
            getFlights()
        } else {
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