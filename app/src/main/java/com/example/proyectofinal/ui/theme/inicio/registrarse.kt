package com.example.proyectofinal.ui.theme.inicio

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyectofinal.R

@Composable
fun PantallaRegistro(navController: NavHostController) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contraseña by remember { mutableStateOf("") }
    var repetirContraseña by remember { mutableStateOf("") }

    var errorContraseña by remember { mutableStateOf(false) }
    var errorCorreo by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Centrar la imagen
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Logo",
            modifier = Modifier
                .size(100.dp)
                .padding(bottom = 16.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.primary)
        )

        // OutlinedTextField y otros campos
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = correo,
            onValueChange = {
                correo = it
                // Realiza una validación básica del correo electrónico
                errorCorreo = !it.contains("@")
            },
            label = { Text("Correo electrónico") },
            isError = errorCorreo,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = contraseña,
            onValueChange = { contraseña = it },
            label = { Text("Contraseña") },
            visualTransformation = if (errorContraseña) VisualTransformation.None else PasswordVisualTransformation(),
            isError = errorContraseña,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = repetirContraseña,
            onValueChange = {
                repetirContraseña = it
                // Validar si la contraseña repetida coincide con la original
                errorContraseña = it != contraseña
            },
            label = { Text("Repetir Contraseña") },
            visualTransformation = if (errorContraseña) VisualTransformation.None else PasswordVisualTransformation(),
            isError = errorContraseña,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Botón centrado
        Button(onClick = {
            if (nombre.isNotEmpty() && correo.contains("@") && contraseña.isNotEmpty() && contraseña == repetirContraseña) {
                // Registro exitoso, navegar al inicio
                navController.navigate("login") {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                }
            } else {
                // Mostrar mensaje de error
                Toast.makeText(context, "Error en el registro. Por favor, verifica tus datos.", Toast.LENGTH_SHORT).show()
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Registrarse")
        }

        // Flecha para volver al inicio de sesión en la esquina superior izquierda
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .size(36.dp)
                .clickable {
                    navController.navigateUp()
                }
        )
    }
}
