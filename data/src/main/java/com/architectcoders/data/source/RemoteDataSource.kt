package com.architectcoders.data.source

import com.architectcoders.domain.Question


interface RemoteDataSource {
    suspend fun getAllQuestions():List<Question>
}