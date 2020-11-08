package com.architectcoders.grupo2verano2020

import com.architectcoders.data.source.LocalDataSource
import com.architectcoders.data.source.RemoteDataSource
import com.architectcoders.domain.question.Question
import com.architectcoders.grupo2verano2020.data.di.ProjectComponent
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(
    modules = [

    ]
)
interface QuestionUserComponent:ProjectComponent{

    val localDataSource: LocalDataSource
    val remoteDataSource: RemoteDataSource

    @Component.Factory
    interface FactoryTest{
        fun create():QuestionUserComponent
    }
}

@Module
class QuestionAppModule{

    @Provides
    @Singleton
    fun questionLocalSourceProvider():LocalDataSource = FakeQuestionLocalSource()

    @Provides
    @Singleton
    fun questionRemoteDataSourceProvider():RemoteDataSource = FakeQuestionRemoteDataSource()

}

class FakeQuestionLocalSource : LocalDataSource {

    var question:List<Question> = emptyList()

    override suspend fun isEmpty() = question.isEmpty()

    override suspend fun saveQuestion(question: List<Question>) {
      this.question = question
    }

    override suspend fun getQuestions(): List<Question> = question

    override suspend fun findById(id: String): Question = question.first { it.id==id }


    override suspend fun update(question: Question) {
      this.question = this.question.filterNot { it.id == question.id }+ question
    }

}


class FakeQuestionRemoteDataSource : RemoteDataSource {



    override suspend fun getAllQuestions(): List<Question> {
        TODO("Not yet implemented")
    }

}

