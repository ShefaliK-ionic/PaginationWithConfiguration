package com.example.mytask.di

import com.example.mytask.network.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofileModule {

    @Provides
    fun getRetrofit():Retrofit=Retrofit.Builder().baseUrl("https://dummyjson.com/")
        .addConverterFactory(GsonConverterFactory.create()).build()


    @Provides
    fun getApiInterface(retrofit: Retrofit):ApiInterface=retrofit.create(ApiInterface::class.java)


}