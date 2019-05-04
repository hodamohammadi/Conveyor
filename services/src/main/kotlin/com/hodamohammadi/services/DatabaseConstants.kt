package com.hodamohammadi.services

/**
 * Firebase static constants.
 */
class DatabaseConstants private constructor(){
    companion object {
        const val THREADS_DATABASE: String = "threads"
        const val USERS_DATABASE: String = "users"
        const val USER_THREADS: String = "userThreads"
    }
}