package com.weatherapp.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation


@Composable
fun DataField(modifier: Modifier = Modifier,
              value: String, label : String,
              onValueChange : (String) -> Unit,
              initialValue : String = "") {
    OutlinedTextField(
        value = value,
        label = {Text(label)},
        modifier = modifier.fillMaxWidth(),
        onValueChange = onValueChange
    )
}

@Composable fun PasswordField(modifier: Modifier = Modifier,
                              value: String, label : String,
                              onValueChange : (String) -> Unit,
                              initialValue : String = "") {
    OutlinedTextField(
        value = value,
        label = {Text(label)},
        modifier = modifier.fillMaxWidth(),
        onValueChange = onValueChange,
        visualTransformation = PasswordVisualTransformation()
    )
}