package com.architectcoders.grupo2verano2020.data.server


import com.architectcoders.grupo2verano2020.data.server.model.Questions
import retrofit2.http.GET

interface TheQuestionDbService {
    @GET("Questions/Jwf0Y4VAFsX0m8hBRP6f/questions.json")
    suspend fun listQuestionAsync(): List<Questions>

}