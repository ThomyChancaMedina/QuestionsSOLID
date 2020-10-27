package com.architectcoders.grupo2verano2020.data.provider

import com.architectcoders.grupo2verano2020.data.database.storage.Storage
private const val TEST_USER_LIST = "test_user_list"
private const val TEST_USER = "test_user"
class ResultTest(private val storage: Storage) {

    //string

    val testIud: String
        get() = storage.getString(TEST_USER)

    fun saveTest(username: String) {
        storage.setString(TEST_USER, username)
    }



}