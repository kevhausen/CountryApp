package com.example.countryapp.model.db

import android.app.Application
import androidx.room.*
import com.example.countryapp.model.dataclass.*


/*creacion de base de datos al estilo "manifest"
al crear la base de datos de esta forma, es necesario modificar el manifest, diciendole que cuando inicie la aplicacion, cargue la clase CountryAplicaction de las primeras
<application
     android:name=".model.db.CountryAplication"
*/
@Database(entities = [Country::class,CountryDetail::class],version = 1, exportSchema = false)
@TypeConverters(LanguageConverter::class,CurrencyConverter::class, ListConverter::class, ListDoubleConverter::class)
abstract class CountryDB:RoomDatabase() {
    abstract fun countryDao():CountryDao

}
class CountryAplication: Application(){
companion object{
    var countryDB:CountryDB?=null
}

    override fun onCreate() {
        super.onCreate()
        countryDB= Room.databaseBuilder(this,CountryDB::class.java,"countries_database").build()
    }
}