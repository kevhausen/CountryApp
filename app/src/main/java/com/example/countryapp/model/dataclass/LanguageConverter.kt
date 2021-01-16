package com.example.countryapp.model.dataclass

import androidx.room.TypeConverter
import com.google.gson.Gson

class LanguageConverter {
    @TypeConverter
    fun listToJson(value: List<Language>?): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Language>? {

        val objects = Gson().fromJson(value, Array<Language>::class.java) as Array<Language>
        val list = objects.toList()
        return list
    }

}