package com.example.covidapp.di

import com.example.covidapp.api.ApiServices
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

/*    private val MyMoshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()!!*/

    /*@Provides
    @Singleton
    fun getRetrofit() = Retrofit
        .Builder()
        .baseUrl(ApiServices.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(MyMoshi))
        .build()
        .create(ApiServices::class.java)!!*/

    /*private val myMoshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()!!*/

    @Provides
    @Singleton
    fun getRetrofito() =
        Retrofit.Builder()
            .baseUrl(ApiServices.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServices::class.java)

    /*private fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(ApiServices.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun newApis(): ApiServices = getRetrofit().create(ApiServices::class.java)*/
}