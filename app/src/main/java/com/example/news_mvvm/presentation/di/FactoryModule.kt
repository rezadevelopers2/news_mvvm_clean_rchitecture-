package com.example.news_mvvm.presentation.di

import android.app.Application
import com.example.news_mvvm.domain.usecase.DeleteSaveNewsUseCase
import com.example.news_mvvm.domain.usecase.GetNewsHeadlineUseCase
import com.example.news_mvvm.domain.usecase.GetSaveNewsUseCase
import com.example.news_mvvm.domain.usecase.GetSearchNewUseCase
import com.example.news_mvvm.domain.usecase.SaveNewsUseCase
import com.example.news_mvvm.presentation.viewModel.NewViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun providerViewModelFactory(
        app:Application,
        getNewsHeadlineUseCase: GetNewsHeadlineUseCase,
        getSearchNewUseCase: GetSearchNewUseCase,
         saveNewsUseCase: SaveNewsUseCase,
        getSaveNewsUseCase: GetSaveNewsUseCase,
        deleteSaveNewsUseCase: DeleteSaveNewsUseCase
    ):NewViewModelFactory{
        return  NewViewModelFactory(app, getNewsHeadlineUseCase,getSearchNewUseCase,saveNewsUseCase,getSaveNewsUseCase,deleteSaveNewsUseCase)
    }
}