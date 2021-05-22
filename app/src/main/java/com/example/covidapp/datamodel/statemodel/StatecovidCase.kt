package com.example.covidapp.datamodel.statemodel


import com.google.gson.annotations.SerializedName

data class StatecovidCase(
    @SerializedName("statewise") val statewise: List<Statewise>,
    @SerializedName("tested") val tested: List<Tested>
)