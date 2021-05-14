package com.example.ui

import androidx.lifecycle.*
import com.example.covidapp.api.ApiServices
import com.example.covidapp.datamodel.newsmodel.Root
import com.example.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor
    (private val apiServices: ApiServices) : ViewModel() {

    private val getMsgLiveData = MutableLiveData<Event<String>>()
    val getMsgLiveDataNow: LiveData<Event<String>>
        get() = getMsgLiveData

    fun getResponse(): LiveData<Call<Root>> {
        return liveData {
            try {
                val call = apiServices.getNewsData(q = "covid")
                emit(call)
            } catch (e: IOException) {
                getMsgLiveData.value = Event("Error ${e.message}")
            }
        }
    }
}