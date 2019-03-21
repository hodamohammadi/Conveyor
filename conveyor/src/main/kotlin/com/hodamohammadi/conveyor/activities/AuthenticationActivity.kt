package com.hodamohammadi.conveyor.activities

import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.hodamohammadi.conveyor.R
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.hodamohammadi.commons.navigation.loadIntentOrNull
import com.hodamohammadi.conveyor.services.RoutePath
import java.util.*

/**
 * Activity for authentication screens.
 */
class AuthenticationActivity : AppCompatActivity() {
    companion object {
        private const val rcSignIn = 9001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.authentication_activity)
        // TODO - Check whether a user is already signed in. Will implement when we have logout functionality.
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                AuthUI.IdpConfig.GoogleBuilder().build(),
                                AuthUI.IdpConfig.PhoneBuilder().build(),
                                AuthUI.IdpConfig.AnonymousBuilder().build()))
                        .build(), rcSignIn)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == rcSignIn) {
            val response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                launchChat()
            } else {
                Toast.makeText(this, response?.error?.localizedMessage, Toast.LENGTH_SHORT)
                        .show()
            }
        }
    }
    private fun launchChat() = RoutePath.CHAT_ACTIVITY.loadIntentOrNull().let {
        it!!.action = RoutePath.CHATS_LIST_FRAGMENT
        startActivity(it)
    }
}