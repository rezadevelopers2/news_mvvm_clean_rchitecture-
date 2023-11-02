package com.example.news_mvvm.data.repository

import com.example.news_mvvm.data.db.ArticleDao
import com.example.news_mvvm.data.model.Article
import com.example.news_mvvm.data.repository.dataSource.NewsLocalDataSource
import kotlinx.coroutines.flow.Flow

class NewsLocalDataSourceImpl(
    private val articleDao: ArticleDao
) :NewsLocalDataSource {

    override suspend fun saveArticleToDB(article: Article) {
       return articleDao.insert(article)
    }

    override suspend fun deleteArticleToDB(article: Article) {
        articleDao.deleteArticle(article)
    }

    override fun getSaveAllArticle(): Flow<List<Article>> {
        return articleDao.getAllArticle()
    }
}