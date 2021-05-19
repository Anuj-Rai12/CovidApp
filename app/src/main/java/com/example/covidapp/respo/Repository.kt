package com.example.covidapp.respo

import androidx.room.withTransaction
import com.example.covidapp.api.ApiServices
import com.example.covidapp.roomdb.NewsDataBaseInstance
import com.example.utils.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: ApiServices,
    private val db: NewsDataBaseInstance
) {
    private val newsDao = db.getDao()
    fun newsBoundResources() = networkBoundResource(
        query = {
            newsDao.getAllNews()
        },
        fetch = {
            delay(2000)
            api.getNewsData("covid").articles
        },
        saveFetchResult = { articles ->
            db.withTransaction {
                newsDao.deleteAllData()
                newsDao.insertAllNews(articles)
            }
        },
        shouldFetch = {
            true
        }
    )
}