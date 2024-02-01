package com.example.proyectofinal

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.pantallaPrincipal.route) {
        composable(route = AppScreens.pantallaPrincipal.route) {
            FuncionScaffold(navController)
        }
        composable(route = AppScreens.pantallaDatos.route + "/{guitarra}",
            arguments = listOf(navArgument(name = "guitarra"){
                type = NavType.StringType
            }))
        {
            DatosGuitarra(navController, it.arguments?.getString("guitarra"))
        }
        composable(route = AppScreens.pantallaBuscar.route){
            Busqueda(navController)
        }
    }
}