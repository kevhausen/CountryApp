package com.example.countryapp.model.dataclass

import androidx.room.TypeConverter
import com.google.gson.Gson

class CurrencyConverter {
    @TypeConverter
    fun listToJson(value: List<Currency>?): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Currency>? {

        val objects = Gson().fromJson(value, Array<Currency>::class.java) as Array<Currency>
        val list = objects.toList()
        return list
    }
}