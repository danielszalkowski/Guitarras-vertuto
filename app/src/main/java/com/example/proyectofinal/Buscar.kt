package com.example.proyectofinal

import android.graphics.Paint.Align
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Busqueda(navController: NavController){
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            query = query,
            onQueryChange = { query = it },
            onSearch = {
                active = false
            },
            active = active,
            onActiveChange = { active = it },
            placeholder = { Text(text = "Buscar guitarra por modelo")},
            trailingIcon = { IconButton(onClick = {
                active = false
                navController.popBackStack()
            }) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Salir")
            }}
        ) {

                if(query.isNotEmpty()){
                    val guitarrasFiltradas = listaGuitarras.filter { it.modelo.contains(query, true) }
                    LazyColumn{
                        items(guitarrasFiltradas) {(modelo) ->
                            GuitarraBuscarCard(modelo, navController )
                        }
                    }
                }
        }
    }
}

@Composable
fun GuitarraBuscarCard(modelo: String, navController: NavController){
    Card {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.Transparent)
                .clickable {
                    navController.navigate(AppScreens.pantallaDatos.route + "/" + modelo)
                }
        ) {
            Text(text = "$modelo", fontSize = 25.sp, modifier = Modifier.align(Alignment.CenterStart))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewBusqueda() {
    var navController = rememberNavController()
    Busqueda(navController)
}

