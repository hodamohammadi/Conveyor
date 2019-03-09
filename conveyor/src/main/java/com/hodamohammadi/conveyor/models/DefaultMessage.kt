package com.hodamohammadi.conveyor.models

import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.commons.models.IUser
import java.io.Serializable
import java.util.Date

/**
 * Default model for a single message.
 */
data class DefaultMessage(
    val messageId: String,
    val messageText: String,
    val messageCreatedAt: Date,
    val messageUser: DefaultUser
): Serializable, IMessage {

    override fun getId(): String {
        return messageId
    }

    override fun getText(): String {
        return messageText
    }

    override fun getCreatedAt(): Date {
        return messageCreatedAt
    }

    override fun getUser(): IUser {
        return messageUser
    }
}