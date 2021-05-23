package com.example.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.covidapp.datamodel.gloablmodel.GloablCountryDataItem
import com.example.covidapp.datamodel.newsmodel.Articles
import com.example.covidapp.datamodel.statemodel.Statewise
import com.example.covidapp.datamodel.statemodel.Tested
import com.example.covidapp.respo.Repository
import com.example.utils.Event
import com.example.utils.MySealed
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor
    (private val repository: Repository) : ViewModel() {


    var setSourceUrl: String? = null
    var setSourceSecUrl: String? = null
    var getUrl: String? = null
    private val getMsgLiveData = MutableLiveData<Event<String>>()
    val getMsgLiveDataNow: LiveData<Event<String>>
        get() = getMsgLiveData

    private var data = repository.newsBoundResources()
    private var dataState = repository.newStateBoundResource()
    private var dataGlobal = repository.newGlobalResource()
    private var dataTesting = repository.newStateSaved()

    var articlesDataS: LiveData<MySealed<List<Articles>>>? = null
    var stateDataS: LiveData<MySealed<List<Statewise>>>? = null
    var globalDataS: LiveData<MySealed<List<GloablCountryDataItem>>>? = null
    var testingDataS: LiveData<MySealed<Tested>>? = null

    init {
        val articlesDat = data.asLiveData()
        articlesDataS = articlesDat
        stateDataS = dataState.asLiveData()
        globalDataS = dataGlobal.asLiveData()
        testingDataS = dataTesting.asLiveData()
    }

    fun retry() {
        articlesDataS = null
        articlesDataS = repository.newsBoundResources().asLiveData()
    }

    fun testingRetry() {
        testingDataS = null
        testingDataS = repository.newStateSaved().asLiveData()
    }

    fun retryState() {
        stateDataS = null
        stateDataS = repository.newStateBoundResource().asLiveData()
    }

    fun retryGlobal() {
        globalDataS = null
        globalDataS = repository.newGlobalResource().asLiveData()
    }

    private var articlesData = MutableLiveData<Articles>()
    val getAllArticles: LiveData<Articles>
        get() = articlesData

    private var stateData = MutableLiveData<Statewise>()
    val getAllstateData: LiveData<Statewise>
        get() = stateData

    private var globalData = MutableLiveData<GloablCountryDataItem>()
    val getAllGlobalData: LiveData<GloablCountryDataItem>
        get() = globalData

    fun getItemTo(articles: Articles) {
        articlesData.value = articles
    }

    fun getItemState(stateWise: Statewise) {
        stateData.value = stateWise
    }

    fun getItemGlobal(globalCountryDataItem: GloablCountryDataItem) {
        globalData.value = globalCountryDataItem
    }
}