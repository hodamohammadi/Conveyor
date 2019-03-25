package com.hodamohammadi.navigations.features

import android.content.Intent
import com.hodamohammadi.navigation.RoutePath
import com.hodamohammadi.navigations.loaders.loadIntentOrNull

object AuthenticationNavigation : DynamicFeature<Intent> {
    override val dynamicStart: Intent?
        get() = RoutePath.AUTHENTICATION.loadIntentOrNull()
}