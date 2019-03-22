package com.hodamohammadi.conveyor.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hodamohammadi.commons.navigation.loadFragmentOrNull
import com.hodamohammadi.conveyor.R
import com.hodamohammadi.conveyor.services.RoutePath

/**
 * Main activity for chat screens.
 */
class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_activity)
        parseIntent(intent)
    }

    private fun parseIntent(intent: Intent) {
        val action: String = intent.action
        if (RoutePath.CHATS_LIST_FRAGMENT.equals(action)) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.chat_container, RoutePath.CHATS_LIST_FRAGMENT.loadFragmentOrNull()!!)
                    .commit()

        } else if (RoutePath.SINGLE_CHAT_FRAGMENT.equals(action)) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.chat_container, RoutePath.SINGLE_CHAT_FRAGMENT.loadFragmentOrNull()!!)
                    .commit()
        }
    }

}