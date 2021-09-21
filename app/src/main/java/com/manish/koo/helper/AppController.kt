package com.manish.koo.helper

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import com.manish.koo.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Application File for request queues of volley.
 * Additional cases are made to remove duplicate calls to volley.
 */

class AppController : Application(), HasActivityInjector {
    lateinit var context: Context

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
        context = applicationContext
        instance = this
    }

    /**
     * Returns an [AndroidInjector] of [Activity]s.
     */
    override fun activityInjector() = dispatchingAndroidInjector

    companion object {
        @SuppressLint("StaticFieldLeak")
        @get:Synchronized
        lateinit var instance: AppController

    }

}