package com.example.news_mvvm.presentation.di

import android.app.Application
import androidx.room.Room
import com.example.news_mvvm.data.db.ArticleDao
import com.example.news_mvvm.data.db.ArticleDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {


    @Singleton
    @Provides
    fun providerNewsDataBase(app:Application):ArticleDataBase{
        return Room.databaseBuilder(app,ArticleDataBase::class.java,"news_db")
            .fallbackToDestructiveMigration() .build()
    }

    @Singleton
    @Provides
    fun providerNewsDao(articleDataBase: ArticleDataBase):ArticleDao{
        return articleDataBase.getArticleAO()
    }
}