package com.architectcoders.grupo2verano2020.data.di

import android.app.Application
import androidx.room.Room
import com.architectcoders.data.source.LocalDataSource
import com.architectcoders.data.source.RemoteDataSource
import com.architectcoders.data.testSource.TestDataSource
import com.architectcoders.data.testSource.TestLocalSource
import com.architectcoders.grupo2verano2020.data.database.ProjectDatabase
import com.architectcoders.grupo2verano2020.data.database.Test.RoomTestDataSource
import com.architectcoders.grupo2verano2020.data.database.question.RoomDataSource
import com.architectcoders.grupo2verano2020.data.server.QuestionDbDataSource
import com.architectcoders.grupo2verano2020.data.server.TheQuestionDb
import com.architectcoders.grupo2verano2020.data.server.provider.TestDbDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun databaseProvider(app:Application)= Room.databaseBuilder(
        app,
        ProjectDatabase::class.java,
        "question-db"
    ).build()

    @Provides
    fun localQuestionSourceProvider(db: ProjectDatabase):LocalDataSource= RoomDataSource(db)

    @Provides
    fun remoteDataSourceProvider(theQuestionDb: TheQuestionDb):RemoteDataSource=QuestionDbDataSource(theQuestionDb)

    @Provides
    fun localTestSourceProvider(db: ProjectDatabase):TestLocalSource=RoomTestDataSource(db)

    @Provides
    fun testDataSourceProvider(): TestDataSource = TestDbDataSource()



}