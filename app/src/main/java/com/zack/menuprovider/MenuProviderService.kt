package com.zack.menuprovider

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import org.koin.android.ext.android.inject

class MenuProviderService : Service() {


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        val notification = createNotification()

        startForeground(1, notification)

    }

    override fun onDestroy() {
        super.onDestroy()
        stopForeground(true)
    }

    private fun createNotification(): Notification {
        val notificationChannelId = "PROXY SERVER"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager;
            val channel = NotificationChannel(
                notificationChannelId,
                "Menu Provider channel",
                NotificationManager.IMPORTANCE_HIGH
            ).let {
                it.description = "Menu Provider for menu app"
                it
            }
            notificationManager.createNotificationChannel(channel)
        }


        val builder: Notification.Builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) Notification.Builder(
            this,
            notificationChannelId
        ) else Notification.Builder(this)

        return builder
            .setContentTitle("Menu Provider")
            .setContentText("Menu provider running")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setTicker("Menu provider running")
            .build()
    }

}