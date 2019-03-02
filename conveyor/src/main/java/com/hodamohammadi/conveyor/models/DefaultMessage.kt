package com.hodamohammadi.conveyor.models

import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.commons.models.IUser
import java.io.Serializable
import java.util.*

/**
 * Default model for a single message.
 */
class DefaultMessage: IMessage, Serializable {
    private var id: String
    private var text: String
    private var createdAt: Date
    private var user: IUser

    constructor(id: String, text: String, createdAt: Date, user: DefaultUser) {
        this.id = id
        this.text = text
        this.createdAt = createdAt
        this.user = user
    }

    fun setId(id: String) {
        this.id = id
    }

    override fun getId(): String {
        return id
    }

    fun setText(id: String) {
        this.text = text
    }

    override fun getText(): String {
        return text
    }

    fun setcreatedAt(createdAt: Date) {
        this.createdAt = createdAt
    }

    override fun getCreatedAt(): Date {
        return createdAt
    }

    fun setuser(user: DefaultUser) {
        this.user = user
    }

    override fun getUser(): IUser {
        return user
    }

}