package com.g3.kiwi.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun ViewModel.doInCoroutine(action: suspend (CoroutineScope) -> Unit) {
    this.viewModelScope.launch(Dispatchers.IO) {
        action(this)
    }
}

fun ViewModel.doInCoroutine(dispatcher: CoroutineDispatcher = Dispatchers.IO, action: suspend (CoroutineScope) -> Unit) {
    this.viewModelScope.launch(Dispatchers.IO) {
        action(this)
    }
}