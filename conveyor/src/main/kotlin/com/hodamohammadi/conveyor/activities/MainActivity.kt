package com.hodamohammadi.conveyor.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hodamohammadi.conveyor.R
import com.hodamohammadi.navigation.RoutePath
import com.hodamohammadi.navigations.features.AuthenticationNavigation
import com.hodamohammadi.navigations.features.ChatNavigation

/**
 * Main application activity.
 */
class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        launchAuthentication()
    }

    private fun launchAuthentication() = AuthenticationNavigation.dynamicStart?.let {
        startActivityForResult(it, LOGIN)
        //startActivity(it)
    }

    private fun launchChat() = ChatNavigation.dynamicStart?.let {
        it.action = RoutePath.CHATS_LIST_FRAGMENT
        startActivity(it)
    }

    private fun launchError() {
        // error.
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when {
            requestCode == LOGIN && resultCode == Activity.RESULT_OK -> launchChat()
            else -> launchError()
        }
        finish()
    }

    companion object {
        private const val LOGIN = 100
    }
}