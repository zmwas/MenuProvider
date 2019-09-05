package com.zack.menuprovider

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomMasterTable.TABLE_NAME



@Dao
interface MenuItemDao {
    @Insert
    fun saveAll(menuItems: List<MenuItem>)

    @Insert
    fun save(menuItem: MenuItem):Long

    @Query("SELECT * FROM menu")
    fun selectAll(): Cursor

    @Query("SELECT * FROM menu  WHERE _id = :id")
    fun selectById(id: Long): Cursor

    @Query("SELECT * FROM menu")
    fun fetchAll(): List<MenuItem>
}