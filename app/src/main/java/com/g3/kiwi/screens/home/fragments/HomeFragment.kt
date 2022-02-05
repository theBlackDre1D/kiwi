package com.g3.kiwi.screens.home.fragments

import android.content.Context
import android.view.LayoutInflater
import androidx.core.view.isVisible
import com.g3.base.either.Either
import com.g3.base.screens.fragment.BaseFragment
import com.g3.base.screens.fragment.BaseFragmentHandler
import com.g3.kiwi.R
import com.g3.kiwi.databinding.HomeFragmentBinding
import org.koin.androidx.viewmodel.ext.android.stateViewModel


class HomeFragment : BaseFragment<HomeFragmentBinding, HomeFragmentHandler>() {

    private val homeFragmentViewModel: HomeFragmentViewModel by stateViewModel()
    override fun setBinding(layoutInflater: LayoutInflater): HomeFragmentBinding = HomeFragmentBinding.inflate(layoutInflater)
    override fun onFragmentLoadingFinished(binding: HomeFragmentBinding, context: Context) {
        setupObservers()
        homeFragmentViewModel.getSuggestion()
    }

    private fun setupObservers() {
        homeFragmentViewModel.suggestions.observe(this) { suggestion ->
            when (suggestion) {
                is Either.Loading -> binding.loadingPB.isVisible = true
                is Either.Success -> {
                    binding.loadingPB.isVisible = false
                    binding.suggestionTV.text = suggestion.value.activity
                }
                is Either.Error -> {
                    binding.loadingPB.isVisible = false
                    showSnackBar(binding.root, suggestion.message)
                }
                else -> {}
            }
        }
    }

}

interface HomeFragmentHandler : BaseFragmentHandler