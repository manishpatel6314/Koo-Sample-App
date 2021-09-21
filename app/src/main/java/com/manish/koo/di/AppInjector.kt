package com.manish.koo.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.manish.koo.helper.AppController
import dagger.android.AndroidInjection
import dagger.android.support.HasSupportFragmentInjector

object AppInjector {
    fun init(controllerApp: AppController) {
        DaggerAppComponent.builder().application(controllerApp)
            .build().inject(controllerApp)
        controllerApp
            .registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                    handleActivity(activity)
                }

                override fun onActivityStarted(activity: Activity) {

                }

                override fun onActivityResumed(activity: Activity) {

                }

                override fun onActivityPaused(activity: Activity) {

                }

                override fun onActivityStopped(activity: Activity) {

                }

                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {

                }

                override fun onActivityDestroyed(activity: Activity) {

                }
            })
    }

    private fun handleActivity(activity: Activity) {
        if (activity is HasSupportFragmentInjector) {
            AndroidInjection.inject(activity)
        }
    }
}
