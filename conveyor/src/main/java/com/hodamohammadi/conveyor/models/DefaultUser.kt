package com.hodamohammadi.conveyor.models

import android.os.Parcelable
import com.stfalcon.chatkit.commons.models.IUser
import kotlinx.android.parcel.Parcelize

/**
 * Default model for a user.
 */
@Parcelize
data class DefaultUser(private val userId: String,
                       private val userName: String?,
                       private val userAvatar: String?) : Parcelable, IUser {

    override fun getId(): String {
        return userId
    }

    override fun getName(): String? {
        return userName
    }

    override fun getAvatar(): String? {
        return userAvatar
    }
}