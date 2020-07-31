package com.g3.kiwi.routing

import android.app.Activity
import android.content.Intent
import com.g3.kiwi.base.BaseCoordinator
import com.g3.kiwi.screens.home.HomeActivity

class KiwiCoordinator : BaseCoordinator() {

    fun openHomeActivity(activity: Activity) {
        val intent = Intent(activity, HomeActivity::class.java)
        super.startActivity(activity, intent, true)
    }
}