package com.example.countryapp.model.retrofit

import com.example.countryapp.model.dataclass.Country
import com.example.countryapp.model.dataclass.CountryDetail
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface CountryApi {
    @GET("all?fields=name")
    suspend fun getAllCountries(): Response<List<Country>>

    @GET("name/{name}")
    suspend fun getDetailCountryByName(@Path("name")name:String):Response<CountryDetail>


}