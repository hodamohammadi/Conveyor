package com.hodamohammadi.navigations.features

import android.content.Intent
import com.hodamohammadi.navigation.RoutePath
import com.hodamohammadi.navigations.loaders.loadIntentOrNull

object ChatNavigation : DynamicFeature<Intent> {
    override val dynamicStart: Intent?
        get() = RoutePath.CHAT_ACTIVITY.loadIntentOrNull()
}