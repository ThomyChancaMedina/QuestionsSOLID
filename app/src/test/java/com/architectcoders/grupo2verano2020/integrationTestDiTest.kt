package com.architectcoders.grupo2verano2020

import com.architectcoders.data.testSource.TestDataSource
import com.architectcoders.data.testSource.TestLocalSource
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
    TestUserAppModule::class,
    DataModule::class
    ]
)
interface TestUserComponent : ProjectComponent {

    val testLocalSource: TestLocalSource
    val testDataSource: TestDataSource

    @Component.Factory
    interface FactoryTest {
        fun create(): TestUserComponent
    }

}

@Module
class TestUserAppModule {

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
    mockedTest.copy(2),
    mockedTest.copy(3),
    mockedTest.copy(4),
)
