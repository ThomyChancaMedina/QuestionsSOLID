package com.architectcoders.grupo2verano2020

import android.app.Application
import com.architectcoders.grupo2verano2020.data.di.AppModule
import com.architectcoders.grupo2verano2020.data.di.DataModule
import com.architectcoders.grupo2verano2020.data.di.ProjectComponent
import com.architectcoders.grupo2verano2020.data.server.TheQuestionDb
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.mockwebserver.MockWebServer
import javax.inject.Named
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, DataModule::class,TestServerModule::class])
interface UiTestComponent :ProjectComponent{
    val questionDB:TheQuestionDb
    val mockWebServer:MockWebServer

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): UiTestComponent
    }
}

@Module
class TestServerModule{
    @Singleton
    @Provides
    @Named("baseUrl")
    fun baseUrlProvider()= "http://127.0.0.1:800"

    @Provides
    @Singleton
    fun mockWebServerProvider():MockWebServer{
        var mockWebServer:MockWebServer?=null
        val thread = Thread {
            mockWebServer = MockWebServer()
            mockWebServer?.start(8080)
        }
        thread.start()
        thread.join()
        return mockWebServer?:throw NullPointerException()
    }

    @Provides
    @Singleton
    fun lolServiceManagerProvider(
        @Named("baseUrl") baseUrl:String
    ): TheQuestionDb = TheQuestionDb(baseUrl)


}