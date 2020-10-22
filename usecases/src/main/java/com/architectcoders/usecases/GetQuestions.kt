package com.architectcoders.usecases

import com.architectcoders.data.repository.QuestionRepository
import com.architectcoders.domain.Question

class GetQuestions(private val questionRepository: QuestionRepository) {

    suspend fun invoke():List<Question> = questionRepository.getQuestions()

}