package com.example.somoni.data.apiservice

import com.example.somoni.extensions.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Api {
    private val retrofitClient:Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    @Singleton
    @Provides
    fun getRetrofitRequest() : Gets = retrofitClient.create(Gets::class.java)
}