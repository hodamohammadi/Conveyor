package com.hodamohammadi.chatapp.models

import com.stfalcon.chatkit.commons.models.IMessage
import java.util.*

/**
 * Default model for a single message.
 */
class DefaultMessage constructor(private val id: String, private val text: String, private val createdAt: Date,
                                 private val user: DefaultUser): IMessage {

    override fun getId(): String {
        return id
    }

    override fun getText(): String? {
        return text
    }

    override fun getCreatedAt(): Date? {
        return createdAt
    }

    override fun getUser(): DefaultUser {
        return this.user
    }
}