package com.zack.menuprovider

import android.app.Application
import android.content.ContentValues
import androidx.core.content.contentValuesOf
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.zack.menuprovider.Util.Companion.getMenuList
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import java.util.concurrent.Executors

val databaseModule = module {
    single {
        Room.databaseBuilder(androidApplication(), MenuDatabase::class.java, Config.databaseName)
            .addCallback(object :RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Executors.newSingleThreadExecutor().execute {
                        for (menuItem:MenuItem in getMenuList(androidApplication())) {
                            val contentValues = ContentValues()
                            contentValues.put("id",menuItem.id)
                            contentValues.put("name", menuItem.name)
                            contentValues.put("price", menuItem.price)
                            contentValues.put("type", menuItem.type)
                            db.insert("menu", 5,contentValues)
                        }
                    }
                }
            })
            .build()
    }

    single {
        get<MenuDatabase>().menuItemDao()
    }
}


