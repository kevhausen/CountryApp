package com.example.countryapp.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countryapp.MainActivity
import com.example.countryapp.databinding.ListFragmentBinding
import com.example.countryapp.isOnline
import com.example.countryapp.model.TAG
import com.example.countryapp.viewmodel.CountryVM
import com.google.android.material.snackbar.Snackbar
import kotlin.Exception


//con binding, es necesario declarar como varible de clase "binding", ya que probablemente la utilizaremos a lo largo de todos los override del fragment
class ListFragment :Fragment() {

    /*
    se declara como lateinit "binding: y automaticamente viewBinding crea una clase del layout correspondiente (el alyout de este fragment es "list_fragment", por lo tanto
    la clase autogenerada se llamara ListFragmentBinding)

    */
    private lateinit var binding:ListFragmentBinding
    private val countryVM : CountryVM by activityViewModels()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(isOnline(requireContext())){
            countryVM.insertCountryDataIntoDB()
        } else{
            Snackbar.make(requireActivity().findViewById(android.R.id.content),"No hay internet",Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= ListFragmentBinding.inflate(layoutInflater)
        // se inicializa binding con su clase correspondiente + .inflate(layoutInflater)
        if(!isOnline(requireContext())){
            showFailWidget(true)
            binding.buttonRetryConnection.setOnClickListener {
                if(isOnline(requireContext())){
                    showFailWidget(false)
                    countryVM.insertCountryDataIntoDB()
                } else{
                    Snackbar.make(requireActivity().findViewById(android.R.id.content),"No hay internet",Snackbar.LENGTH_LONG).show()
                }

            }
        }


        val mAdapter=ListAdapter()
        binding.recyclerViewCountryList.apply {
            layoutManager=LinearLayoutManager(context)
            adapter=mAdapter
        }


        //aca el viewHolder trae lo que esta en la base de datos y lo seteamos en el adapter
        countryVM.getCountryList().observe(viewLifecycleOwner,{
            mAdapter.updateData(it)
        })

        //siempre se retorna la raiz del binding, para mostrar tode
        return binding.root
    }

    //funcion que muestra un boton de "reintentar" junto a un texto. se usa cuando no hay internet
    private fun showFailWidget(show :Boolean){
        if(show){
            binding.textRetryConnection.visibility=View.VISIBLE
            binding.buttonRetryConnection.visibility=View.VISIBLE
        } else{
            binding.textRetryConnection.visibility=View.GONE
            binding.buttonRetryConnection.visibility=View.GONE
        }
    }
}