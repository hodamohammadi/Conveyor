package com.hodamohammadi.chat.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.hodamohammadi.chat.models.DefaultMessage
import com.hodamohammadi.services.Resource
import com.hodamohammadi.services.gateways.FirebaseGateway

/**
 * View model for communication between chat UIs.
 */
class SingleChatViewModel : ViewModel() {
    val getThreadHistoryLiveData: LiveData<Resource<List<DefaultMessage>>>
    val getThreadHistory = MutableLiveData<String>()

    init {

        getThreadHistoryLiveData = Transformations.switchMap(getThreadHistory){
            FirebaseGateway.getThreadHistory(it!!)
        }
    }

}