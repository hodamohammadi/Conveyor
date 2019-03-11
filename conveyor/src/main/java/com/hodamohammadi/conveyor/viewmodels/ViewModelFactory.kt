package com.hodamohammadi.conveyor.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

/**
 * singleton of view model factory to create instances of view models.
 */
object ViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            ChatViewModel() as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}