package com.example.countryapp.model.dataclass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="countries")
data class Country(@PrimaryKey var name:String) {
}