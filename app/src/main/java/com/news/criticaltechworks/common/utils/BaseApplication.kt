package com.news.criticaltechworks.common.utils

import android.app.Application
import android.content.Context


/**
 * Global appContext object. VERRY helpful to get application context anywhere in the code :)
 */
val appContext = BaseApplication.appContext

/**
 * Base Application class
 */
open class BaseApplication : Application() {
    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}