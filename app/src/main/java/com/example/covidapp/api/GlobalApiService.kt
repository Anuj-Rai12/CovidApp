package com.example.covidapp.api

import com.example.covidapp.datamodel.gloablmodel.GloablCountryData
import retrofit2.http.GET

interface GlobalApiService {
    companion object {
        const val BaseUrl = "https://disease.sh"
    }

    @GET("/v3/covid-19/countries")
    suspend fun getGlobalData(): GloablCountryData
}