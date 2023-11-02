package com.example.news_mvvm.presentation.di

import com.example.news_mvvm.domain.repository.NewsRepository
import com.example.news_mvvm.domain.usecase.DeleteSaveNewsUseCase
import com.example.news_mvvm.domain.usecase.GetNewsHeadlineUseCase
import com.example.news_mvvm.domain.usecase.GetSaveNewsUseCase
import com.example.news_mvvm.domain.usecase.GetSearchNewUseCase
import com.example.news_mvvm.domain.usecase.SaveNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun providerNewsHeadlineUseCase(
        newsRepository: NewsRepository
    ):GetNewsHeadlineUseCase{
        return GetNewsHeadlineUseCase(newsRepository)
    }


    @Singleton
    @Provides
    fun providerGetSearchNewsUseCase(
        newsRepository: NewsRepository
    ):GetSearchNewUseCase{
        return GetSearchNewUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun providerSaveNewsUseCase(
        newsRepository: NewsRepository
    ):SaveNewsUseCase{
        return SaveNewsUseCase(newsRepository)
    }


    @Singleton
    @Provides
    fun providerGetSaveNewsUseCase(
        newsRepository: NewsRepository
    ):GetSaveNewsUseCase{
        return GetSaveNewsUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun providerDeleteNewsUseCase(
        newsRepository: NewsRepository
    ):DeleteSaveNewsUseCase{
        return DeleteSaveNewsUseCase(newsRepository)
    }
}