package com.hodamohammadi.chat.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.hodamohammadi.chat.models.DefaultDialog
import com.hodamohammadi.services.gateways.FirebaseGateway
import com.hodamohammadi.services.Resource

/**
 * View model for communication between chat UIs.
 */
class ChatViewModel : ViewModel() {
    val getUserDialogsLiveData: LiveData<Resource<List<DefaultDialog>>>
    val getUserDialogs = MutableLiveData<Void>()
    var threadId: String? = null

    init {
        getUserDialogsLiveData = Transformations.switchMap(getUserDialogs){
            FirebaseGateway.getUserThreads()
        }
    }

}