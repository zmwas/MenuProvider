package com.zack.menuprovider

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.io.IOException
import java.nio.charset.Charset


class Util {
    companion object {
        val gson: Gson = Gson()

        @JvmStatic
        fun loadJSONFromAsset(context: Context): String {
            var json: String? = null
            try {
                val `is` = context.getAssets().open("z_output.json")
                val size = `is`.available()
                val buffer = ByteArray(size)
                `is`.read(buffer)
                `is`.close()
                json = String(buffer, Charset.defaultCharset())
            } catch (ex: IOException) {
                ex.printStackTrace()
            }

            return json!!
        }

        @JvmStatic
        fun getMenuList(context: Context): List<MenuItem> {
            val json: String? = loadJSONFromAsset(context)
            val list: List<MenuItem>
            list = gson.fromJson(json, object : TypeToken<List<MenuItem>>() {}.type)
            return list
        }

    }
}