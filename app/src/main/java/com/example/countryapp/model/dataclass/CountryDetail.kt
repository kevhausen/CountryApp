package com.example.countryapp.model.dataclass

import androidx.room.*

@Entity(tableName = "country_detail")
data class CountryDetail(
    val alpha2Code: String?,
    val alpha3Code: String?,
    val altSpellings: List<String>?,
    val area: Double?,
    val borders: List<String>?,
    val callingCodes: List<String>?,
    val capital: String?,
    val cioc: String?,

    //converter
    val currencies: List<Currency>?,

    val demonym: String?,
    val flag: String?,
    val gini: Double?,

    //converter
    val languages: List<Language>?,

    val latlng: List<Double>?,
    @PrimaryKey val name: String,
    val nativeName: String?,
    val numericCode: String?,
    val population: Int?,
    val region: String?,
    val subregion: String?,
    val timezones: List<String>?,
    val topLevelDomain: List<String>?,

    //converter
    @Embedded val translations: Translations?
)