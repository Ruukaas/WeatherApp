package com.weatherapp.model

import android.graphics.Bitmap
import com.google.android.gms.maps.model.LatLng

data class City(
    val name: String,
    var weather: String? = null,
    var location: LatLng? = null,
    var img_url: String? = null,
    var bitmap: Bitmap? = null

)
