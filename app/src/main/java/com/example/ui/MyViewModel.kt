package com.example.ui

import androidx.lifecycle.*
import com.example.covidapp.api.ApiServices
import com.example.covidapp.datamodel.newsmodel.Root
import com.example.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor
    (private val apiServices: ApiServices) : ViewModel() {

    private val getMsgLiveData = MutableLiveData<Event<String>>()
    val getMsgLiveDataNow: LiveData<Event<String>>
        get() = getMsgLiveData
    private val datas = MutableLiveData<Root>()
    val getall: LiveData<Root>
        get() = datas

   init {
       viewModelScope.launch {
           val co=apiServices.getNewsData("covid")
           datas.value=co
       }
   }

}