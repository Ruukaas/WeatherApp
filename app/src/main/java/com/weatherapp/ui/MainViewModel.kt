package com.weatherapp.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.weatherapp.db.fb.FBDatabase
import com.weatherapp.model.City
import com.weatherapp.model.User

class MainViewModel : ViewModelBase(), FBDatabase.Listener {
    private val _cities = mutableStateListOf<City>()
    val cities : List<City> get() = _cities

    private val _user = mutableStateOf (User("", ""))
    val user : User
        get() = _user.value

    override fun onUserLoaded(user: User) {
        _user.value = user
    }

    override fun onCityAdded(city: City) {
        _cities.add(city)
    }

    override fun onCityRemoved(city: City) {
        _cities.remove(city)
    }
}
