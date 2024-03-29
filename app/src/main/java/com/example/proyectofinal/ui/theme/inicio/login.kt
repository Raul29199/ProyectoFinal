package com.example.proyectofinal.ui.theme.inicio


import Navegacion.GrafoNavegacion
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyectofinal.R
import kotlinx.coroutines.launch
import android.content.Context
import androidx.compose.ui.platform.LocalContext


@Composable
fun PantallaInicioSesion(navController: NavHostController) {
    var nombre by remember { mutableStateOf("") }
    var contraseña by remember { mutableStateOf("") }
    var mostrarMensajeError by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var mediaPlayer: MediaPlayer? by remember { mutableStateOf(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.imageninicio),
            contentDescription = "Logo",
            modifier = Modifier
                .size(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        OutlinedTextField(
            value = contraseña,
            onValueChange = { contraseña = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        if (mostrarMensajeError) {
            Text(
                text = "Credenciales no válidas. Inténtalo de nuevo.",
                color = Color.Red,
                modifier = Modifier.padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        fun toggleMusic(context: Context) {

            mediaPlayer = MediaPlayer.create(context, R.raw.gameboy)
            mediaPlayer?.start()

        }
        val context = LocalContext.current


        Button(
            onClick = {
                // Navegar a la pantalla de inicio
                if(contraseña.equals("admin",ignoreCase = true)&&nombre.equals("Admin",ignoreCase = true)){
                    navController.navigate("pantallaInicio")
                    toggleMusic(context)
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("Iniciar sesión")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Agregar el texto "Registrarse" que navega a la pantalla de registro
        Text(
            text = "¿No tienes cuenta? Registrarse",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .clickable {
                    scope.launch {
                        navController.navigate("pantallaRegistro")
                    }
                }
        )
    }
}
