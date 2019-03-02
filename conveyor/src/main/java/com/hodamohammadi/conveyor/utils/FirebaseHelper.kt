package com.hodamohammadi.conveyor.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.hodamohammadi.conveyor.models.DefaultUser


/**
 * Helper class for Firebase services.
 */
class FirebaseHelper {

    companion object {

        private val firebaseAuth : FirebaseAuth = FirebaseAuth.getInstance()
        private val firebaseUser : FirebaseUser? = firebaseAuth.getCurrentUser()

        fun isUserAuthenticated() : Boolean {
            return firebaseUser != null
        }

        fun getCurrentUser() : DefaultUser {
            return DefaultUser(firebaseUser!!.uid, firebaseUser.displayName, firebaseUser.photoUrl.toString())
        }

    }
}