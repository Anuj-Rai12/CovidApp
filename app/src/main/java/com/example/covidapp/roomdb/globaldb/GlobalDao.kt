package com.example.covidapp.roomdb.globaldb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.covidapp.datamodel.gloablmodel.GloablCountryDataItem
import kotlinx.coroutines.flow.Flow

@Dao
interface GlobalDao {
    @Query("select * from Global_Country_data")
    fun getAllGlobal(): Flow<List<GloablCountryDataItem>>

    @Insert
    suspend fun insertGlobal(globalCountryData: List<GloablCountryDataItem>)

    @Query("delete from Global_Country_data")
    suspend fun deleteAllGlobal()
}