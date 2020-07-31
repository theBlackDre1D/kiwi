package com.g3.kiwi.screens.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.g3.kiwi.Session

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Session.application.coordinator.openHomeActivity(this)
    }
}