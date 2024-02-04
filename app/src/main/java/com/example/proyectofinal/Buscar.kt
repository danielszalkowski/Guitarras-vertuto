package com.example.proyectofinal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.primary)) {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary),
            query = query,
            onQueryChange = { query = it },
            onSearch = {
                //active = false
                navController.navigate(AppScreens.pantallaGuitarrasBuscar.route+"/"+query)
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
                            GuitarraBuscarList(modelo, navController )
                        }
                    }
                }
        }
    }
}

@Composable
fun GuitarraBuscarList(modelo: String, navController: NavController){
    var guitar = listaGuitarras.first { it.modelo == modelo }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(top = 4.dp, bottom = 4.dp)
                .clickable {
                    navController.navigate(AppScreens.pantallaDatos.route + "/" + modelo)
                }
        ) {
            Image(painter = painterResource(id = guitar.imagen), contentDescription = "Imagen guitarra",
                Modifier
                    .size(50.dp)
                    .padding(start = 8.dp))
            Text(text = "$modelo", fontSize = 25.sp, modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 58.dp))
        }
}


@Preview(showBackground = true)
@Composable
fun PreviewBusqueda() {
    var navController = rememberNavController()
    Busqueda(navController)
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SearchGuitarGridView(innerPadding: PaddingValues, navController: NavController, query: String) {
    LazyVerticalGrid(
        modifier = Modifier
            .consumeWindowInsets(innerPadding)
            .padding(8.dp),
        columns = GridCells.Fixed(1),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = innerPadding,
        content = {
            items(listaGuitarras.filter { it.modelo.equals(query) }) {
                ItemGuitar(it) {
                    navController.navigate(route = AppScreens.pantallaDatos.route+"/"+it.modelo)
                }
            }
        })
}
