package com.example.covidapp.roomdb.stateroomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.covidapp.datamodel.statemodel.Statewise
import kotlinx.coroutines.flow.Flow

@Dao
interface StateDao {
    @Query("select * from StateCovidResult")
    fun selectAllState(): Flow<List<Statewise>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllState(stateWise: List<Statewise>)

    @Query("delete from StateCovidResult")
    suspend fun deleteAllState()
}