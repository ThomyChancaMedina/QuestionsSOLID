package com.architectcoders.grupo2verano2020.data.database.question

import com.architectcoders.domain.question.Question
import com.architectcoders.grupo2verano2020.data.database.question.QuestionsAnswers as DataBase

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



