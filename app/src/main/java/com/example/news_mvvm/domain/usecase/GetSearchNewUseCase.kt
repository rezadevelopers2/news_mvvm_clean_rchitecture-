package com.example.news_mvvm.domain.usecase

import com.example.news_mvvm.data.model.ApiResponse
import com.example.news_mvvm.data.util.Resource
import com.example.news_mvvm.domain.repository.NewsRepository

class GetSearchNewUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(country:String ,page:Int,searchQuery : String): Resource<ApiResponse> {
        return  newsRepository.getSearchedNews(country,page,searchQuery)
    }
}