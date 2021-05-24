package com.example.covidapp.di

import android.app.Application
import androidx.room.Room
import com.example.covidapp.api.ApiServices
import com.example.covidapp.api.GlobalApiService
import com.example.covidapp.api.StateApiService
import com.example.covidapp.roomdb.NewsDataBaseInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    private val client = OkHttpClient.Builder().apply {
        this.connectTimeout(30, TimeUnit.SECONDS)
        this.readTimeout(20, TimeUnit.SECONDS)
        this.writeTimeout(20, TimeUnit.SECONDS)
    }.build()

    @Provides
    @Singleton
    fun getRetrofit(): ApiServices =
        Retrofit.Builder()
            .baseUrl(ApiServices.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServices::class.java)


    @Provides
    @Singleton
    fun getDataBaseInstance(app: Application) = Room.databaseBuilder(
        app,
        NewsDataBaseInstance::class.java,
        NewsDataBaseInstance.DatabaseName
    ).fallbackToDestructiveMigration().build()
    //Api State

    @Provides
    @Singleton
    fun getStateRetrofit(): StateApiService =
        Retrofit.Builder()
            .baseUrl(StateApiService.Base_Url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StateApiService::class.java)

    //Api Global
    @Provides
    @Singleton
    fun getGlobalRetrofit(): GlobalApiService =
        Retrofit.Builder()
            .baseUrl(GlobalApiService.BaseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GlobalApiService::class.java)
}