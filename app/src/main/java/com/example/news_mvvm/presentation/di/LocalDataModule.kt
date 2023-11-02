package com.example.news_mvvm.presentation.di

import com.example.news_mvvm.data.db.ArticleDao
import com.example.news_mvvm.data.repository.NewsLocalDataSourceImpl
import com.example.news_mvvm.data.repository.dataSource.NewsLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun providerLocalDataSource(articleDao: ArticleDao):NewsLocalDataSource{
        return NewsLocalDataSourceImpl(articleDao)
    }
}