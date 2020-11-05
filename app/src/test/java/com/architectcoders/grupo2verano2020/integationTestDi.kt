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
@Component(modules = [
    TestAppModule::class,
    DataModule::class
])
interface TestProjectComponent : ProjectComponent {

    val testLocalSource: TestLocalSource
    val testDataSource:TestDataSource

    @Component.Factory
    interface FactoryTest {
        fun create(): TestProjectComponent
    }
}


@Module
class TestAppModule {

    @Provides
    @Singleton
    fun testLocalSourceProvider(): TestLocalSource = FakeTestLocalSource()

    @Provides
    @Singleton
    fun testDataSourceProvider(): TestDataSource = FakeTestDataSource()

}

class FakeTestLocalSource : TestLocalSource {

    var testQuestionT: List<TestQuestion> = emptyList()

    override suspend fun isEmpty() = testQuestionT.isEmpty()

    override suspend fun saveTests(testQuestion: List<TestQuestion>) {
        this.testQuestionT = testQuestion
    }

    override suspend fun getTests(): List<TestQuestion> = testQuestionT

    override suspend fun findById(id: Int): TestQuestion = testQuestionT.first { it.uniqueId == id }

    override suspend fun update(testQuestion: TestQuestion) {
        testQuestionT =
            testQuestionT.filterNot { it.uniqueId == testQuestion.uniqueId } + testQuestion
    }

}

class FakeTestDataSource : TestDataSource {
    var tests = defaultFakeTest
    override suspend fun getAllTest(): List<TestQuestion> = tests

}

val defaultFakeTest = listOf(
    mockedTest.copy(1),
    mockedTest.copy(2),
    mockedTest.copy(3),
    mockedTest.copy(4),
)