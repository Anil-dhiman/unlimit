package com.unlimit_test.presentation

import android.app.Application
import android.content.Context
import com.unlimit_test.presentation.di.appModule
import com.unlimit_test.presentation.di.repoModule
import com.unlimit_test.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

class App : Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context=this@App

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(getModules())
        }
    }

    fun getModules(): MutableList<Module> {
        return mutableListOf<Module>(appModule, repoModule, viewModelModule)
    }

}