package com.architectcoders.grupo2verano2020.data.server


import com.architectcoders.data.source.RemoteDataSource
import com.architectcoders.domain.Question


class QuestionDbDataSource : RemoteDataSource {

    override suspend fun getAllQuestions(): List<Question> =
        TheQuestionDb.retrofit
            .listQuestionAsync()
            .map { it.toDomainQuestionAnswer() }


}