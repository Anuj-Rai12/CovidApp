package com.example.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covidapp.api.ApiServices
import com.example.covidapp.datamodel.newsmodel.Articles
import com.example.covidapp.datamodel.newsmodel.Root
import com.example.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor
    (private val apiServices: ApiServices) : ViewModel() {


    private val getMsgLiveData = MutableLiveData<Event<String>>()
    val getMsgLiveDataNow: LiveData<Event<String>>
        get() = getMsgLiveData
    private val dataS = MutableLiveData<Root>()
    val getAll: LiveData<Root>
        get() = dataS

    init {
        viewModelScope.launch {
            val co = apiServices.getNewsData("covid")
            dataS.value = co
        }
    }

    private val articlesData = MutableLiveData<Articles>()
    val getAllArticles: LiveData<Articles>
        get() = articlesData

    fun getItemTo(articles: Articles) {
        articlesData.value = articles
    }
}