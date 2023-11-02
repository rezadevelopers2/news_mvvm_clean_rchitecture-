package com.example.news_mvvm.presentation.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.news_mvvm.data.model.ApiResponse
import com.example.news_mvvm.data.model.Article
import com.example.news_mvvm.data.util.Resource
import com.example.news_mvvm.domain.usecase.DeleteSaveNewsUseCase
import com.example.news_mvvm.domain.usecase.GetNewsHeadlineUseCase
import com.example.news_mvvm.domain.usecase.GetSaveNewsUseCase
import com.example.news_mvvm.domain.usecase.GetSearchNewUseCase
import com.example.news_mvvm.domain.usecase.SaveNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel (
    val app : Application,
    val  getNewsHeadlineUseCase: GetNewsHeadlineUseCase,
    val  getSearchNewUseCase: GetSearchNewUseCase,
    private val  saveNewsUseCase: SaveNewsUseCase,
    private val getSaveNewsUseCase: GetSaveNewsUseCase,
    private val deleteSaveNewsUseCase: DeleteSaveNewsUseCase
): AndroidViewModel(app) {

    val newsHeadlines : MutableLiveData<Resource<ApiResponse>> = MutableLiveData()

    fun getNewsHeadline(country:String ,page:Int) =
        viewModelScope.launch(Dispatchers.IO) {
            newsHeadlines.postValue(Resource.Loading())
            try {


                if (isNetWorkAvailable(app)) {

                    val apiResult = getNewsHeadlineUseCase.execute(country, page)
                    newsHeadlines.postValue(apiResult)
                } else {
                    newsHeadlines.postValue(Resource.Error("Internet is not Available"))
                }


            }catch(e:Exception){
                newsHeadlines.postValue(Resource.Error(e.message.toString()))

            }
        }


    val searchNews : MutableLiveData<Resource<ApiResponse>> = MutableLiveData()

    fun getSearchNewsHeadline(country:String ,page:Int,query:String) =
        viewModelScope.launch(Dispatchers.IO) {
            searchNews.postValue(Resource.Loading())
            try {


                if (isNetWorkAvailable(app)) {

                    val apiResult = getSearchNewUseCase.execute(country, page,query)
                    searchNews.postValue(apiResult)
                } else {
                    searchNews.postValue(Resource.Error("Internet is not Available"))
                }


            }catch(e:Exception){
                searchNews.postValue(Resource.Error(e.message.toString()))

            }
        }



    fun saveArticle(article: Article){

        viewModelScope.launch {
            saveNewsUseCase.execute(article);
        }
    }

    fun getSavedNews() = liveData {

        getSaveNewsUseCase.execute().collect{
            emit(it)
        }
    }

    fun deleteArticle(article: Article)= viewModelScope.launch {
        deleteSaveNewsUseCase.execute(article)
    }

    private fun isNetWorkAvailable(context: Context?):Boolean{


        if(context == null)return false

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
             val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
             if (capabilities != null) {
                 when {
                     capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                         return true
                     }
                     capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                         return true
                     }
                     capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                         return true
                     }
                 }
             }
         } else {
             val activeNetworkInfo = connectivityManager.activeNetworkInfo
             if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                 return true
             }
         }
        return false
    }



}