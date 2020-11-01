package com.architectcoders.grupo2verano2020.data.di


import com.architectcoders.grupo2verano2020.data.server.TheQuestionDb
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class ServerModule {

    @Singleton
    @Provides
    @Named("baseUrl")
    fun baseUrlProvider()="https://architectcoders.firebaseio.com/"

    @Singleton
    @Provides
    fun questionDBProvider(@Named("baseUrl")baseUrl:String)= TheQuestionDb(baseUrl)
}