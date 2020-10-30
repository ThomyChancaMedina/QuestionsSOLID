package com.architectcoders.grupo2verano2020.data.server

import com.architectcoders.grupo2verano2020.data.server.model.Questions as ServerQuestion
import com.architectcoders.domain.question.Question

import com.architectcoders.domain.question.Answer
import com.architectcoders.grupo2verano2020.data.server.model.Answer as ServerAnswer

fun ServerQuestion.toDomainQuestionAnswer(): Question =
    Question(
       id =  id,
       question =  question,
       answers =  answers.map { it.toDomainAnswer() }
    )

fun ServerAnswer.toDomainAnswer(): Answer =
    Answer(
        answer,
        isCorrect
    )