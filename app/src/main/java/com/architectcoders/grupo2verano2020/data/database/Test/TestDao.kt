package com.architectcoders.grupo2verano2020.data.database.Test

import androidx.room.*

@Dao
interface TestDao {
    @Query("SELECT * FROM TestQuestion")
    fun getAllTest():List<TestQuestion>

    @Query("SELECT * FROM TestQuestion WHERE uniqueId=:id")
    fun findById(id:Int):TestQuestion

    @Query("SELECT COUNT(uniqueId) FROM TestQuestion ")
    fun testCount():Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTest(test:List<TestQuestion>)

    @Update
    fun updateTest(test:TestQuestion)
}