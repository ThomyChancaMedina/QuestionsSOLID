package com.architectcoders.grupo2verano2020

import com.architectcoders.data.source.LocalDataSource
import com.architectcoders.data.source.RemoteDataSource
import com.architectcoders.data.testSource.TestDataSource
import com.architectcoders.data.testSource.TestLocalSource
import com.architectcoders.domain.question.Question
import com.architectcoders.domain.test.TestQuestion
import com.architectcoders.grupo2verano2020.data.di.DataModule
import com.architectcoders.grupo2verano2020.data.di.ProjectComponent
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        QuestionAppModule::class,
        DataModule::class
    ]
)
interface TestComponent : ProjectComponent {

    val localDataSource: LocalDataSource
    val remoteDataSource: RemoteDataSource
    val testLocalSource: TestLocalSource
    val testDataSource: TestDataSource


    @Component.Factory
    interface FactoryTest {
        fun create(): TestComponent
    }
}

@Module
class QuestionAppModule {

    @Provides
    @Singleton
    fun questionLocalSourceProvider(): LocalDataSource = FakeQuestionLocalSource()

    @Provides
    @Singleton
    fun questionRemoteDataSourceProvider(): RemoteDataSource = FakeQuestionRemoteDataSource()


    @Provides
    @Singleton
    fun testLocalSourceProvider(): TestLocalSource = FakeTestLocalSource()

    @Provides
    @Singleton
    fun testTestDataSourceProvider(): TestDataSource = FakeTestDataSource()


}

class FakeTestLocalSource : TestLocalSource {

    var testQuestion: List<TestQuestion> = emptyList()

    override suspend fun isEmpty() = testQuestion.isEmpty()

    override suspend fun saveTests(testQuestion: List<TestQuestion>) {
        this.testQuestion = testQuestion
    }

    override suspend fun getTests(): List<TestQuestion> = testQuestion

    override suspend fun findById(id: Int): TestQuestion = testQuestion.first { it.uniqueId == id }

    override suspend fun update(testQuestion: TestQuestion) {
        this.testQuestion =
            this.testQuestion.filterNot { it.uniqueId == testQuestion.uniqueId } + testQuestion
    }

}

class FakeTestDataSource : TestDataSource {
    var testQuestions = defaultFakeTest

    override suspend fun getAllTest() = testQuestions

}

val defaultFakeTest = listOf(
    mockedTest.copy(1),

)


class FakeQuestionLocalSource : LocalDataSource {

    var question: List<Question> = emptyList()

    override suspend fun isEmpty() = question.isEmpty()

    override suspend fun saveQuestion(question: List<Question>) {
        this.question = question
    }

    override suspend fun getQuestions(): List<Question> = question

    override suspend fun findById(id: String): Question = question.first { it.id == id }


    override suspend fun update(question: Question) {
        this.question = this.question.filterNot { it.id == question.id } + question
    }

}


class FakeQuestionRemoteDataSource : RemoteDataSource {


    override suspend fun getAllQuestions(): List<Question> {
        TODO("Not yet implemented")
    }

}

