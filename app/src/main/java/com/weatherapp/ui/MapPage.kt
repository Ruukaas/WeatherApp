package com.weatherapp.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.weatherapp.repo.Repository

@Preview
@Composable
fun MapPage(modifier: Modifier = Modifier, viewModel: MainViewModel, context: Context, repository: Repository) {
    val camPosState = rememberCameraPositionState ()

    Column() {
        val hasLocationPermission by remember {
            mutableStateOf( ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
            )
        }

        GoogleMap (
            modifier = Modifier.fillMaxSize(),
            onMapClick = {
                repository.addCity(lat = it.latitude, lng = it.longitude)
                         },
            cameraPositionState = camPosState,
            properties = MapProperties(isMyLocationEnabled = hasLocationPermission),
            uiSettings = MapUiSettings(myLocationButtonEnabled = true)
        ) {
            viewModel.cities.forEach {
                if (it.location != null) {
                    Marker(state = MarkerState(position = it.location!!),
                        title = it.name, snippet = "${it.location}")
                }
            }
        }


    }
}