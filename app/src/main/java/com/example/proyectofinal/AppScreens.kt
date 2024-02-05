package com.example.proyectofinal

sealed class AppScreens(val route:String){
    object pantallaPrincipal: AppScreens("pantalla_principal")
    object pantallaDatos: AppScreens("pantalla_datos")
    object pantallaBuscar: AppScreens("pantalla_buscar")
    object pantallaAcusticas: AppScreens("pantalla_acusticas")
    object pantallaElectricas: AppScreens("pantalla_electricas")
    object pantallaGuitarrasBuscar: AppScreens("pantalla_guitarras_buscar")
    object pantallaSplash: AppScreens("pantalla_splash")
}