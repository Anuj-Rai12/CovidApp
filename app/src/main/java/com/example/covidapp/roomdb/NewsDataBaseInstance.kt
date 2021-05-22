package com.example.covidapp.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.covidapp.datamodel.newsmodel.Articles
import com.example.covidapp.datamodel.statemodel.Statewise
import com.example.covidapp.roomdb.stateroomdb.StateDao

@Database(entities = [Articles::class, Statewise::class], version = 1, exportSchema = false)
abstract class NewsDataBaseInstance : RoomDatabase() {
    abstract fun getDao(): NewsDao
    abstract fun getStateDao(): StateDao

    companion object {
        const val DatabaseName = "MY_News_Table"
    }

}