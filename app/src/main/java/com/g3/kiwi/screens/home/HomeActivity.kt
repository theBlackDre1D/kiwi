package com.g3.kiwi.screens.home

import android.view.LayoutInflater
import com.g3.kiwi.R
import com.g3.kiwi.base.BaseActivity
import com.g3.kiwi.databinding.MainActivityBinding

class HomeActivity : BaseActivity<MainActivityBinding, Nothing>(), HomeFragmentHandler {

    override fun setNavigationGraph() = R.id.homeNavigationContainer
    override fun setBinding(layoutInflater: LayoutInflater): MainActivityBinding = MainActivityBinding.inflate(layoutInflater)
    override fun onActivityLoadingFinished(binding: MainActivityBinding) {}

}