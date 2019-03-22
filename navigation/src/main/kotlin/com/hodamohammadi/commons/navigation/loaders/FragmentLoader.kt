package com.hodamohammadi.commons.navigation.loaders

import android.support.v4.app.Fragment

fun String.loadFragmentOrNull(): Fragment? =
    try {
        this.loadClassOrNull<Fragment>()?.newInstance()
    } catch (e: ClassNotFoundException) {
        null
    }
