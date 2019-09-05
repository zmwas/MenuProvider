package com.zack.menuprovider

import android.app.Application
import com.zack.menuprovider.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MenuProvider : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MenuProvider)
            modules(databaseModule)
        }
    }
}