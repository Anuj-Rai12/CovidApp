package com.example.covidapp.api

import com.example.covidapp.datamodel.statemodel.StatecovidCase
import retrofit2.http.GET

interface StateApiService {
    companion object {
        const val Base_Url = "https://api.covid19india.org"
    }

    @GET("/data.json")
    suspend fun getStateData(): StatecovidCase
}