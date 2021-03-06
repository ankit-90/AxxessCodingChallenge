package com.axxesscodingchallenge.base

import android.app.Application
import android.content.Context
import androidx.core.os.BuildCompat
import com.axxesscodingchallenge.BuildConfig
import com.axxesscodingchallenge.module.networkModule
import com.axxesscodingchallenge.module.repositoryModule
import com.axxesscodingchallenge.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 * @author Ankit Chandel
 * @since 04/11/20
 * <h1>BaseApp</h1>
 * <p>Base Application of the app the the application level components must be inittialized here</p>
 * */
class BaseApp: Application() {

    companion object {
        private lateinit var INSTANCE: BaseApp
        fun getContext(): BaseApp {
            return this.INSTANCE
        }
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        startKoin {
            androidLogger()
            androidContext(this@BaseApp)
            modules(listOf(networkModule, viewModelModule, repositoryModule))
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }


}