package com.example.news_mvvm.data.db

import androidx.room.TypeConverter
import com.example.news_mvvm.data.model.Source

class Converts {

    @TypeConverter
    fun fromSource(source : Source):String?{
        return source.name
    }

    @TypeConverter
    fun toSource(name : String):Source{
        return Source(name,name)
    }
}