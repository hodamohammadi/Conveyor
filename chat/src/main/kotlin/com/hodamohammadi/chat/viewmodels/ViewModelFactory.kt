package com.hodamohammadi.chat.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

/**
 * singleton of view model factory to create instances of view models.
 */
object ViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SharedChatViewModel::class.java)) {
            SharedChatViewModel() as T
        } else if (modelClass.isAssignableFrom(SingleChatViewModel::class.java)) {
            SingleChatViewModel() as T
        } else if (modelClass.isAssignableFrom(ChatsListViewModel::class.java)) {
            ChatsListViewModel() as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}