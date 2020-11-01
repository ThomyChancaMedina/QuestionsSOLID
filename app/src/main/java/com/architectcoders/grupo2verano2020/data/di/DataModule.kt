package com.architectcoders.grupo2verano2020.data.di

import com.architectcoders.data.repository.QuestionRepository
import com.architectcoders.data.repository.TestRepository
import com.architectcoders.data.source.LocalDataSource
import com.architectcoders.data.source.RemoteDataSource
import com.architectcoders.data.testSource.TestDataSource
import com.architectcoders.data.testSource.TestLocalSource
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun questionRepositoryProvider(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
    ) = QuestionRepository(localDataSource, remoteDataSource)

    @Provides
    fun testRepositoryProvider(
        testLocalSource: TestLocalSource,
        testDataSource: TestDataSource
    ) = TestRepository(testLocalSource, testDataSource)

}