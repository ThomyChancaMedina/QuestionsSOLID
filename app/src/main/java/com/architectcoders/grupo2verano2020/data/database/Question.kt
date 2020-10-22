package com.architectcoders.grupo2verano2020.data.database



import androidx.room.Entity
import androidx.room.PrimaryKey
import com.architectcoders.domain.Answer



@Entity
data class QuestionsAnswers(
    @PrimaryKey val id: String,
    val question: String,
    val answer: List<Answer>
)

