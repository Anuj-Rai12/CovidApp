package com.example.ui

import androidx.lifecycle.*
import com.example.covidapp.api.ApiServices
import com.example.covidapp.datamodel.newsmodel.Root
import com.example.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
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
        try {
            val call = apiServices.getNewsData(q = "covid")
            call.enqueue(object : Callback<Root> {
                override fun onResponse(call: Call<Root>, response: Response<Root>) {
                    if (response.isSuccessful) {
                        val op = response.body()
                        if (op != null) {
                            datas.value = op!!
                        }
                    } else {
                        getMsgLiveData.value = Event("${response.message()}")
                    }
                }

                override fun onFailure(call: Call<Root>, t: Throwable) {
                    getMsgLiveData.value = Event("${t.message}")
                }

            })
        } catch (e: IOException) {
            getMsgLiveData.value = Event("Error ${e.message}")
        }

    }

}