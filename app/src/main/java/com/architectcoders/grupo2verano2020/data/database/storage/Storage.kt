package com.architectcoders.grupo2verano2020.data.database.storage

interface Storage {

    fun setString(key: String, value: String)
    fun getString(key: String): String
}