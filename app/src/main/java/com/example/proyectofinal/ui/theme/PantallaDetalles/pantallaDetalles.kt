package PantallaDetalles

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectofinal.R

@Composable
fun pantallaDetalles(navController: NavController, pokemon: String?) {
    // Aquí deberías tener una lógica para obtener detalles del Pokémon basado en su nombre
    val detallesPokemon = obtenerDetallesPokemon(pokemon)

    // Composición de la pantalla de detalles
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Espaciador
        Spacer(modifier = Modifier.height(16.dp))

        // Botón de retroceso
        IconButton(
            onClick = {
                navController.navigate("pantallaInicio")
            }
        ) {
            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = null)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Detalles del Pokémon
        detallesPokemon?.let { pokemon ->
            // Muestra la imagen del Pokémon
            Image(
                painter = painterResource(id = pokemon.imagenes),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .align(Alignment.CenterHorizontally)
            )

            // Espaciador
            Spacer(modifier = Modifier.height(16.dp))

            // Muestra el nombre del Pokémon
            Text(
                text = pokemon.nombre,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 15.dp)
                    .align(Alignment.CenterHorizontally)
            )

            // Muestra la ruta y texto de la Pokédex
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Muestra la ruta del Pokémon en negrita
                Text(
                    text = "Consola: ${pokemon.consola}",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                // Espaciador
                Spacer(modifier = Modifier.height(15.dp))

                // Muestra el texto de la Pokédex en negrita
                Text(
                    text = "Pokédex: ${pokemon.descripcion}",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            // Espaciador
            Spacer(modifier = Modifier.height(16.dp))
        } ?: run {
            // Muestra un mensaje si no se encuentran detalles del Pokémon
            Text(
                text = "No se encontraron detalles para el Pokémon seleccionado.",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
// Modelo de datos para detalles del Pokémon
data class DetallesPokemon(
    val nombre: String,
    val imagenes: Int,
    val consola: String,
    val descripcion: String
)
// Función de ejemplo para obtener detalles del Pokémon
fun obtenerDetallesPokemon(nombrePokemon: String?): DetallesPokemon? {
    // Implementa lógica para buscar detalles del Pokémon basado en el nombre
    // Puedes obtener detalles de una com.example.proyectofinal.ui.theme.BBDD.getLista, base de datos, red, etc.
    // Aquí hay un ejemplo básico con una com.example.proyectofinal.ui.theme.BBDD.getLista estática
    return when (nombrePokemon) {
            "Pokémon Azul" -> DetallesPokemon(
                "Pokémon Azul",
                R.drawable.azul,
                "Game Boy",
                "Pokémon Azul.."
            )
            "Pokémon Rojo" -> DetallesPokemon(
                "Pokémon Rojo",
                R.drawable.rojo,
                "Game Boy",
                "Pokémon Rojo.."
            )
            "Pokémon Amarillo" -> DetallesPokemon(
                "Pokémon Amarillo",
                R.drawable.amarillo,
                "Game Boy",
                "Pokémon Amarillo.."
            )
            "Pokémon Oro" -> DetallesPokemon(
                "Pokémon Oro",
                R.drawable.oro,
                "Game Boy Color",
                "Pokémon Oro.."
            )
            "Pokémon Plata" -> DetallesPokemon(
                "Pokémon Plata",
                R.drawable.plata,
                "Game Boy Color",
                "Pokémon Plata.."
            )
            "Pokémon Cristal" -> DetallesPokemon(
                "Pokémon Cristal",
                R.drawable.crital,
                "Game Boy Color",
                "Pokémon Cristal.."
            )
            "Pokémon Rubí" -> DetallesPokemon(
                "Pokémon Rubí",
                R.drawable.rubi,
                "Game Boy Advance",
                "Pokémon Rubí.."
            )
            "Pokémon Zafiro" -> DetallesPokemon(
                "Pokémon Zafiro",
                R.drawable.zafiro,
                "Game Boy Advance",
                "Pokémon Zafiro.."
            )
            "Pokémon Rojo Fuego" -> DetallesPokemon(
                "Pokémon Rojo Fuego",
                R.drawable.rojo_fuego,
                "Game Boy Advance",
                "Pokémon Rojo Fuego.."
            )
            "Pokémon Verde Hoja" -> DetallesPokemon(
                "Pokémon Verde Hoja",
                R.drawable.verde_hoja,
                "Game Boy Advance",
                "Pokémon Verde Hoja.."
            )
            "Pokémon Esmeralda" -> DetallesPokemon(
                "Pokémon Esmeralda",
                R.drawable.esmeralda,
                "Game Boy Advance",
                "Pokémon Esmeralda.."
            )
            "Pokémon Diamante" -> DetallesPokemon(
                "Pokémon Diamante",
                R.drawable.diamante,
                "Nintendo DS",
                "Pokémon Diamante.."
            )
            "Pokémon Perla" -> DetallesPokemon(
                "Pokémon Perla",
                R.drawable.perla,
                "Nintendo DS",
                "Pokémon Perla.."
            )
            "Pokémon Platino" -> DetallesPokemon(
                "Pokémon Platino",
                R.drawable.platino,
                "Nintendo DS",
                "Pokémon Platino.."
            )
            "Pokémon HeartGold" -> DetallesPokemon(
                "Pokémon HeartGold",
                R.drawable.heartgold,
                "Nintendo DS",
                "Pokémon HeartGold.."
            )
            "Pokémon SoulSilver" -> DetallesPokemon(
                "Pokémon SoulSilver",
                R.drawable.soulsilver,
                "Nintendo DS",
                "Pokémon SoulSilver.."
            )
            "Pokémon Blanco" -> DetallesPokemon(
                "Pokémon Blanco",
                R.drawable.blanco,
                "Nintendo DS",
                "Pokémon Blanco.."
            )
            "Pokémon Negro" -> DetallesPokemon(
                "Pokémon Negro",
                R.drawable.negro,
                "Nintendo DS",
                "Pokémon Negro.."
            )
            "Pokémon Blanco 2" -> DetallesPokemon(
                "Pokémon Blanco 2",
                R.drawable.blanco2,
                "Nintendo DS",
                "Pokémon Blanco 2.."
            )
            "Pokémon Negro 2" -> DetallesPokemon(
                "Pokémon Negro 2",
                R.drawable.negro2,
                "Nintendo DS",
                "Pokémon Negro 2.."
            )
            "Pokémon X" -> DetallesPokemon(
                "Pokémon X",
                R.drawable.x,
                "Nintendo 3DS",
                "Pokémon X.."
            )
            "Pokémon Y" -> DetallesPokemon(
                "Pokémon Y",
                R.drawable.y,
                "Nintendo 3DS",
                "Pokémon Y.."
            )
            "Pokémon Alfa Zafiro" -> DetallesPokemon(
                "Pokémon Alfa Zafiro",
                R.drawable.alfa_zafiro,
                "Nintendo 3DS",
                "Pokémon Alfa Zafiro.."
            )
            "Pokémon Omega Rubí" -> DetallesPokemon(
                "Pokémon Omega Rubí",
                R.drawable.alfa_ruby,
                "Nintendo 3DS",
                "Pokémon Omega Rubí.."
            )
            "Pokémon Luna" -> DetallesPokemon(
                "Pokémon Luna",
                R.drawable.luna,
                "Nintendo 3DS",
                "Pokémon Luna.."
            )
            "Pokémon Sol" -> DetallesPokemon(
                "Pokémon Sol",
                R.drawable.sol,
                "Nintendo 3DS",
                "Pokémon Sol.."
            )
            "Pokémon Ultrasol" -> DetallesPokemon(
                "Pokémon Ultrasol",
                R.drawable.ultrasol,
                "Nintendo 3DS",
                "Pokémon Ultrasol.."
            )
            "Pokémon Ultraluna" -> DetallesPokemon(
                "Pokémon Ultraluna",
                R.drawable.ultraluna,
                "Nintendo 3DS",
                "Pokémon Ultraluna.."
            )
            "Pokémon: Let's Go, Eevee!" -> DetallesPokemon(
                "Pokémon: Let's Go, Eevee!",
                R.drawable.let_go_evee,
                "Nintendo Switch",
                "Pokémon: Let's Go, Eevee!.."
            )
            "Pokémon: Let's Go, Pikachu!" -> DetallesPokemon(
                "Pokémon: Let's Go, Pikachu!",
                R.drawable.let_go_pikachu,
                "Nintendo Switch",
                "Pokémon: Let's Go, Pikachu!.."
            )
            "Pokémon Escudo" -> DetallesPokemon(
                "Pokémon Escudo",
                R.drawable.escudo,
                "Nintendo Switch",
                "Pokémon Escudo.."
            )
            "Pokémon Espada" -> DetallesPokemon(
                "Pokémon Espada",
                R.drawable.espada,
                "Nintendo Switch",
                "Pokémon Espada.."
            )
            "Pokémon Diamante Brillante" -> DetallesPokemon(
                "Pokémon Diamante Brillante",
                R.drawable.diamante_brillante,
                "Nintendo Switch",
                "Pokémon Diamante Brillante.."
            )
            "Pokémon Perla Reluciente" -> DetallesPokemon(
                "Pokémon Perla Reluciente",
                R.drawable.perla_brillante,
                "Nintendo Switch",
                "Pokémon Perla Reluciente.."
            )
            "Pokémon Escarlata" -> DetallesPokemon(
                "Pokémon Escarlata",
                R.drawable.escarlata,
                "Nintendo Switch",
                "Pokémon Escarlata.."
            )
            "Pokémon Púrpura" -> DetallesPokemon(
                "Pokémon Púrpura",
                R.drawable.purpura,
                "Nintendo Switch",
                "Pokémon Púrpura.."
            )
            else -> null
        }
    }