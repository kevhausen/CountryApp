package com.example.countryapp.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.countryapp.model.dataclass.Country
import com.example.countryapp.model.dataclass.CountryDetail
import com.example.countryapp.model.db.CountryAplication
import com.example.countryapp.model.db.CountryDB
import com.example.countryapp.model.retrofit.RetrofitCountry
import com.google.android.material.snackbar.Snackbar
val TAG="kevin"
class CountryRepo {
    private val retrofit=RetrofitCountry.retrofitInstance()
    //esta es la forma de instanciar la db al estilo "manifest"
    private val dao=CountryAplication.countryDB!!.countryDao()

    //esto es para enviar el error hasta la vista, va a tener que pasar por el viewmodel
    var errorMessage=MutableLiveData<String>()
    /*cuando exista un cambio aqui, significa que la response fue fallida,
    entonces tengo que poner un observador en el main o fragment donde se llame este metodo, y asignarle el error body a un snackbar
*/


    //esto extrae de la api los nombres de paises y los guarda en db
    suspend fun getCountriesFromWebIntoDB(){
        val response=retrofit.getAllCountries()
        when(response.isSuccessful){
            true-> response.body()?.let { dao.insertCountryNamesDB(it) }
            //false-> errorMessage.value=response.errorBody().toString()
            false -> Log.d(TAG, "getCountriesFromWebIntoDB: ")
        }
    }

    //esto es para solicitar los nombres de paises desde la db
    fun getCountryList():LiveData<List<Country>> = dao.getAllCountriesDB()



    //esto es cuando el usuario hace click en un pais, extraer los detalles desde internet y meterlos en la db
    suspend fun getCountryDetailFromWebIntoDB(name:String){
        val response=retrofit.getDetailCountryByName(name)
        when(response.isSuccessful){
            true-> response.body()?.let { dao.insertCountryDetail(it) }
            false-> Log.d(TAG, "getCountriesFromWebIntoDB: ")
        }
    }

    //esto pide los detalles del pais solicitado desde la db

    fun getCountryDetail(name:String):LiveData<CountryDetail> = dao.getCountryDetailByNameDB(name)
}