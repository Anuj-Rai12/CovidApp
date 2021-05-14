package com.example.covidapp.datamodel.newsmodel


data class Root(
    val status: String,
    val totalResults: Long,
    val articles: ArrayList<Articles>
)


