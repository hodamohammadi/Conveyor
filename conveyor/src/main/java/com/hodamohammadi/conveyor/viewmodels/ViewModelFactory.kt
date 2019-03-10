package com.hodamohammadi.conveyor.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

/**
 * singleton of view model factory to create instances of view models.
 */
class ViewModelFactory private constructor(): ViewModelProvider.Factory {
    companion object {
        private lateinit var INSTANCE: ViewModelFactory

        val factory: ViewModelFactory
            get() {
                if (INSTANCE == null) {
                    INSTANCE = ViewModelFactory()
                }
                return INSTANCE
            }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            ChatViewModel() as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}