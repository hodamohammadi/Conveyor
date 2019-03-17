package com.hodamohammadi.conveyor.models

import com.stfalcon.chatkit.commons.models.IDialog
import com.stfalcon.chatkit.commons.models.IMessage
import java.io.Serializable

/**
 * Default model for a Dialog object.
 */
data class DefaultDialog(
    @JvmField val dialogPhoto: String = "",
    @JvmField val unreadCount: Int = 0,
    @JvmField val id: String = "",
    @JvmField val users: MutableList<DefaultUser> = mutableListOf(),
    @JvmField var lastMessage: IMessage? = null,
    @JvmField val dialogName: String = ""
): Serializable, IDialog<IMessage> {

    override fun getDialogPhoto(): String {
        return dialogPhoto
    }

    override fun getUnreadCount(): Int {
        return unreadCount
    }

    override fun getId(): String {
        return id
    }

    override fun getUsers(): MutableList<DefaultUser> {
        return users
    }

    override fun getLastMessage(): IMessage? {
        return lastMessage
    }

    override fun setLastMessage(message: IMessage?) {
        this.lastMessage = message
    }

    override fun getDialogName(): String {
        return dialogName
    }
}