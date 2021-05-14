package com.example.covidapp.api


import com.example.covidapp.datamodel.newsmodel.Root
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiServices {

    companion object {
        const val BASE_URL = "https://newsapi.org"
    }

    @Headers("X-Api-Key: d03377d5e0704038ab5469f44f206f64")
    @GET("/v2/top-headlines")
     fun getNewsData(
        @Query("q") q: String,
        @Query("country") ind: String = "in",
    ): Call<Root>
}