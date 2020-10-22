package com.architectcoders.grupo2verano2020

import android.app.Application
import androidx.room.Room
import com.architectcoders.grupo2verano2020.data.database.QuestionDatabase

class App : Application() {

    lateinit var db: QuestionDatabase
        private set


    override fun onCreate() {
        super.onCreate()
        db= Room.databaseBuilder(
            this,
            QuestionDatabase::class.java,
            "question-db"
        ).build()
    }

}