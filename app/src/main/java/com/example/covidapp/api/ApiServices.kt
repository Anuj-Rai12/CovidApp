package com.example.covidapp.api


import com.example.covidapp.datamodel.newsmodel.Root
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    companion object {
        const val BASE_URL = "https://newsapi.org"
    }

    @GET("/v2/top-headlines")
    fun getNewsData(
        @Query("q") q: String,
        @Query("country") ind: String = "in",
        @Query("apiKey") key: String = "d03377d5e0704038ab5469f44f206f64",
    ):Call<Root>
}