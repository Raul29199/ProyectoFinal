package CreacionDePokemon

import com.example.proyectofinal.ui.theme.BBDD.Juego
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.proyectofinal.R
import com.example.proyectofinal.ui.theme.BBDD.lista
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun pantallaCreacionPokemon(navController: NavHostController) {
    var nombre by remember { mutableStateOf("") }
    var tipoSeleccionado by remember { mutableStateOf(setOf<String>()) }
    var fechaSeleccionada by remember { mutableStateOf(Calendar.getInstance()) }
    val consolas = listOf("Game Boy", "Game Boy Color", "Game Boy Advance", "Nintendo DS", "Nintendo 3DS", "Nintendo Switch")
    var consola by remember {mutableStateOf("")}
    var buscador by remember { mutableStateOf("") }
    var menuDesplegado by remember { mutableStateOf(false) }
    var imagen by remember { mutableStateOf(R.drawable.alfa_ruby) }
    var expanded by remember { mutableStateOf(false) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val scrollState = rememberScrollState()
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = 1578096000000)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = nombre,
            onValueChange = { nuevoNombre ->
                nombre = nuevoNombre
            },
            label = { Text("Nombre del juego de Pokémon") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            )
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray.copy(alpha = 0.1f))
                .padding(8.dp)
                .clickable { expanded = true }
        ) {
            Text( "Consola seleccionado: $consola", modifier = Modifier.padding(16.dp))
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                consolas.forEach { team ->
                    DropdownMenuItem(text = { Text(text = team) }, onClick = { consola = team
                        expanded = true })
                }
            }
        }
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            // Pre-select a date for January 4, 2020
            DatePicker(state = datePickerState, modifier = Modifier.padding(16.dp))

            Text("Selecciona la fecha de inicio: ${datePickerState.selectedDateMillis ?: "no seleccionado"}")
        }

        // Botones de navegación
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = {
                // Navegar hacia atrás
                navController.navigate("pantallaInicio")
            }) {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Back")
            }

            Spacer(modifier = Modifier.width(16.dp))

            IconButton(onClick = {
                // Guardar el Pokémon y navegar hacia atrás
                // Aquí deberías guardar la información en tu com.example.proyectofinal.ui.theme.BBDD.getLista de Pokémon o en tu modelo de datos
                lista.add(Juego(nombre, imagen, consola, Date(datePickerState.selectedDateMillis ?: 0)))
                navController.navigate("pantallaInicio")
            }) {
                Icon(imageVector = Icons.Outlined.Check, contentDescription = "Save")
            }
        }
    }
}
