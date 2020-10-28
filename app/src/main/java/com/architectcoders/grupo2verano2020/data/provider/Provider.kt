package com.architectcoders.grupo2verano2020.data.provider


import com.architectcoders.grupo2verano2020.App
import com.architectcoders.grupo2verano2020.data.model.TestQuestion
import com.architectcoders.grupo2verano2020.ui.common.listQuestion


interface QuestionProvider {
    fun getItems(): List<TestQuestion>
}


class QuestionProviderImpl(val app: App) : QuestionProvider {
    override fun getItems(): List<TestQuestion> {
        Thread.sleep(2000)
        return app.listQuestion().mapIndexed { idx, value ->
            TestQuestion(
                idx,
                value,
                0
            )
        }
    }

}