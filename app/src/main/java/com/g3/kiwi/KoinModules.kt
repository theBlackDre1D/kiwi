package com.g3.kiwi

import androidx.lifecycle.SavedStateHandle
import com.g3.kiwi.screens.home.HomeFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModules = module {

    // ViewModels
    viewModel { (handle: SavedStateHandle) -> HomeFragmentViewModel(handle) }
}