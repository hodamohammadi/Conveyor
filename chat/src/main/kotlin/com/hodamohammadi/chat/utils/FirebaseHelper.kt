package com.hodamohammadi.chat.utils

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hodamohammadi.chat.models.DefaultDialog
import com.hodamohammadi.chat.models.DefaultMessage
import com.hodamohammadi.chat.models.DefaultUser
import com.hodamohammadi.services.Resource
import com.stfalcon.chatkit.commons.models.IMessage
import java.util.Date

/**
 * Helper class for Firebase services.
 */
class FirebaseHelper private constructor() {
    companion object {
        private val TAG = FirebaseHelper::class.qualifiedName
        private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        private val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
        private var localUser: DefaultUser? = null

        val isUserAuthenticated: Boolean
            get() = firebaseAuth.currentUser != null

        fun getCurrentUser(): DefaultUser {
            if (localUser == null) {
                val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
                localUser = DefaultUser(firebaseUser!!.uid, firebaseUser.displayName,
                        firebaseUser.photoUrl.toString(), null)
            }
            return localUser!!
        }

        fun sendMessage(messageInput: String, threadId: String): IMessage {
            val messageReference: DatabaseReference =
                    getThreadsDatabase().child(threadId)
            val messageKey: String? = messageReference.push().key
            val message = DefaultMessage(messageKey!!, messageInput, Date(), getCurrentUser())
            messageReference.child(messageKey).setValue(message)
            return message
        }

        private fun getThreadsDatabase(): DatabaseReference {
            val databaseReference: DatabaseReference =
                    firebaseDatabase.getReference(FirebaseConstants.THREADS_DATABASE)
            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Log.d(TAG, "successful database reference")
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e(TAG, databaseError.message)
                }
            })
            return databaseReference
        }

        private fun getUsersDatabase(): DatabaseReference {
            val databaseReference: DatabaseReference =
                    firebaseDatabase.getReference(FirebaseConstants.USERS_DATABASE)
            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Log.d(TAG, "successful database reference")
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e(TAG, databaseError.message)
                }
            })
            return databaseReference
        }

        fun getUserThreads(): MutableLiveData<Resource<List<DefaultDialog>>> {
            val data = MutableLiveData<Resource<List<DefaultDialog>>>()

            val threads: MutableList<DefaultDialog> = mutableListOf()
            val messageReference: DatabaseReference =
                    getUsersDatabase().child(firebaseAuth.currentUser!!.uid)
                            .child(FirebaseConstants.USER_THREADS)

            messageReference.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            for (childDataSnapshot: DataSnapshot in dataSnapshot.children) {
                                childDataSnapshot.getValue(DefaultDialog::class.java)!!
                                threads.add(childDataSnapshot.getValue(DefaultDialog::class.java)!!)
                            }
                            data.value = Resource.success(threads)
                        }

                        override fun onCancelled(databaseError: DatabaseError) {
                            data.value = Resource.error(null)
                        }
                    })

            return data
        }
    }
}