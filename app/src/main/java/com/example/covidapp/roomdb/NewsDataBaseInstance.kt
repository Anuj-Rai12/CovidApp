package com.example.covidapp.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.covidapp.datamodel.newsmodel.Articles

@Database(entities = [Articles::class], version = 1, exportSchema = false)
abstract class NewsDataBaseInstance : RoomDatabase() {
    abstract fun getDao(): NewsDao

    companion object {
        const val DatabaseName = "MY_News_Table"
    }

}