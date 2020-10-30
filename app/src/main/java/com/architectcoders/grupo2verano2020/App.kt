package com.architectcoders.grupo2verano2020

import android.app.Application
import com.architectcoders.grupo2verano2020.data.di.DaggerProjectComponent
import com.architectcoders.grupo2verano2020.data.di.ProjectComponent


open class App : Application() {

    lateinit var component: ProjectComponent
        private set

    override fun onCreate() {
        super.onCreate()

        component= initTestComponent()

    }
    open fun initTestComponent()=DaggerProjectComponent.factory().create(this)
}