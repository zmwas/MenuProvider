package com.zack.menuprovider

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menu")
data class MenuItem(@PrimaryKey(autoGenerate = true)val _id:Long, val id:String, val name:String, val price:Int, val type:String) {
}