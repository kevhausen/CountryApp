package com.example.countryapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.countryapp.databinding.DetailFragmentBinding
import com.example.countryapp.databinding.ListFragmentBinding
import com.example.countryapp.model.TAG
import com.example.countryapp.model.dataclass.Country
import com.example.countryapp.model.dataclass.CountryDetail
import com.example.countryapp.viewmodel.CountryVM
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener

class DetailFragment:Fragment() {

    private lateinit var binding: DetailFragmentBinding
    private val countryVM: CountryVM by activityViewModels()
    private lateinit var countryDetail: CountryDetail
    private var clickedCountry = MutableLiveData<Country>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        /*aqui estuve harto rato peleando porque salia un error con el viewLifeCycleOwner, decia que era nulo. La cuestion era
        * que cuando se usa binding, hay que retornar al final binding.root, yo tenia que retornaba
        * la cosa tipica esa "return inflater.inflate(R.layout.fragment_phone_detail, container, false)"
        * luego salieron errores al hacer muy rapido el click a distintos paises, para solucionar eso habia que hacer
        * un null check dentro del observador (linea 49 "if(it!=null)")*/

        binding = DetailFragmentBinding.inflate(layoutInflater)
        //necesito saber el clicked country
        clickedCountry.value = countryVM.currentCountry.value
        Log.d(TAG, "onCreateView: pase al detail")
        clickedCountry.value.let { country ->
            if (country != null) {
                countryVM.getCountryDetail(country).observe(viewLifecycleOwner, {
                    Log.d(TAG, "onViewCreated: estoy dentro del obervador")
                    if (it != null) {
                        countryDetail = it
                        binding.apply {
                            titleCountryName.text = countryDetail.name
                            txtAlphacode.text = countryDetail.alpha2Code
                            txtArea.text = countryDetail.area.toString()
                            txtCapital.text = countryDetail.capital
                            txtDomain.text = countryDetail.topLevelDomain.toString()
                            txtGentilicio.text = countryDetail.demonym

                            if(countryDetail.gini!=null){
                                txtGini.text = countryDetail.gini.toString()
                            }


                            val languages= mutableListOf<String>()
                            for(idioma in countryDetail.languages!!){
                                idioma.name?.let { it1 -> languages.add(it1) }
                            }
                            txtIdioma.text = languages.toString()

                            val nameCurrency= mutableListOf<String>()
                            for (currency in countryDetail.currencies!!){
                                currency.name?.let { it1 -> nameCurrency.add(it1) }
                            }
                            txtMoneda.text = nameCurrency.toString()
                            txtPopulation.text = countryDetail.population.toString()
                            txtPrefijo.text = countryDetail.callingCodes.toString()
                            txtRegion.text = countryDetail.region
                            txtSubregion.text = countryDetail.subregion
                            txtTimezone.text = countryDetail.timezones.toString()

                            txtVecinos.text = countryDetail.borders.toString()
                            GlideToVectorYou.justLoadImage(
                                activity,
                                countryDetail.flag?.toUri(),
                                banderaSvg
                            )
                        }
                    }
                })


            }

            return binding.root
        }


    }
}