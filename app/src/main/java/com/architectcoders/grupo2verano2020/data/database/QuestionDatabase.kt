package com.architectcoders.grupo2verano2020.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.architectcoders.grupo2verano2020.data.database.Test.TestDao
import com.architectcoders.grupo2verano2020.data.database.Test.TestQuestion
import com.architectcoders.grupo2verano2020.data.database.question.IntegerListConverter
import com.architectcoders.grupo2verano2020.data.database.question.QuestionDao
import com.architectcoders.grupo2verano2020.data.database.question.QuestionsAnswers

@Database(entities =[QuestionsAnswers::class,TestQuestion::class],version = 1, exportSchema = true)
@TypeConverters(value = [IntegerListConverter::class])
abstract class ProjectDatabase :RoomDatabase(){
    abstract fun questionDao(): QuestionDao
    abstract fun testDao():TestDao


}