package com.example.countryapp.model.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL="https://restcountries.eu/rest/v2/"

class RetrofitCountry {
    companion object {

        fun retrofitInstance(): CountryApi {

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(CountryApi::class.java)

        }
    }


}