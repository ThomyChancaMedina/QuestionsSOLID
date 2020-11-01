package com.architectcoders.grupo2verano2020.data.server.provider

import com.architectcoders.domain.test.TestQuestion
import com.architectcoders.grupo2verano2020.data.database.Test.TestQuestion as DomainTest
import com.architectcoders.grupo2verano2020.data.server.model.TestQuestion as ProviderTest

fun TestQuestion.toRoomTest(): DomainTest =
    DomainTest(
        uniqueId,
        text,
        pos,
        answer
    )


fun DomainTest.toDomainTest(): TestQuestion =
    TestQuestion(
        uniqueId,
        text,
        pos,
        answer,
    )

fun ProviderTest.toDomainTest(): TestQuestion =
    TestQuestion(
        0,
        text,
        pos,
        answer,
    )
