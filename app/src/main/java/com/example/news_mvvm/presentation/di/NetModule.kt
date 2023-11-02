package com.example.news_mvvm.presentation.di

import com.example.news_mvvm.BuildConfig
import com.example.news_mvvm.data.api.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Singleton
    @Provides
    fun privderRetrofite():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.MY_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun  providerNewsApiService(retrofit: Retrofit):NewsApiService{
        return retrofit.create(NewsApiService::class.java)
    }
}