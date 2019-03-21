package com.hodamohammadi.conveyor.services

/**
 * Route path constants.
 */
class RoutePath private constructor(){
    companion object {
        const val PACKAGE_NAME: String = "com.hodamohammadi.conveyor."
        const val AUTHENTICATION: String = PACKAGE_NAME + "activities.AuthenticationActivity"
        const val CHAT_ACTIVITY: String = PACKAGE_NAME + "activities.ChatActivity"
        const val CHATS_LIST_FRAGMENT: String = PACKAGE_NAME + "fragments.ChatsListFragment"
        const val SINGLE_CHAT_FRAGMENT: String = PACKAGE_NAME + "fragments.SingleChatFragment"
    }
}