package com.hodamohammadi.chatapp.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hodamohammadi.chatapp.R
import com.hodamohammadi.chatapp.fragments.SingleChatFragment
import com.hodamohammadi.chatapp.utils.FirebaseHelper

/**
 * Main activity for chat screens.
 */
class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_activity)

        if (FirebaseHelper.isUserAuthenticated()) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.chat_container, SingleChatFragment())
                    .commit()
        }
    }
}
