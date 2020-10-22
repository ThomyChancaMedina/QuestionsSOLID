package com.architectcoders.grupo2verano2020.data.database

import com.architectcoders.domain.Question
import com.architectcoders.grupo2verano2020.data.database.QuestionsAnswers as DataBase

fun Question.toRoomQuestion(): DataBase =
    DataBase(
        id,
        question,
        answers
    )

fun DataBase.toDomainQuestion(): Question =
    Question(
        id,
        question,
        answer
    )



