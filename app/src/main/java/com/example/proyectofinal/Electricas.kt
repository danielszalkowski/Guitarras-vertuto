package com.example.proyectofinal

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ElectricGridView(innerPadding: PaddingValues, navController: NavController) {
    Scaffold (
        topBar = {
            AppBarElectricas(navController)
        }
    ) {
    ContentElectricas(innerPadding, navController)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContentElectricas(innerPadding: PaddingValues, navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(MaterialTheme.colorScheme.primary)
            .padding(top = 56.dp)
    ) {


        LazyVerticalGrid(
            modifier = Modifier
                .consumeWindowInsets(innerPadding)
                .padding(8.dp),
            columns = GridCells.Fixed(1),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = innerPadding,
            content = {
                items(listaGuitarras.filter { it.tipo.equals("Electrica") }) {
                    ItemGuitar(it) {
                        navController.navigate(route = AppScreens.pantallaDatos.route + "/" + it.modelo)
                    }
                }
            })
    }
}
@Composable
fun AppBarElectricas(navController: NavController) {
    Box (
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .height(64.dp)
    ){
        IconButton(
            onClick = { navController.navigate(AppScreens.pantallaPrincipal.route) },
            modifier = Modifier
                .align(Alignment.CenterStart)
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Volver", tint = MaterialTheme.colorScheme.onPrimaryContainer)
        }
        Text(
            text = "Guitarras eléctricas",
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.Center),
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}
