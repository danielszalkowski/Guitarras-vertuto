package com.example.proyectofinal

sealed class AppScreens(val route:String){
    object pantallaPrincipal: AppScreens("pantalla_principal")
    object pantallaDatos: AppScreens("pantalla_datos")
}