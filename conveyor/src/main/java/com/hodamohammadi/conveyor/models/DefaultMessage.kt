package com.hodamohammadi.conveyor.models

import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.commons.models.IUser
import java.io.Serializable
import java.util.Date

/**
 * Default model for a single message.
 */
data class DefaultMessage(
    @JvmField val id: String = "",
    @JvmField val text: String = "",
    @JvmField val createdAt: Date = Date(),
    @JvmField val user: DefaultUser = DefaultUser()) : Serializable, IMessage {

    override fun getId(): String {
        return id
    }

    override fun getText(): String {
        return text
    }

    override fun getCreatedAt(): Date {
        return createdAt
    }

    override fun getUser(): IUser {
        return user
    }
}