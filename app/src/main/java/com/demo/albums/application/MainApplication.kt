package com.demo.albums.application

import android.app.Application
import com.demo.albums.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/*
*Setting up Hilt-
 Base Application class: This class is necessary for the hilt and should annotate it with @HiltAndroidApp.
* Need to mention at manifest file in the application tag.
* @HiltAndroidApp would be internally generating the components required by any class
* */
@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
