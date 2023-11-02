package com.example.news_mvvm.presentation.di

import com.example.news_mvvm.data.api.NewsApiService
import com.example.news_mvvm.data.repository.dataSource.NewsRemoteDataSource
import com.example.news_mvvm.data.repository.dataSourceImpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun providerNewsRemoteDataSource(newsApiService: NewsApiService):NewsRemoteDataSource{
        return NewsRemoteDataSourceImpl(newsApiService)
    }
}