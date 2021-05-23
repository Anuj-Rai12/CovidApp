package com.example.covidapp.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.covidapp.datamodel.gloablmodel.GloablCountryDataItem
import com.example.covidapp.datamodel.newsmodel.Articles
import com.example.covidapp.datamodel.statemodel.Statewise
import com.example.covidapp.datamodel.statemodel.Tested
import com.example.covidapp.roomdb.globaldb.GlobalDao
import com.example.covidapp.roomdb.stateroomdb.StateDao
import com.example.covidapp.roomdb.testingdb.TestingDao

@Database(
    entities = [Articles::class, Statewise::class, GloablCountryDataItem::class, Tested::class],
    version = 2,
    exportSchema = false
)
abstract class NewsDataBaseInstance : RoomDatabase() {
    abstract fun getDao(): NewsDao
    abstract fun getStateDao(): StateDao
    abstract fun getGlobalDao(): GlobalDao
    abstract fun getTestingDao(): TestingDao

    companion object {
        const val DatabaseName = "MY_News_Table"
    }

}