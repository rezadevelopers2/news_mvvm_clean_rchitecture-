package com.example.news_mvvm.data.repository

import com.example.news_mvvm.data.model.ApiResponse
import com.example.news_mvvm.data.model.Article
import com.example.news_mvvm.data.repository.dataSource.NewsLocalDataSource
import com.example.news_mvvm.data.repository.dataSource.NewsRemoteDataSource
import com.example.news_mvvm.data.util.Resource
import com.example.news_mvvm.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl (
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
):NewsRepository{

    override suspend fun getNewsHeadline(country:String ,page:Int): Resource<ApiResponse> {
        return responseToResource(newsRemoteDataSource.getTopHeadline(country,page))
    }

    private fun responseToResource(response :Response<ApiResponse>):Resource<ApiResponse>{

        if(response.isSuccessful){
            response.body()?.let { result->
                return Resource.Success(result)
            }
        }

        return Resource.Error(response.message())
    }

    override suspend fun getSearchedNews(country:String ,page:Int,searchQuery: String): Resource<ApiResponse> {
        return responseToResource(newsRemoteDataSource.getSearchTopHeadline(country,page,searchQuery))
    }

    override suspend fun saveNews(article: Article) {
        newsLocalDataSource.saveArticleToDB(article)
    }

    override suspend fun deleteNews(article: Article) {
        newsLocalDataSource.deleteArticleToDB(article)
    }

    override suspend fun getSaveNews(): Flow<List<Article>> {
       return newsLocalDataSource.getSaveAllArticle()
    }
}