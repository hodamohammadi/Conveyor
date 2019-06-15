package com.hodamohammadi.chat.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hodamohammadi.services.gateways.FirebaseGateway
import com.hodamohammadi.chat.R
import com.hodamohammadi.chat.models.DefaultMessage
import com.hodamohammadi.chat.utils.AppUtils
import com.hodamohammadi.chat.viewmodels.ViewModelFactory
import com.hodamohammadi.chat.viewmodels.SharedChatViewModel
import com.hodamohammadi.chat.viewmodels.SingleChatViewModel
import com.hodamohammadi.services.BaseResourceObserver
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
    private lateinit var sharedChatViewModel: SharedChatViewModel
    private lateinit var singleChatViewModel: SingleChatViewModel

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

        sharedChatViewModel = ViewModelProviders.of(requireActivity(), ViewModelFactory)
                .get(SharedChatViewModel::class.java)

        singleChatViewModel = ViewModelProviders.of(this, ViewModelFactory)
                .get(SingleChatViewModel::class.java)

        singleChatViewModel.getThreadHistoryLiveData
                .observe(this, object : BaseResourceObserver<List<DefaultMessage>>() {
                    override fun onSuccess(data: List<DefaultMessage>?) {
                        super.onSuccess(data)
                        if (!data.isNullOrEmpty()) {
                            for (message: DefaultMessage in data) {
                                messagesAdapter.addToStart(message, false)
                            }
                            messagesAdapter.notifyDataSetChanged()
                        }
                    }
                })
        singleChatViewModel.getThreadHistory.value = sharedChatViewModel.threadId
    }

    private fun initAdapter() {
        messagesAdapter =
                MessagesListAdapter(FirebaseGateway.getCurrentUser().id, AppUtils.CustomImageLoader)
        this.messagesList.setAdapter(messagesAdapter)
    }

    override fun onSubmit(input: CharSequence): Boolean {
        val message: IMessage =
                FirebaseGateway.sendMessage(input.toString(), sharedChatViewModel.threadId!!)
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