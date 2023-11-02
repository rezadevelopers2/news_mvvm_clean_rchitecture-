package com.example.news_mvvm.data.repository.dataSource

import com.example.news_mvvm.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {

    suspend fun saveArticleToDB(article: Article)
    suspend fun deleteArticleToDB(article: Article)
    fun  getSaveAllArticle(): Flow<List<Article>>
}