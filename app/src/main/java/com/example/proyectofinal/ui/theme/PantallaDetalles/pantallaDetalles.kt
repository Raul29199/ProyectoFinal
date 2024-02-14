package PantallaDetalles

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectofinal.R

@Composable
fun pantallaDetalles(navController: NavController, pokemon: String?) {
    // Aquí deberías tener una lógica para obtener detalles del Pokémon basado en su nombre
    val detallesPokemon = obtenerDetallesPokemon(pokemon)
    var mediaPlayer: MediaPlayer? by remember { mutableStateOf(null) }
    var isPlaying by remember { mutableStateOf(false) }
    fun toggleMusic(context: Context) {
        if (isPlaying) {
            mediaPlayer?.pause()
        } else {
            mediaPlayer = MediaPlayer.create(context, R.raw.medio)
            mediaPlayer?.start()
        }
        isPlaying = !isPlaying
    }

// Ícono de música en la esquina superior derecha
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.End
    ) {
        // Necesitas pasar el contexto de la aplicación al hacer clic en el botón
        val context = LocalContext.current
        IconButton(
            onClick = {
                toggleMusic(context)
            }
        ) {
            val icon = if (isPlaying) {
                Icons.Outlined.Close
            } else {
                Icons.Outlined.PlayArrow
            }
            Icon(imageVector = icon, contentDescription = null)
        }
    }
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
                "Pokémon Azul.. "+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."
            )
            "Pokémon Rojo" -> DetallesPokemon(
                "Pokémon Rojo",
                R.drawable.rojo,
                "Game Boy",
                "Pokémon Rojo.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon Amarillo" -> DetallesPokemon(
                "Pokémon Amarillo",
                R.drawable.amarillo,
                "Game Boy",
                "Pokémon Amarillo.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon Oro" -> DetallesPokemon(
                "Pokémon Oro",
                R.drawable.oro,
                "Game Boy Color",
                "Pokémon Oro.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon Plata" -> DetallesPokemon(
                "Pokémon Plata",
                R.drawable.plata,
                "Game Boy Color",
                "Pokémon Plata.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon Cristal" -> DetallesPokemon(
                "Pokémon Cristal",
                R.drawable.crital,
                "Game Boy Color",
                "Pokémon Cristal.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon Rubí" -> DetallesPokemon(
                "Pokémon Rubí",
                R.drawable.rubi,
                "Game Boy Advance",
                "Pokémon Rubí.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon Zafiro" -> DetallesPokemon(
                "Pokémon Zafiro",
                R.drawable.zafiro,
                "Game Boy Advance",
                "Pokémon Zafiro.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon Rojo Fuego" -> DetallesPokemon(
                "Pokémon Rojo Fuego",
                R.drawable.rojo_fuego,
                "Game Boy Advance",
                "Pokémon Rojo Fuego.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon Verde Hoja" -> DetallesPokemon(
                "Pokémon Verde Hoja",
                R.drawable.verde_hoja,
                "Game Boy Advance",
                "Pokémon Verde Hoja.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon Esmeralda" -> DetallesPokemon(
                "Pokémon Esmeralda",
                R.drawable.esmeralda,
                "Game Boy Advance",
                "Pokémon Esmeralda.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon Diamante" -> DetallesPokemon(
                "Pokémon Diamante",
                R.drawable.diamante,
                "Nintendo DS",
                "Pokémon Diamante.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon Perla" -> DetallesPokemon(
                "Pokémon Perla",
                R.drawable.perla,
                "Nintendo DS",
                "Pokémon Perla.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon Platino" -> DetallesPokemon(
                "Pokémon Platino",
                R.drawable.platino,
                "Nintendo DS",
                "Pokémon Platino.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon HeartGold" -> DetallesPokemon(
                "Pokémon HeartGold",
                R.drawable.heartgold,
                "Nintendo DS",
                "Pokémon HeartGold.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon SoulSilver" -> DetallesPokemon(
                "Pokémon SoulSilver",
                R.drawable.soulsilver,
                "Nintendo DS",
                "Pokémon SoulSilver.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon Blanco" -> DetallesPokemon(
                "Pokémon Blanco",
                R.drawable.blanco,
                "Nintendo DS",
                "Pokémon Blanco.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon Negro" -> DetallesPokemon(
                "Pokémon Negro",
                R.drawable.negro,
                "Nintendo DS",
                "Pokémon Negro.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon Blanco 2" -> DetallesPokemon(
                "Pokémon Blanco 2",
                R.drawable.blanco2,
                "Nintendo DS",
                "Pokémon Blanco 2.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon Negro 2" -> DetallesPokemon(
                "Pokémon Negro 2",
                R.drawable.negro2,
                "Nintendo DS",
                "Pokémon Negro 2.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon X" -> DetallesPokemon(
                "Pokémon X",
                R.drawable.x,
                "Nintendo 3DS",
                "Pokémon X.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon Y" -> DetallesPokemon(
                "Pokémon Y",
                R.drawable.y,
                "Nintendo 3DS",
                "Pokémon Y.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon Alfa Zafiro" -> DetallesPokemon(
                "Pokémon Alfa Zafiro",
                R.drawable.alfa_zafiro,
                "Nintendo 3DS",
                "Pokémon Alfa Zafiro.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon Omega Rubí" -> DetallesPokemon(
                "Pokémon Omega Rubí",
                R.drawable.alfa_ruby,
                "Nintendo 3DS",
                "Pokémon Omega Rubí.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon Luna" -> DetallesPokemon(
                "Pokémon Luna",
                R.drawable.luna,
                "Nintendo 3DS",
                "Pokémon Luna.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon Sol" -> DetallesPokemon(
                "Pokémon Sol",
                R.drawable.sol,
                "Nintendo 3DS",
                "Pokémon Sol.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon Ultrasol" -> DetallesPokemon(
                "Pokémon Ultrasol",
                R.drawable.ultrasol,
                "Nintendo 3DS",
                "Pokémon Ultrasol.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon Ultraluna" -> DetallesPokemon(
                "Pokémon Ultraluna",
                R.drawable.ultraluna,
                "Nintendo 3DS",
                "Pokémon Ultraluna.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon: Let's Go, Eevee!" -> DetallesPokemon(
                "Pokémon: Let's Go, Eevee!",
                R.drawable.let_go_evee,
                "Nintendo Switch",
                "Pokémon: Let's Go, Eevee!.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon: Let's Go, Pikachu!" -> DetallesPokemon(
                "Pokémon: Let's Go, Pikachu!",
                R.drawable.let_go_pikachu,
                "Nintendo Switch",
                "Pokémon: Let's Go, Pikachu!.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon Escudo" -> DetallesPokemon(
                "Pokémon Escudo",
                R.drawable.escudo,
                "Nintendo Switch",
                "Pokémon Escudo.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon Espada" -> DetallesPokemon(
                "Pokémon Espada",
                R.drawable.espada,
                "Nintendo Switch",
                "Pokémon Espada.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon Diamante Brillante" -> DetallesPokemon(
                "Pokémon Diamante Brillante",
                R.drawable.diamante_brillante,
                "Nintendo Switch",
                "Pokémon Diamante Brillante.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon Perla Reluciente" -> DetallesPokemon(
                "Pokémon Perla Reluciente",
                R.drawable.perla_brillante,
                "Nintendo Switch",
                "Pokémon Perla Reluciente.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon Escarlata" -> DetallesPokemon(
                "Pokémon Escarlata",
                R.drawable.escarlata,
                "Nintendo Switch",
                "Pokémon Escarlata.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            "Pokémon Púrpura" -> DetallesPokemon(
                "Pokémon Púrpura",
                R.drawable.purpura,
                "Nintendo Switch",
                "Pokémon Púrpura.."+"Los videojuegos de Pokémon constituyen la parte fundamental de la franquicia Pokémon y comprenden todos los videojuegos lanzados al mercado desde sus inicios en 1996. Pokémon nació originalmente con la distribución de Pokémon Verde y Pokémon Rojo en Japón, el primer videojuego de la franquicia. A partir de los videojuegos surgieron las otras grandes ramas de Pokémon: anime, manga y Trading Card Game. Todavía hoy, son los videojuegos los que marcan la pauta dominante, de manera que las anteriores derivan su contenido de los videojuegos.\n" +
                        "\n" +
                        "La clasificación de videojuegos de Pokémon más importante es aquella que distingue entre videojuegos principales y spin-offs o videojuegos secundarios. Pertenecen a la primera categoría los videojuegos en los que el jugador es un entrenador Pokémon, que se embarca en una aventura Pokémon que consiste en reunir una serie de medallas con el objetivo último de participar en la Liga Pokémon y atrapar a todos los Pokémon. Se consideran \"principales\" porque siguen el modelo establecido en Pokémon Verde y Pokémon Rojo, son los más abundantes y variados en contenido, y son los que inauguran cada nueva generación de la franquicia. Estos videojuegos son la esencia de Pokémon y también los más vendidos."

            )
            else -> null
        }
    }