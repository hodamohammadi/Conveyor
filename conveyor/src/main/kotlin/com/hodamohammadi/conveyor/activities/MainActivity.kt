package com.hodamohammadi.conveyor.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hodamohammadi.conveyor.R
import com.hodamohammadi.navigations.features.AuthenticationNavigation

/**
 * Main application activity.
 */
class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        launchAuthentication()
    }

    private fun launchAuthentication() = AuthenticationNavigation.dynamicStart?.let {
        startActivity(it)
    }
}