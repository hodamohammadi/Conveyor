package com.hodamohammadi.conveyor.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.hodamohammadi.conveyor.R
import com.hodamohammadi.conveyor.models.DefaultMessage
import com.hodamohammadi.conveyor.utils.FirebaseHelper
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.messages.MessageInput
import com.stfalcon.chatkit.messages.MessagesList
import com.stfalcon.chatkit.messages.MessagesListAdapter
import java.util.*

/**
 * Fragment for a single chat screen.
 */
class SingleChatFragment : Fragment(), MessageInput.InputListener, MessageInput.AttachmentsListener,
        MessageInput.TypingListener {

    private lateinit var messagesList : MessagesList
    private lateinit var messagesAdapter : MessagesListAdapter<IMessage>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater?.inflate(R.layout.single_chat_fragment, container, false)

        this.messagesList = view.findViewById<MessagesList>(R.id.messagesList) as MessagesList

        val input = view.findViewById<MessageInput>(R.id.input) as MessageInput
        input.setInputListener(this)
        input.setTypingListener(this)
        input.setAttachmentsListener(this)

        initAdapter()

        return view
    }

    private fun initAdapter() {
        messagesAdapter = MessagesListAdapter<IMessage>(FirebaseHelper.getCurrentUser().id, imageLoader)
        this.messagesList.setAdapter(messagesAdapter)
    }

    override fun onSubmit(input: CharSequence): Boolean {
        //TODO: set user info.
        val user = FirebaseHelper.getCurrentUser()
        messagesAdapter.addToStart(DefaultMessage("messageId", input.toString(), Date(), user), true)
        return true
    }

    override fun onAddAttachments() {
        //TODO: Implement method.
    }

    override fun onStartTyping() {
        //do nothing
    }

    override fun onStopTyping() {
        //do nothing
    }

    object imageLoader : ImageLoader {
        override fun loadImage(imageView: ImageView, url: String?, payload: Any?) {
            //TODO: set image.
        }
    }
}