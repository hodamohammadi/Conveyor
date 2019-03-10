package com.hodamohammadi.conveyor.models

import com.stfalcon.chatkit.commons.models.IDialog
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.commons.models.IUser
import java.io.Serializable

/**
 * Default model for a Dialog object.
 */
class DefaultDialog(
    val dialogImage: String = "",
    val dialogUnreadCount: Int = 0,
    val dialogId: String = "",
    val dialogUsers: MutableList<DefaultUser> = mutableListOf(),
    var dialogLastMessage: IMessage? = null,
    val dialogNickname: String = ""
): Serializable, IDialog<IMessage> {
    override fun getDialogPhoto(): String {
        return dialogImage
    }

    override fun getUnreadCount(): Int {
        return dialogUnreadCount
    }

    override fun getId(): String {
        return dialogId
    }

    override fun getUsers(): MutableList<DefaultUser> {
        return dialogUsers
    }

    override fun getLastMessage(): IMessage? {
        return dialogLastMessage
    }

    override fun setLastMessage(message: IMessage?) {
        this.dialogLastMessage = message
    }

    override fun getDialogName(): String {
        return dialogNickname
    }
}