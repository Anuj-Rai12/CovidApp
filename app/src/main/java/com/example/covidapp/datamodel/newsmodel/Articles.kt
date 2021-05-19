package com.example.covidapp.datamodel.newsmodel

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "News_Articles")
data class Articles(
    @Embedded
    val source: Source,
    val author: String?,
    @PrimaryKey
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)