package com.example.news_mvvm.domain.usecase

import com.example.news_mvvm.data.model.Article
import com.example.news_mvvm.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSaveNewsUseCase (private val newsRepository: NewsRepository){
    suspend fun execute(): Flow<List<Article>>{
        return newsRepository.getSaveNews()
    }
}