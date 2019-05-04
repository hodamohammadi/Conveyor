package com.hodamohammadi.authentication

import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.hodamohammadi.authentication.R
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
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
            if (resultCode == RESULT_OK) {
                signInSuccess()
            } else {
                val response = IdpResponse.fromResultIntent(data)
                signInFail(response?.error?.localizedMessage)
            }
        }
    }

    private fun signInSuccess() = setResult(RESULT_OK).also { finish() }

    private fun signInFail(message: String?) = setResult(RESULT_CANCELED).also {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onBackPressed() {
        setResult(RESULT_CANCELED)
        super.onBackPressed()
    }
}