package com.architectcoders.grupo2verano2020




class AppTest : App(){
    override fun initTestComponent() = DaggerUiTestComponent.factory().create(this)
}