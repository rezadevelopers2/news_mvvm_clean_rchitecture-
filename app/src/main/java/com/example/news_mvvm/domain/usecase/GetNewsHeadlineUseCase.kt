package com.example.news_mvvm.domain.usecase

import com.example.news_mvvm.data.model.ApiResponse
import com.example.news_mvvm.data.util.Resource
import com.example.news_mvvm.domain.repository.NewsRepository

class GetNewsHeadlineUseCase (private val newsRepository: NewsRepository) {

    suspend fun execute(country:String ,page:Int):Resource<ApiResponse>{
        return  newsRepository.getNewsHeadline(country, page)
    }
}