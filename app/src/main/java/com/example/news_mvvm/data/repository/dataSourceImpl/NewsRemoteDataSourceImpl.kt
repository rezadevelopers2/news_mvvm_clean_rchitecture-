package com.example.news_mvvm.data.repository.dataSourceImpl

import com.example.news_mvvm.data.api.NewsApiService
import com.example.news_mvvm.data.model.ApiResponse
import com.example.news_mvvm.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl (
    private val newsApiService: NewsApiService,

): NewsRemoteDataSource {


    override suspend fun getTopHeadline(country:String ,page:Int): Response<ApiResponse> {
        return newsApiService.getTopHeadlines(country,page)
    }

    override suspend fun getSearchTopHeadline(
        country: String,
        page: Int,
        searchQuery: String
    ): Response<ApiResponse> {
        return newsApiService.getSearchTopHeadlines(country,page, searchQuery )
    }
}