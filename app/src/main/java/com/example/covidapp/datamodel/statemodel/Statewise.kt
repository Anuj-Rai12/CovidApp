package com.example.covidapp.datamodel.statemodel


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "StateCovidResult")
data class Statewise(
    val active: String,
    val confirmed: String,
    val deaths: String,
    val deltaconfirmed: String,
    val deltadeaths: String,
    val deltarecovered: String,
    @PrimaryKey
    val lastupdatedtime: String,
    val migratedother: String,
    val recovered: String,
    val state: String,
    val statecode: String,
    val statenotes: String
)