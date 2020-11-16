package com.architectcoders.grupo2verano2020.data.database.Test

import com.architectcoders.data.testSource.TestLocalSource
import com.architectcoders.domain.test.TestQuestion
import com.architectcoders.grupo2verano2020.data.database.ProjectDatabase
import com.architectcoders.grupo2verano2020.data.server.provider.toDomainTest
import com.architectcoders.grupo2verano2020.data.server.provider.toRoomTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomTestDataSource(db: ProjectDatabase) : TestLocalSource {

    private val testDao = db.testDao()

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { testDao.testCount() <= 0 }

    override suspend fun saveTests(testQuestion: List<TestQuestion>) =
        withContext(Dispatchers.IO) { testDao.insertTest(testQuestion.map { it.toRoomTest() }) }

    override suspend fun getTests(): List<TestQuestion> =
        withContext(Dispatchers.IO) { testDao.getAllTest().map { it.toDomainTest() } }

    override suspend fun findById(id: Int): TestQuestion =
        withContext(Dispatchers.IO) { testDao.findById(id).toDomainTest() }

    override suspend fun update(testQuestion: TestQuestion) =
        withContext(Dispatchers.IO) { testDao.updateTest(testQuestion.toRoomTest()) }

}