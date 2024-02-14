package Navegacion

import CreacionDePokemon.pantallaCreacionPokemon
import PantallaDetalles.pantallaDetalles
import PantallaPrincipal.pantallaInicio
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyectofinal.ui.theme.inicio.PantallaInicioSesion
import com.example.proyectofinal.ui.theme.inicio.PantallaRegistro

@Composable
fun GrafoNavegacion() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "login") {
        composable("login") {
            PantallaInicioSesion(navController)
        }
        composable("pantallaInicio") { backStackEntry ->
            pantallaInicio(navController)
        }
        composable(
            "creacionP/{consola}")
         { backStackEntry ->
            val pokemonNombre = backStackEntry.arguments?.getString("consola")
             pantallaCreacionPokemon(navController)

        }
        composable("pantallaRegistro") { backStackEntry ->
            PantallaRegistro(navController)
        }
        composable(
            "pantallaDetalles/{pokemonNombre}",
            arguments = listOf(navArgument("pokemonNombre") { type = NavType.StringType })
        ) { backStackEntry ->
            val pokemonNombre = backStackEntry.arguments?.getString("pokemonNombre")
            pantallaDetalles(navController,pokemonNombre)

        }

        // Otras composables y rutas según sea necesario creacionP/{consola}
    }
}