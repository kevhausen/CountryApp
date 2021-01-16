package com.example.countryapp.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.countryapp.model.dataclass.Country
import com.example.countryapp.model.dataclass.CountryDetail

@Dao
interface CountryDao {

    //acceso a lista con nombres de paises
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountryNamesDB(contryList:List<Country>)

    @Query("SELECT * FROM countries")
    fun getAllCountriesDB():LiveData<List<Country>>

    //acceso a detalle de pais, preguntando por nombre de pais
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountryDetail(countryDetail:CountryDetail)

    @Query("SELECT * FROM country_detail WHERE name=:name ")
    fun getCountryDetailByNameDB(name:String):LiveData<CountryDetail>






}