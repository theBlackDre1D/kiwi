package com.g3.kiwi.screens.home.activity

import android.view.LayoutInflater
import com.g3.kiwi.R
import com.g3.kiwi.base.BaseActivity
import com.g3.kiwi.databinding.MainActivityBinding
import com.g3.kiwi.screens.home.fragments.HomeFragmentHandler

class HomeActivity : BaseActivity<MainActivityBinding, Nothing>(),
    HomeFragmentHandler {

    override fun setNavigationGraph() = R.id.homeNavigationContainer
    override fun setBinding(layoutInflater: LayoutInflater): MainActivityBinding = MainActivityBinding.inflate(layoutInflater)
    override fun onActivityLoadingFinished(binding: MainActivityBinding) {}

}