package com.example.news_mvvm.presentation.di

import com.example.news_mvvm.data.repository.NewsRepositoryImpl
import com.example.news_mvvm.data.repository.dataSource.NewsLocalDataSource
import com.example.news_mvvm.data.repository.dataSource.NewsRemoteDataSource
import com.example.news_mvvm.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {


    @Singleton
    @Provides
    fun providerNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource,
        newsLocalDataSource: NewsLocalDataSource
    ):NewsRepository{
        return  NewsRepositoryImpl(newsRemoteDataSource,newsLocalDataSource)
    }

}