package com.hodamohammadi.conveyor.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hodamohammadi.conveyor.R
import com.hodamohammadi.conveyor.utils.AppUtils
import com.hodamohammadi.conveyor.utils.FirebaseHelper
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

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.chats_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapater()
    }

    fun initAdapater() {
        dialogListAdapter = DialogsListAdapter(AppUtils.CustomImageLoader)
        dialogListAdapter.setItems(FirebaseHelper.getThreadsList())
        dialogListAdapter.setOnDialogClickListener(this)
        dialogListAdapter.setOnDialogLongClickListener(this)

        dialogsList.setAdapter(dialogListAdapter)
    }

    override fun onDialogClick(dialog: IDialog<IMessage>?) {
        FirebaseHelper.setCurrentThreadId("thread_id_placeholder")
    }

    override fun onDialogLongClick(dialog: IDialog<IMessage>?) {
        //TODO: Implement chat delete/edits
    }
}