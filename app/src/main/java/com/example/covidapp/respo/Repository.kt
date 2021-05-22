package com.example.covidapp.respo

import androidx.room.withTransaction
import com.example.covidapp.api.ApiServices
import com.example.covidapp.api.GlobalApiService
import com.example.covidapp.api.StateApiService
import com.example.covidapp.roomdb.NewsDataBaseInstance
import com.example.utils.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: ApiServices,
    private val stateApi: StateApiService,
    private val globalApi: GlobalApiService,
    private val db: NewsDataBaseInstance
) {
    private val newsDao = db.getDao()
    private val stateDao = db.getStateDao()
    private val globalDao = db.getGlobalDao()

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

    fun newStateBoundResource() = networkBoundResource(
        query = {
            stateDao.selectAllState()
        },
        fetch = {
            delay(1000)
            stateApi.getStateData().statewise
        },
        saveFetchResult = { statewise ->
            db.withTransaction {
                stateDao.deleteAllState()
                stateDao.insertAllState(stateWise = statewise)
            }
        },
        shouldFetch = {
            true
        }
    )

    fun newGlobalResource() = networkBoundResource(
        query = {
            globalDao.getAllGlobal()
        },
        fetch = {
             globalApi.getGlobalData()
        },
        saveFetchResult = {
            db.withTransaction {
                globalDao.deleteAllGlobal()
                globalDao.insertGlobal(it)
            }
        },
        shouldFetch = {
            true
        }
    )
}
