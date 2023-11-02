package com.example.news_mvvm.presentation.di

import com.example.news_mvvm.presentation.adapter.NewsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NewsAdapterModule {

    @Singleton
    @Provides
    fun providerNewsAdapter():NewsAdapter{
        return NewsAdapter()
    }
}