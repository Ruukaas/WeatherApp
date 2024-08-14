package com.weatherapp.ui

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weatherapp.MainActivity
import com.weatherapp.RegisterActivity

@Preview(showBackground = true)
@Composable
fun LoginPage(modifier: Modifier = Modifier) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val activity = LocalContext.current as? Activity
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Bem-vindo/a!",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.size(24.dp))
        DataField(value = email, label = "Digite seu email", onValueChange = { email = it })
        Spacer(modifier = Modifier.size(24.dp))
        PasswordField(
            value = password,
            label = "Digite sua senha",
            onValueChange = { password = it })
        Spacer(modifier = Modifier.size(24.dp))
        Row(modifier = modifier) {
            Button(
                onClick = {
                    Toast.makeText(activity, "Login OK!", Toast.LENGTH_LONG).show()
                    activity?.startActivity(
                        Intent(activity, MainActivity::class.java).setFlags(
                            Intent.FLAG_ACTIVITY_SINGLE_TOP
                        )
                    )

                },
                enabled = email.isNotEmpty() && password.isNotEmpty()
            ) {
                Text("Login")
            }
            Spacer(modifier = Modifier.size(10.dp))
            Button(
                onClick = { email = ""; password = "" }
            ) {
                Text("Limpar")
            }
        }
        Row(modifier = modifier) {
            Button(
                onClick = {
                    activity?.startActivity(
                        Intent(activity, RegisterActivity::class.java).setFlags(
                            Intent.FLAG_ACTIVITY_SINGLE_TOP
                        )
                    )
                }
            ) {
                Text("Faça seu registro")
            }

        }
    }
}