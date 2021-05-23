package com.example.covidapp.roomdb.testingdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.covidapp.datamodel.statemodel.Tested
import kotlinx.coroutines.flow.Flow

@Dao
interface TestingDao {
    @Query("select * from Testing_Table")
    fun getAllTesting(): Flow<Tested>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTested(tested: Tested)

    @Query("delete from Testing_Table")
    suspend fun deleteAllTested()
}