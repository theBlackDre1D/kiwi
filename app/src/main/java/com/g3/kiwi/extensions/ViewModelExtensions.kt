package com.g3.kiwi.extensions

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> ViewModel.flowTask(task: suspend () -> T): Flow<T> {
    return flow {
        val result = task()
        emit(result)
    }
}