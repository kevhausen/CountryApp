package com.example.countryapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.countryapp.databinding.ListItemViewholderBinding
import com.example.countryapp.model.TAG
import com.example.countryapp.model.dataclass.Country

class ListAdapter:RecyclerView.Adapter<ListAdapter.CountryHolder>() {

    private var countryList= listOf<Country>()

    private val clickedItem=MutableLiveData<Country>()

    //este metodo es consultado en el fragment para saber que pais se clikeo.(ListAdapter.getClickedItem)
    fun getClickedItem():MutableLiveData<Country> = clickedItem

    //este metodo es para llamarlo en el fragment, ya que ahi se instancia el viewmodel y este le pasa el "updatesList"
    fun updateData(updatesList:List<Country>){
        countryList=updatesList
        notifyDataSetChanged()

    }

    inner class CountryHolder(var binding: ListItemViewholderBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country){
            binding.countryName.text=country.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        return CountryHolder(ListItemViewholderBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        val country=countryList[position]
        holder.bind(country)
        holder.itemView.setOnClickListener {
            //cuando el usuario haga click en un pais, se va a guardar en el valor "clickedItem", el cual puede ser consultado desde otra clase, con el metodo "getClickedItem"
            clickedItem.value=country
            Log.d(TAG, "onBindViewHolder: ${clickedItem.value}")
        }
    }

    override fun getItemCount(): Int {
        return countryList.size
    }
}