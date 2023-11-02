package com.example.news_mvvm.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.news_mvvm.data.model.Article

@Database(
    entities = [Article::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converts::class)
abstract  class ArticleDataBase :RoomDatabase() {
    abstract fun getArticleAO():ArticleDao
}