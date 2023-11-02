package com.example.news_mvvm.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.news_mvvm.domain.usecase.DeleteSaveNewsUseCase
import com.example.news_mvvm.domain.usecase.GetNewsHeadlineUseCase
import com.example.news_mvvm.domain.usecase.GetSaveNewsUseCase
import com.example.news_mvvm.domain.usecase.GetSearchNewUseCase
import com.example.news_mvvm.domain.usecase.SaveNewsUseCase

class NewViewModelFactory(
    val app : Application,
    val  getNewsHeadlineUseCase: GetNewsHeadlineUseCase,
    val getSearchNewUseCase: GetSearchNewUseCase,
    private val  saveNewsUseCase: SaveNewsUseCase,
    private val getSaveNewsUseCase: GetSaveNewsUseCase,
    private val deleteSaveNewsUseCase: DeleteSaveNewsUseCase
) :ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(app,getNewsHeadlineUseCase,getSearchNewUseCase,saveNewsUseCase,getSaveNewsUseCase,deleteSaveNewsUseCase) as T
    }
}