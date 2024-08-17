package com.weatherapp.ui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import com.google.android.gms.maps.model.LatLng
import com.weatherapp.model.City
import com.weatherapp.model.User

class MainViewModel : ViewModelBase() {
    private val _cities = getCities().toMutableStateList()
    val cities : List<City> get() = _cities

    fun remove(city: City) {
        _cities.remove(city)
    }
    fun add(city: String, location: LatLng? = null) {
        _cities.add(City(city, "Carregando clima...", location))
    }

    private val _user = mutableStateOf (User("", ""))
    val user : User
        get() = _user.value
}


private fun getCities() = List(30) { i ->
    City(name = "Cidade $i", weather = "Carregando clima...")
}