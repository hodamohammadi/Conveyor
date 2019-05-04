package com.hodamohammadi.chat.models

import com.stfalcon.chatkit.commons.models.IUser
import java.io.Serializable

/**
 * Default model for a user.
 */
data class DefaultUser(
    @JvmField val id: String = "",
    @JvmField val name: String? = "",
    @JvmField val avatar: String? = "",
    @JvmField val dialogs: List<DefaultDialog>? = null) : Serializable, IUser {

    override fun getId(): String {
        return id
    }

    override fun getName(): String? {
        return name
    }

    override fun getAvatar(): String? {
        return avatar
    }
}