package com.architectcoders.grupo2verano2020.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities =[QuestionsAnswers::class],version = 1, exportSchema = true)
@TypeConverters(value = [IntegerListConverter::class])
abstract class QuestionDatabase :RoomDatabase(){
    abstract fun questionDao(): QuestionDao

}