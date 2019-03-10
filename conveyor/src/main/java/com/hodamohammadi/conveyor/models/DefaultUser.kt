package com.hodamohammadi.conveyor.models

import com.stfalcon.chatkit.commons.models.IDialog
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.commons.models.IUser
import java.io.Serializable

/**
 * Default model for a user.
 */
data class DefaultUser(
    val userId: String = "",
    val userName: String? = "",
    val userAvatar: String? = "",
    val dialogs: List<DefaultDialog>? = emptyList()
): Serializable, IUser {

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