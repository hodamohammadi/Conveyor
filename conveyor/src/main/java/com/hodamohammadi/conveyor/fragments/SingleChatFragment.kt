package com.hodamohammadi.conveyor.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hodamohammadi.conveyor.utils.FirebaseHelper
import com.hodamohammadi.conveyor.R
import com.hodamohammadi.conveyor.utils.AppUtils
import com.hodamohammadi.conveyor.viewmodels.ViewModelFactory
import com.hodamohammadi.conveyor.viewmodels.ChatViewModel
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
    private lateinit var chatViewModel: ChatViewModel

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

        chatViewModel = ViewModelProviders.of(requireActivity(), ViewModelFactory)
                .get(ChatViewModel::class.java)

        initAdapter()
    }

    private fun initAdapter() {
        messagesAdapter =
                MessagesListAdapter(FirebaseHelper.getCurrentUser().id, AppUtils.CustomImageLoader)
        this.messagesList.setAdapter(messagesAdapter)
    }

    override fun onSubmit(input: CharSequence): Boolean {
        val message: IMessage =
                FirebaseHelper.sendMessage(input.toString(), chatViewModel.threadId!!)
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
}