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
import com.g3.kiwi.databinding.HomeFragmentBinding
import com.g3.kiwi.models.FlightResponse
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import java.text.SimpleDateFormat
import java.util.*

private const val FLIGHTS_COUNT = 5
private const val DATE_FORMAT = "dd/MM/yyyy"

class HomeFragment : BaseFragment<HomeFragmentBinding, HomeFragmentHandler>() {

    private val homeFragmentViewModel: HomeFragmentViewModel by stateViewModel()
    override fun setBinding(layoutInflater: LayoutInflater): HomeFragmentBinding = HomeFragmentBinding.inflate(layoutInflater)
    override fun onFragmentLoadingFinished(binding: HomeFragmentBinding, context: Context) {
        setupFlightsObserver()
        getFlights()
    }

    private fun getFlights() {
        if (homeFragmentViewModel.flights.value == null) {
            val calendar = Calendar.getInstance()
            val dateFormatter = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
            val todayDate = dateFormatter.format(Date(calendar.timeInMillis))

            homeFragmentViewModel.getFlights(todayDate, todayDate)
        }
    }

    private fun setupFlightsObserver() {
        homeFragmentViewModel.flights.observe(this, Observer { response ->
            binding.loadingPB.isVisible = false
            binding.indicatorsV.isVisible = true
            when (response) {
                is Either.Error -> showSnackBar(binding.root, R.string.error__loading_flights)
                is Either.Success -> setupViewPager(response.value)
            }
        })
    }

    private fun setupViewPager(response: FlightResponse) {
        val fragments = mutableListOf<FlightPagerFragment>()
        val flights = response.flights.subList(0, FLIGHTS_COUNT)
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