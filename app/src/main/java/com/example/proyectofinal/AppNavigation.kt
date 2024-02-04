package com.example.proyectofinal

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
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
        composable(route = AppScreens.pantallaAcusticas.route){
            AcousticGridView(innerPadding = PaddingValues(2.dp), navController = navController)
        }
        composable(route = AppScreens.pantallaElectricas.route){
            ElectricGridView(innerPadding = PaddingValues(2.dp), navController = navController)
        }
        composable(route = AppScreens.pantallaGuitarrasBuscar.route + "/{guitarra}",
            arguments = listOf(navArgument(name = "guitarra"){
                type = NavType.StringType
            }))
        {
            //ElectricGridView(innerPadding = PaddingValues(2.dp), navController = navController)
            BusquedaGridView(PaddingValues(2.dp), navController, it.arguments?.getString("guitarra")!!)
        }
    }
}