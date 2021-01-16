package com.example.countryapp.model.dataclass

import androidx.room.TypeConverter
import com.google.gson.Gson

class ListDoubleConverter {
    @TypeConverter
    fun listToJson(value: List<Double>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Double>? {
        val objects = Gson().fromJson(value, Array<Double>::class.java) as Array<Double>
        val list = objects.toList()
        return list
    }
}