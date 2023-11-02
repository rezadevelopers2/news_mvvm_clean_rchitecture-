package com.example.news_mvvm.data.repository.dataSource

import com.example.news_mvvm.data.model.ApiResponse
import retrofit2.Response
interface NewsRemoteDataSource {

    suspend fun getTopHeadline(country:String ,page:Int):Response<ApiResponse>
    suspend fun getSearchTopHeadline(country:String ,page:Int,searchQuery:String):Response<ApiResponse>
}