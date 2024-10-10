package com.weatherapp.model

import android.graphics.Bitmap
import com.google.android.gms.maps.model.LatLng

data class City(
    val name: String,
    var weather: Weather? = null,
    var forecast: List<Forecast>? = null, // Usada mais a frente
    var location: LatLng? = null,
    var img_url: String? = null,
    var bitmap: Bitmap? = null,
//    var forecast: Forecast? = null
    var isMonitored: Boolean? = false
)
