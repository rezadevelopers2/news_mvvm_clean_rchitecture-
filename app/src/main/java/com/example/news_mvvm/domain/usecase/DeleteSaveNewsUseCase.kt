package com.example.news_mvvm.domain.usecase

import com.example.news_mvvm.data.model.Article
import com.example.news_mvvm.domain.repository.NewsRepository

class DeleteSaveNewsUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(article: Article) = newsRepository.deleteNews(article)
}