package com.syarah.budgetmanagement

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class BudgetManagementApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setUpLogging()
    }

    private fun setUpLogging() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}