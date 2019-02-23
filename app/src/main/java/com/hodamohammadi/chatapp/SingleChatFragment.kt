package com.hodamohammadi.chatapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Fragment for a single chat screen.
 */
class SingleChatFragment : Fragment() {
    companion object {
        /**
         * Creates a new instance of SingleChatFragment.
         */
        fun newInstance(): SingleChatFragment {
            return SingleChatFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.single_chat_fragment, container, false)
    }
}