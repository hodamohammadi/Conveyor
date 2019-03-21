package com.hodamohammadi.conveyor.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hodamohammadi.commons.navigation.loadIntentOrNull
import com.hodamohammadi.conveyor.R
import com.hodamohammadi.conveyor.models.DefaultDialog
import com.hodamohammadi.conveyor.services.BaseResourceObserver
import com.hodamohammadi.conveyor.services.RoutePath
import com.hodamohammadi.conveyor.utils.AppUtils
import com.hodamohammadi.conveyor.viewmodels.ChatViewModel
import com.hodamohammadi.conveyor.viewmodels.ViewModelFactory
import com.stfalcon.chatkit.commons.models.IDialog
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.dialogs.DialogsListAdapter
import kotlinx.android.synthetic.main.chats_list_fragment.*

/**
 * Fragment for a list of user's chats.
 */
class ChatsListFragment : Fragment(), DialogsListAdapter.OnDialogClickListener<IDialog<IMessage>>,
        DialogsListAdapter.OnDialogLongClickListener<IDialog<IMessage>> {

    private lateinit var dialogListAdapter: DialogsListAdapter<IDialog<IMessage>>
    private lateinit var chatViewModel: ChatViewModel

    companion object {
        fun newInstance() = ChatsListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.chats_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chatViewModel = ViewModelProviders.of(requireActivity(), ViewModelFactory)
                .get(ChatViewModel::class.java)

        initAdapater()

        chatViewModel.getUserDialogsLiveData
                .observe(this, object : BaseResourceObserver<List<DefaultDialog>>() {
            override fun onSuccess(data: List<DefaultDialog>?) {
                super.onSuccess(data)
                if (data.isNullOrEmpty()) {
                    // TODO - Should have an empty state for this
                    Toast.makeText(context, "No chats found!", Toast.LENGTH_SHORT)
                            .show()
                }
                dialogListAdapter.setItems(data)
                dialogListAdapter.notifyDataSetChanged()
            }
        })
    }

    fun initAdapater() {
        dialogListAdapter = DialogsListAdapter(AppUtils.CustomImageLoader)
        dialogListAdapter.setOnDialogClickListener(this)
        dialogListAdapter.setOnDialogLongClickListener(this)
        chatViewModel.getUserDialogs.value = null
        dialogsList.setAdapter(dialogListAdapter)
    }

    override fun onDialogClick(dialog: IDialog<IMessage>?) {
        chatViewModel.threadId = dialog!!.id
        startChat()
    }

    override fun onDialogLongClick(dialog: IDialog<IMessage>?) {
        // TODO: Implement chat delete/edits
    }

    private fun startChat() = RoutePath.CHAT_ACTIVITY.loadIntentOrNull().let {
        it!!.action = RoutePath.SINGLE_CHAT_FRAGMENT
        startActivity(it)
    }
}