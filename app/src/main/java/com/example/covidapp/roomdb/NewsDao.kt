package com.example.covidapp.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.covidapp.datamodel.newsmodel.Articles
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Query("Select * from News_Articles")
    fun getAllNews(): Flow<List<Articles>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllNews(articles: List<Articles>)

    @Query("delete from News_Articles")
    suspend fun deleteAllData()
}