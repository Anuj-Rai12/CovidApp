package com.example.covidapp.di

import android.app.Application
import androidx.room.Room
import com.example.covidapp.api.ApiServices
import com.example.covidapp.roomdb.NewsDataBaseInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun getRetrofit(): ApiServices =
        Retrofit.Builder()
            .baseUrl(ApiServices.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServices::class.java)


    @Provides
    @Singleton
    fun getDataBaseInstance(app: Application) = Room.databaseBuilder(
        app,
        NewsDataBaseInstance::class.java,
        NewsDataBaseInstance.DatabaseName
    ).build()
}