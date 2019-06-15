package com.hodamohammadi.chat.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.hodamohammadi.chat.models.DefaultDialog
import com.hodamohammadi.services.Resource
import com.hodamohammadi.services.gateways.FirebaseGateway

/**
 * View model for communication between chat UIs.
 */
class ChatsListViewModel : ViewModel() {
    val getUserDialogsLiveData: LiveData<Resource<List<DefaultDialog>>>
    val getUserDialogs = MutableLiveData<Void>()

    init {
        getUserDialogsLiveData = Transformations.switchMap(getUserDialogs){
            FirebaseGateway.getUserThreads()
        }
    }

}