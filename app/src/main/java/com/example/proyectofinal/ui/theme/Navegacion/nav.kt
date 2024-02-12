package Navegacion

import CreacionDePokemon.pantallaCreacionPokemon
import PantallaPrincipal.pantallaInicio
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun GrafoNavegacion() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "pantallaInicio") {
        composable("pantallaInicio") { pantallaInicio(navController) }
        composable("pantallaCreacionPokemon/{consola}") { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre")
            pantallaCreacionPokemon(navController, nombre)
        }
        // Otras composables y rutas según sea necesario
    }
}
