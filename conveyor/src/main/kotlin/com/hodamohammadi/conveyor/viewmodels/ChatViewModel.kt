package com.hodamohammadi.conveyor.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.hodamohammadi.conveyor.models.DefaultDialog
import com.hodamohammadi.conveyor.utils.FirebaseHelper
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
            FirebaseHelper.getUserThreads()
        }
    }

}