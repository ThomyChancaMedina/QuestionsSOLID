package com.architectcoders.grupo2verano2020.data.di

import android.app.Application
import com.architectcoders.grupo2verano2020.ui.testFragment.TestFragmentComponent

import com.architectcoders.grupo2verano2020.ui.testFragment.TestSolidModule
import com.architectcoders.grupo2verano2020.ui.testResult.QuestionFragmentComponent
import com.architectcoders.grupo2verano2020.ui.testResult.QuestionModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, DataModule::class, ServerModule::class])
interface ProjectComponent {
    fun plus(module: TestSolidModule): TestFragmentComponent
    fun plus(module: QuestionModule): QuestionFragmentComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): ProjectComponent
    }

}