package com.architectcoders.grupo2verano2020

import android.app.Application
import androidx.room.Room
import com.architectcoders.grupo2verano2020.data.database.QuestionDatabase
import com.architectcoders.grupo2verano2020.data.database.storage.SharedPreferencesStorage
import com.architectcoders.grupo2verano2020.data.provider.ResultTest

class App : Application() {

    lateinit var db: QuestionDatabase
        private set

    val resultTest by lazy {
        ResultTest(SharedPreferencesStorage(this))
    }

    override fun onCreate() {
        super.onCreate()
        db= Room.databaseBuilder(
            this,
            QuestionDatabase::class.java,
            "question-db"
        ).build()
    }

}