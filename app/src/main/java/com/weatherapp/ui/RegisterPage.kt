package com.weatherapp.ui

import android.app.Activity
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
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Preview(showBackground = true)
@Composable
fun RegisterPage(modifier: Modifier = Modifier) {
    var username by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    val activity = LocalContext.current as? Activity
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Faça seu cadastro",
            fontSize = 24.sp
        )
        DataField(
            value = username,
            label = "Digite seu nome de usuário",
            onValueChange = { username = it })
        Spacer(modifier = Modifier.size(24.dp))
        DataField(value = email, label = "Digite seu email", onValueChange = { email = it })
        Spacer(modifier = Modifier.size(24.dp))
        PasswordField(
            value = password,
            label = "Digite sua senha",
            onValueChange = { password = it })
        Spacer(modifier = Modifier.size(24.dp))
        PasswordField(
            value = confirmPassword,
            label = "Confirme sua senha",
            onValueChange = { confirmPassword = it })
        Spacer(modifier = Modifier.size(24.dp))
        Row(modifier = modifier) {
            Button(
                onClick = {
                    Firebase.auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(activity!!) { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(activity, "Registro OK!!!!!", Toast.LENGTH_LONG).show()
                            activity.finish()
                            } else {
                                Toast.makeText(activity, "Registro FALHOU!!!!!", Toast.LENGTH_LONG).show()
                            }
                        }

                },
                enabled = email.isNotEmpty() && username.isNotEmpty() &&
                        password.isNotEmpty() && confirmPassword.isNotEmpty()
                        && password.equals(confirmPassword)
            ) {
                Text("Cadastrar")
            }
            Spacer(modifier = Modifier.size(24.dp))
            Button(
                onClick = { email = ""; password = ""; username = ""; confirmPassword = "" }
            ) {
                Text("Limpar")
            }
            Spacer(modifier = Modifier.size(24.dp))
        }

    }
}