package PantallaPrincipal

import InfoArray.Juego
import InfoArray.lista
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun pantallaInicio(navController: NavHostController) {

    var nombre by remember { mutableStateOf("") }
    var tipoSeleccionado by remember { mutableStateOf(setOf<String>()) }
    var fechaSeleccionada by remember { mutableStateOf(Calendar.getInstance()) }
    val consolas = listOf("N64", "WII", "GBA", "GCN", "GCN", "NDS", "WiiU", "3DS", "Switch ")
    var seleccionconsola by remember { mutableStateOf(consolas[0]) }
    var buscador by remember { mutableStateOf("") }
    var menuDesplegado by remember { mutableStateOf(false) }
    var pokemonSeleccionado by remember { mutableStateOf(mutableSetOf<String>()) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SearchBar(
            query = buscador,
            onQueryChange = {
                buscador = it
                if (it.isEmpty()) {
                    menuDesplegado = false
                }
            },
            onSearch = {
                if (seleccionconsola == null) {
                    menuDesplegado = false
                }
            },
            active = menuDesplegado,
            onActiveChange = { menuDesplegado = !menuDesplegado }
        ) {
            consolas.forEach { region ->
                if (region.startsWith(buscador, ignoreCase = true)) {
                    DropdownMenuItem(
                        onClick = {
                            seleccionconsola = region
                            buscador = region
                            menuDesplegado = false
                        },
                        text = { Text(text = region) }
                    )
                }
            }
        }

        if (seleccionconsola != null) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {

                items(lista.filter { it.nombre.equals(seleccionconsola, ignoreCase = true) }) { pokemon ->
                    RegionItem(
                        region = pokemon,
                        isChecked = pokemon.nombre in pokemonSeleccionado,
                        onCheckedChange = { isChecked ->
                            if (isChecked) {
                                pokemonSeleccionado.add(pokemon.nombre)
                            } else {
                                pokemonSeleccionado.remove(pokemon.nombre)
                            }
                        },
                        onClick = {
                            navController.navigate("pantallaDetalles/${pokemon.nombre}")
                        }
                    )
                }
            }
        } else if (seleccionconsola == null) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                items(lista) { pokemon ->
                    RegionItem(
                        region = pokemon,
                        isChecked = pokemon.nombre in pokemonSeleccionado,
                        onCheckedChange = { isChecked ->
                            if (isChecked) {
                                pokemonSeleccionado.add(pokemon.nombre)
                            } else {
                                pokemonSeleccionado.remove(pokemon.nombre)
                            }
                        },
                        onClick = {
                            navController.navigate("pantallaDetalles/${pokemon.nombre}")
                        }
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                navController.navigate("pantallaCreacionPokemon/$seleccionconsola") {
                    launchSingleTop = true
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                }
            }) {
                Text("Añadir")
            }

            Button(onClick = {
                lista.removeAll { it.nombre in pokemonSeleccionado }
                pokemonSeleccionado.clear()
            }) {
                Text("Borrar")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegionItem(region: Juego, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit, onClick: () -> Unit) {
    var checked by remember { mutableStateOf(isChecked) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                // Solo ejecuta el código onClick si se hace clic en la fila pero no en la casilla de verificación
                onClick()
            },
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(1f)
            ) {
                Image(
                    painter = painterResource(id = region.hashCode()),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.primary)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(text = region.nombre, style = MaterialTheme.typography.titleMedium)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Checkbox(
                checked = checked,
                onCheckedChange = {
                    checked = it
                    onCheckedChange(it)
                }
            )
        }
    }
}