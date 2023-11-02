package com.example.news_mvvm.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewApp :Application(){

    override fun onCreate() {
        super.onCreate()
    }
}
