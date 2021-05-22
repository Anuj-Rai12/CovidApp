package com.example.covidapp.datamodel.gloablmodel


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Global_Country_data")
data class GloablCountryDataItem(

    @SerializedName("updated")
    @PrimaryKey
    val updated: Long,

    @SerializedName("countryInfo")
    @Embedded
    val countryInfo: CountryInfo,

    @SerializedName("active") val active: Int,
    @SerializedName("cases") val cases: Int,
    @SerializedName("deaths") val deaths: Int,
    @SerializedName("recovered") val recovered: Int,

    @SerializedName("critical") val critical: Int,
    @SerializedName("todayCases") val todayCases: Int,
    @SerializedName("todayDeaths") val todayDeaths: Int,
    @SerializedName("todayRecovered") val todayRecovered: Int,

    @SerializedName("country") val country: String
)