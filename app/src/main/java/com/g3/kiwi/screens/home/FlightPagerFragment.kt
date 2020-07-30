package com.g3.kiwi.screens.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import com.g3.kiwi.R
import com.g3.kiwi.base.BaseFragment
import com.g3.kiwi.base.BaseFragmentHandler
import com.g3.kiwi.databinding.FlightPagerFragmentBinding
import com.g3.kiwi.extensions.loadImageFromURL
import com.g3.kiwi.models.Flight
import com.g3.kiwi.views.KeyValueTextView

private const val FLIGHT__BUNDLE_KEY = "FLIGHT__BUNDLE_KEY"

class FlightPagerFragment : BaseFragment<FlightPagerFragmentBinding, FlightPagerFragmentHandler>() {

    private val flight: Flight? by lazy {
        val flight = arguments?.getSerializable(FLIGHT__BUNDLE_KEY) as? Flight
        flight
    }

    companion object {
        fun newInstance(flight: Flight): FlightPagerFragment {
            val fragment = FlightPagerFragment()
            val arguments = Bundle()
            arguments.putSerializable(FLIGHT__BUNDLE_KEY, flight)
            fragment.arguments = arguments
            return fragment
        }
    }

    override fun setBinding(layoutInflater: LayoutInflater): FlightPagerFragmentBinding = FlightPagerFragmentBinding.inflate(layoutInflater)
    override fun onFragmentLoadingFinished(binding: FlightPagerFragmentBinding, context: Context) {
        setupScreen()
    }

    private fun setupScreen() {
        binding.destinationV.configuration = KeyValueTextView.KeyValueTextViewConfiguration(R.string.flights__destination, flight?.cityTo)
        binding.priceV.configuration = KeyValueTextView.KeyValueTextViewConfiguration(R.string.flights__price, flight?.price.toString())
        binding.idTV.text = flight?.id
        binding.flightIV.loadImageFromURL("https://images.kiwi.com/photos/600x330/london_gb.jpg") // just mocked image
    }
}

interface FlightPagerFragmentHandler : BaseFragmentHandler