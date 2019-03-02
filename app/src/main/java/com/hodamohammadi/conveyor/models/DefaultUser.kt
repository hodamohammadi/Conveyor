package com.hodamohammadi.conveyor.models

import com.stfalcon.chatkit.commons.models.IUser
import java.io.Serializable

/**
 * Default model for a user.
 */
class DefaultUser: IUser, Serializable {
    private var id: String
    private var name: String?
    private var avatar: String?

    constructor(id: String, name: String?, avatar: String?) {
        this.id = id
        this.name = name
        this.avatar = avatar
    }

    fun setId(id: String) {
        this.id = id
    }

    override fun getId(): String {
        return id
    }

    fun setName(name: String) {
        this.name = name
    }

    override fun getName(): String? {
        return name
    }

    fun setAvatar(avatar: String) {
        this.avatar = avatar
    }

    override fun getAvatar(): String? {
        return avatar
    }
}