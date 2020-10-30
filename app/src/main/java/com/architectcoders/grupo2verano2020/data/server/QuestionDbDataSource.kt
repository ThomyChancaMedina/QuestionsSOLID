package com.architectcoders.grupo2verano2020.data.server


import com.architectcoders.data.source.RemoteDataSource
import com.architectcoders.domain.question.Question


class QuestionDbDataSource(private val theQuestionDb: TheQuestionDb) : RemoteDataSource {

    override suspend fun getAllQuestions(): List<Question> =
        theQuestionDb.retrofit
            .listQuestionAsync()
            .map { it.toDomainQuestionAnswer() }


}