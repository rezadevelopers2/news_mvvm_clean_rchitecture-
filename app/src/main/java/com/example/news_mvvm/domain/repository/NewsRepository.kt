package com.example.news_mvvm.domain.repository

import com.example.news_mvvm.data.model.ApiResponse
import com.example.news_mvvm.data.model.Article
import com.example.news_mvvm.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNewsHeadline(country:String ,page:Int): Resource<ApiResponse>
    suspend fun getSearchedNews(country:String ,page:Int,searchQuery: String): Resource<ApiResponse>
    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    suspend fun getSaveNews():Flow<List<Article>>
}