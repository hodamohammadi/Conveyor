package com.hodamohammadi.navigation

/**
 * Route path constants.
 */
class RoutePath private constructor(){
    companion object {
        const val PACKAGE_NAME: String = "com.hodamohammadi."
        const val AUTHENTICATION: String = PACKAGE_NAME + "authentication.AuthenticationActivity"
        const val CHAT_ACTIVITY: String = PACKAGE_NAME + "conveyor.activities.ChatActivity"
        const val CHATS_LIST_FRAGMENT: String = PACKAGE_NAME + "conveyor.fragments.ChatsListFragment"
        const val SINGLE_CHAT_FRAGMENT: String = PACKAGE_NAME + "conveyor.fragments.SingleChatFragment"
    }
}