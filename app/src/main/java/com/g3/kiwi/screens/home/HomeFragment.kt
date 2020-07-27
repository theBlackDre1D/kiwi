package com.g3.kiwi.screens.home

import android.content.Context
import android.view.LayoutInflater
import com.g3.kiwi.base.BaseFragment
import com.g3.kiwi.base.BaseFragmentHandler
import com.g3.kiwi.databinding.HomeFragmentBinding
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class HomeFragment : BaseFragment<HomeFragmentBinding, HomeFragmentHandler>() {

    private val homeFragmentViewModel: HomeFragmentViewModel by stateViewModel()
    override fun setBinding(layoutInflater: LayoutInflater): HomeFragmentBinding = HomeFragmentBinding.inflate(layoutInflater)
    override fun onFragmentLoadingFinished(binding: HomeFragmentBinding, context: Context) {

    }
}

interface HomeFragmentHandler : BaseFragmentHandler