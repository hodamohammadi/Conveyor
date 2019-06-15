package com.hodamohammadi.chat.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hodamohammadi.chat.R
import com.hodamohammadi.chat.models.DefaultDialog
import com.hodamohammadi.chat.utils.AppUtils
import com.hodamohammadi.chat.viewmodels.ChatsListViewModel
import com.hodamohammadi.chat.viewmodels.SharedChatViewModel
import com.hodamohammadi.chat.viewmodels.ViewModelFactory
import com.hodamohammadi.navigation.RoutePath
import com.hodamohammadi.navigations.loaders.loadFragmentOrNull
import com.hodamohammadi.services.BaseResourceObserver
import com.stfalcon.chatkit.commons.models.IDialog
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.dialogs.DialogsListAdapter
import kotlinx.android.synthetic.main.chats_list_fragment.*

/**
 * Fragment for a list of user's chats.
 */
class ChatsListFragment : Fragment(), DialogsListAdapter.OnDialogClickListener<IDialog<IMessage>>,
        DialogsListAdapter.OnDialogLongClickListener<IDialog<IMessage>> {

    companion object {
        private val TAG = ChatsListFragment::class.qualifiedName
    }

    private lateinit var dialogListAdapter: DialogsListAdapter<IDialog<IMessage>>
    private lateinit var sharedChatViewModel: SharedChatViewModel
    private lateinit var chatsListViewModel: ChatsListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.chats_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedChatViewModel = ViewModelProviders.of(requireActivity(), ViewModelFactory)
                .get(SharedChatViewModel::class.java)
        chatsListViewModel = ViewModelProviders.of(this, ViewModelFactory)
                .get(ChatsListViewModel::class.java)

        initAdapater()

        chatsListViewModel.getUserDialogsLiveData
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
        chatsListViewModel.getUserDialogs.value = null
        dialogsList.setAdapter(dialogListAdapter)
    }

    override fun onDialogClick(dialog: IDialog<IMessage>?) {
        if (dialog != null && dialog.id != null) {
            sharedChatViewModel.threadId = dialog.id
            openSelectedChat()
        }
    }

    override fun onDialogLongClick(dialog: IDialog<IMessage>?) {
        // TODO: Implement chat delete/edits
    }

    private fun openSelectedChat() {
        requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.chat_container, RoutePath.SINGLE_CHAT_FRAGMENT.loadFragmentOrNull()!!)
                .addToBackStack(TAG)
                .commit()
    }
}