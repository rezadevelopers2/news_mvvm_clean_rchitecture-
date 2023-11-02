package com.example.news_mvvm.data.api

import com.example.news_mvvm.data.model.ApiResponse
import com.example.news_mvvm.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country:String ,
        @Query("page") page:Int ,
        @Query("apiKey") apiKey : String = BuildConfig.MY_KEY,
    ):Response<ApiResponse>


    @GET("v2/top-headlines")
    suspend fun getSearchTopHeadlines(
        @Query("country") country:String ,
        @Query("page") page:Int ,
        @Query("q") search:String ,
        @Query("apiKey") apiKey : String = BuildConfig.MY_KEY,
    ):Response<ApiResponse>
}