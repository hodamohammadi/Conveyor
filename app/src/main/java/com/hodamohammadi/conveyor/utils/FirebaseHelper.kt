package com.hodamohammadi.conveyor.utils

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hodamohammadi.conveyor.models.DefaultMessage
import com.hodamohammadi.conveyor.models.DefaultUser
import com.stfalcon.chatkit.commons.models.IMessage
import java.util.*


/**
 * Helper class for Firebase services.
 */
class FirebaseHelper {
    companion object {
        private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        private val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
        private val firebaseUser: FirebaseUser? = firebaseAuth.currentUser

        fun isUserAuthenticated(): Boolean {
            return firebaseUser != null
        }

        fun getCurrentUser(): DefaultUser {
            return DefaultUser(firebaseUser!!.uid, firebaseUser.displayName, firebaseUser.photoUrl.toString())
        }

        fun sendMessage(messageInput: String): IMessage {
            val messageReference: DatabaseReference = getThreadsDatabase().child(getCurrentThreadId())
            val messageKey: String? = messageReference.push().key
            val message = DefaultMessage(messageKey!!, messageInput, Date(), getCurrentUser())
            messageReference.child(messageKey).setValue(message)
            return message
        }

        private fun getThreadsDatabase(): DatabaseReference {
            val databaseReference: DatabaseReference = firebaseDatabase.getReference(FirebaseConstants.THREADS_DATABASE)
            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Log.d("CHAT", "successful database reference")
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e("CHAT", databaseError.message)
                }
            })
            return databaseReference
        }

        private fun getCurrentThreadId(): String {
            return "thread_id_placeholder"
        }

    }
}