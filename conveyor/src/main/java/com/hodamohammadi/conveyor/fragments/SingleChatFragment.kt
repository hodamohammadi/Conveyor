package com.hodamohammadi.conveyor.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.hodamohammadi.conveyor.utils.FirebaseHelper
import com.hodamohammadi.conveyor.R
import com.stfalcon.chatkit.commons.ImageLoader
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.messages.MessageInput
import com.stfalcon.chatkit.messages.MessagesListAdapter
import kotlinx.android.synthetic.main.single_chat_fragment.*

/**
 * Fragment for a single chat screen.
 */
class SingleChatFragment : Fragment(), MessageInput.InputListener, MessageInput.AttachmentsListener,
        MessageInput.TypingListener {

    private lateinit var messagesAdapter: MessagesListAdapter<IMessage>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.single_chat_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        input.setInputListener(this)
        input.setTypingListener(this)
        input.setAttachmentsListener(this)
        initAdapter()
    }

    private fun initAdapter() {
        messagesAdapter = MessagesListAdapter(FirebaseHelper.getCurrentUser().id, CustomImageLoader)
        this.messagesList.setAdapter(messagesAdapter)
    }

    override fun onSubmit(input: CharSequence): Boolean {
        val message: IMessage = FirebaseHelper.sendMessage(input.toString())
        messagesAdapter.addToStart(message, true)
        return true
    }

    override fun onAddAttachments() {
        // TODO: Implement method.
    }

    override fun onStartTyping() {
        // do nothing
    }

    override fun onStopTyping() {
        // do nothing
    }

    object CustomImageLoader : ImageLoader {
        override fun loadImage(imageView: ImageView, url: String?, payload: Any?) {
            // TODO: set image.
        }
    }
}