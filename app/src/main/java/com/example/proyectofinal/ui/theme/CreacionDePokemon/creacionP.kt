package CreacionDePokemon

import InfoArray.Juego
import InfoArray.lista
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun pantallaCreacionPokemon(navController: NavHostController, nombre: String?) {
    var nombre by remember { mutableStateOf("") }
    var tipoSeleccionado by remember { mutableStateOf(setOf<String>()) }
    var fechaSeleccionada by remember { mutableStateOf(Calendar.getInstance()) }
    val consolas = listOf("N64", "WII", "GBA", "GCN", "GCN", "NDS", "WiiU", "3DS", "Switch ")
    var buscador by remember { mutableStateOf("") }
    var seleccionconsola by remember { mutableStateOf(consolas[0]) }
    var menuDesplegado by remember { mutableStateOf(false) }
    var imagen by remember { mutableStateOf(0) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val scrollState = rememberScrollState()
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = 1578096000000)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = nombre,
            onValueChange = { nuevoNombre ->
                nombre = nuevoNombre
                val index = lista.indexOfFirst { it.nombre == nuevoNombre }

                if (index != -1) {
                    // Accede al objeto en la lista con ese índice
                    val elementoSeleccionado = lista[index]

                    // Ahora puedes obtener la imagen del elemento
                    val imagen = elementoSeleccionado.imagen

                    // Realiza la lógica necesaria con la imagen
                } else {
                    // El nombre seleccionado por el usuario no se encontró en la lista
                    // Muestra un mensaje de error utilizando Toast

                }
            },
            label = { Text("Nombre del juego de Pokémon") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            )
        )
    }


    // Cuadro de texto y menú desplegable para la región
        SearchBar(
            query = buscador,
            onQueryChange = {
                buscador = it
                // Cerrar el menú desplegable si la búsqueda está vacía

            },
            onSearch = {
                    menuDesplegado = false
            },
            active = menuDesplegado,
            onActiveChange = { menuDesplegado = it }
        ) {
            // 1 - Filtrar en base a "buscador"
            Column {
                consolas.filter { consola -> consola.startsWith(buscador, ignoreCase = true) }
                    .forEach { consola ->
                        Log.d("Añadir juego", "Tipo de consola: $consola")
                        DropdownMenuItem(
                            onClick = {
                                seleccionconsola = consola
                                buscador = consola
                                menuDesplegado = false
                            },
                            text = { Text(text = consola) }
                        )
                    }
            }
        }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                // Pre-select a date for January 4, 2020
                DatePicker(state = datePickerState, modifier = Modifier.padding(16.dp))

                Text("Selecciona la fecha de inicio: ${datePickerState.selectedDateMillis ?: "no selecionado"}")
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
                navController.popBackStack()
            }) {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Back")
            }

            Spacer(modifier = Modifier.width(16.dp))

            IconButton(onClick = {
                // Guardar el Pokémon y navegar hacia atrás
                // Aquí deberías guardar la información en tu lista de Pokémon o en tu modelo de datos
                lista.add(Juego(nombre,imagen,seleccionconsola,Date(datePickerState.selectedDateMillis ?: 0)))
                navController.popBackStack()
            }) {
                Icon(imageVector = Icons.Outlined.Check, contentDescription = "Save")
            }
        }
    }
@Composable
fun MostrarToast(message: String) {
    val context = LocalContext.current
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
/*AlertDialog(
                        onDismissRequest = { "alerta" },
                        title = { Text("Nombre no válido") },
                        text = { Text("El nombre ingresado no coincide con ningún juego de Pokémon en la lista.") },
                        confirmButton = {
                            Button(
                                onClick = {
                                    // Puedes realizar alguna acción adicional si es necesario
                                }
                            ) {
                                Text("OK", color = Color.Black)
                            }
                        }
                    )*/