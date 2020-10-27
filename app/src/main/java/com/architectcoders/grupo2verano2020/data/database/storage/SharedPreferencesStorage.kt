package com.architectcoders.grupo2verano2020.data.database.storage

import android.content.Context
import com.google.gson.Gson


class SharedPreferencesStorage(context: Context) : Storage {

    private val sharedPreferences = context.getSharedPreferences("Test", Context.MODE_PRIVATE)

    override fun setString(key: String, value: String) {
        with(sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }
    override fun getString(key: String): String {
        return sharedPreferences.getString(key, "")!!
    }


}