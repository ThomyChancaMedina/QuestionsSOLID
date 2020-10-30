package com.architectcoders.grupo2verano2020.data.database.Test

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TestQuestion(
    @PrimaryKey(autoGenerate = true)
    val uniqueId:Int,
    val text:String,
    val pos:Int,
    val answer:String,
)