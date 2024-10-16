package com.weatherapp.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.weatherapp.R
import com.weatherapp.model.City
import com.weatherapp.repo.Repository
import com.weatherapp.ui.nav.BottomNavItem


@Composable
fun ListPage(modifier: Modifier = Modifier, viewModel: MainViewModel, context: Context, repository: Repository, navCtrl: NavHostController) {
    val cityList = viewModel.cities
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(cityList) { city ->
            if (city.weather == null) {
                repository.loadWeather(city) }
            CityItem(city = city,
                onClose = { repository.remove(city) },
                onClick = {
                    viewModel.city = city
                    repository.loadForecast(city)
                    navCtrl.navigate(BottomNavItem.HomePage.route) {
                        navCtrl.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
                            restoreState = true
                        }
                        launchSingleTop = true
                    }
            },
                viewModel = viewModel)
        }
    }
}


@Composable
fun CityItem(
    city: City,
    onClick: () -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(8.dp).clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = city.weather?.imgUrl,
            modifier = Modifier.size(75.dp),
            error = painterResource(id = R.drawable.loading),
            contentDescription = "Imagem"
        )
        Spacer(modifier = Modifier.size(12.dp))
        Column(modifier = modifier.weight(1f)) {
            Text(modifier = Modifier,
                text = city.name,
                fontSize = 24.sp)
            Text(modifier = Modifier,
                text = city.weather?.desc?:"Carregando...",
                fontSize = 16.sp)
        }
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
        Icon(
            imageVector = if (city?.isMonitored == true) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = "Monitor?",
            modifier = Modifier.size(32.dp)
        )
    }
}

