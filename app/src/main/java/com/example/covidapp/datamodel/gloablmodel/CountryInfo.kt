package com.example.covidapp.datamodel.gloablmodel


import com.google.gson.annotations.SerializedName

data class CountryInfo(
    @SerializedName("flag") val flag: String,
    @SerializedName("_id") val id: Int,
)