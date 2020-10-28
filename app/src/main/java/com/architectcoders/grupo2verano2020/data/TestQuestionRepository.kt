package com.architectcoders.grupo2verano2020.data

import com.architectcoders.grupo2verano2020.App
import com.architectcoders.grupo2verano2020.data.provider.QuestionProviderImpl

class TestQuestionRepository(val app:App){

    suspend fun getAllQuestionTest()=
        QuestionProviderImpl(app).getItems()
}