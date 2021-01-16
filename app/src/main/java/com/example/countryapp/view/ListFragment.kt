package com.example.countryapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countryapp.databinding.ListFragmentBinding
import com.example.countryapp.viewmodel.CountryVM
import com.google.android.material.snackbar.Snackbar


//con binding, es necesario declarar como varible de clase "binding", ya que probablemente la utilizaremos a lo largo de todos los override del fragment
class ListFragment :Fragment() {

    /*
    se declara como lateinit "binding: y automaticamente viewBinding crea una clase del layout correspondiente (el alyout de este fragment es "list_fragment", por lo tanto
    la clase autogenerada se llamara ListFragmentBinding)

    */
    private lateinit var binding:ListFragmentBinding
    private val countryVM : CountryVM by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // se inicializa binding con su clase correspondiente + .inflate(layoutInflater)
        binding= ListFragmentBinding.inflate(layoutInflater)

        //lo declaramos aca para usarlo despues con el metodo "updateData"
        val mAdapter=ListAdapter()

        binding.recyclerViewCountryList.apply {
            layoutManager=LinearLayoutManager(context)
            adapter=mAdapter
        }

        //aca el viewHolder trae lo que esta en la base de datos y lo seteamos en el adapter
        countryVM.getCountryList().observe(viewLifecycleOwner,{
            mAdapter.updateData(it)
        })

        /*countryVM.getErrorMessage().observe(viewLifecycleOwner,{
            Snackbar.make(binding.root,"Falla en respuesta de api '$it'",Snackbar.LENGTH_LONG).show()
        })*/


        //siempre se retorna la raiz del binding, para mostrar tode
        return binding.root
    }
}