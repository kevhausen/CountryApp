package com.example.countryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countryapp.model.CountryRepo
import com.example.countryapp.model.dataclass.Country
import com.example.countryapp.model.dataclass.CountryDetail
import kotlinx.coroutines.launch

class CountryVM:ViewModel() {

    //si tengo 4 funciones en el repo, tengo 4 funciones aca (o puede ser que las funciones de "get", solamente las guarde en una variable)

    private val repository = CountryRepo()

    //vamos a guardar aqui el country clikeado
    private val currentCountry=MutableLiveData<Country>()

    fun getErrorMessage():LiveData<String> = repository.errorMessage

    //con estos dos metodos, hacemos las llamadas a la api y las guardamos en db
    init{
        insertCountryDataIntoDB()
    }

    //este puede que lo ponga en el init, ya que siempre necesitare al principio los nombres de paises
    fun insertCountryDataIntoDB(){
        viewModelScope.launch {
            repository.getCountriesFromWebIntoDB()
        }
    }

    fun insertCountryDetailDataIntoDB(name:String){
        viewModelScope.launch {
            repository.getCountryDetailFromWebIntoDB(name)
        }
    }


    //funciones donde se guardaran los litados de paises y el detalle respectivamente

    fun getCountryList():LiveData<List<Country>> = repository.getCountryList()
    fun getCountryDetail(name:String):LiveData<CountryDetail> = repository.getCountryDetail(name)



}