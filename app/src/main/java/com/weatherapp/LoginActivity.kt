package com.weatherapp

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.weatherapp.ui.LoginPage
import com.weatherapp.ui.ViewModelBase
import com.weatherapp.ui.theme.WeatherAppTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModelBase = ViewModelBase()
        val activity = this
        setContent {
            if (viewModelBase.loggedIn) {
                Toast.makeText(activity, "LoginToast!", Toast.LENGTH_SHORT).show()
                activity.startActivity(
                    Intent(activity, MainActivity::class.java).setFlags(
                        FLAG_ACTIVITY_SINGLE_TOP
                    )
                )
            }
            WeatherAppTheme {
                LoginPage()
            }

        }

    }
}

