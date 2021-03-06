package com.g3.kiwi

import android.app.Application
import com.g3.kiwi.routing.KiwiCoordinator
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Session : Application() {

    companion object {
        lateinit var application: Session
            private set
    }

    val coordinator: KiwiCoordinator by lazy { KiwiCoordinator() }

    override fun onCreate() {
        super.onCreate()
        application = this

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@Session)
            modules(koinModules)
        }
    }
}