package com.architectcoders.domain

data class Question(
    val id: String,
    val question: String,
    val answers: List<Answer>
)