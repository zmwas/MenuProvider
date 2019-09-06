package com.zack.menuprovider

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.android.inject
import java.util.concurrent.Executors

class Activity: AppCompatActivity() {
    private val menuItemDao: MenuItemDao by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Executors.newSingleThreadExecutor().execute {
            menuItemDao.fetchAll()
        }
        this.startService(Intent(this, MenuProviderService::class.java))
    }
}
