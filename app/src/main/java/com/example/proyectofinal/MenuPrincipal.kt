package com.example.proyectofinal

import FavGuitarGridView
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinal.ui.theme.ProyectoFinalTheme

@Composable
fun Principal(navController: NavController) {
    FuncionScaffold(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar() {
    TopAppBar(
        title = { Text("Guitarras") },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.LightGray
            , titleContentColor = Color.Black),
        navigationIcon = {
            IconButton(onClick = { }) { Icon(Icons.Filled.Menu, contentDescription = "Desc", tint = Color.Black) }
        },
        actions = {
            IconButton(onClick = {}) { Icon(Icons.Filled.Add, contentDescription = "Desc", tint = Color.Black) }
            Spacer(modifier = Modifier.size(6.dp))
            IconButton(onClick = {}) { Icon(Icons.Filled.Close, contentDescription = "Desc", tint = Color.Black) }
        },
    )
}

@Composable
fun MyBottomNavigation(currentScreen: Pantalla, onTabSelected: (Pantalla) -> Unit) {
    var index by rememberSaveable { mutableIntStateOf(0) }

    NavigationBar(
        containerColor = Color.LightGray,
        contentColor = Color.Black
    ) {
            NavigationBarItem(
                selected = currentScreen ==  Pantalla.MainMenu,
                onClick = {onTabSelected(Pantalla.MainMenu)},
                icon = { if(currentScreen ==  Pantalla.MainMenu) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Home Icon",
                        tint = Color.Black
                    )
                } else {
                    Icon(
                        imageVector = Icons.Outlined.Home,
                        contentDescription = "Home Icon",
                        tint = Color.Black
                    )
                }

                },
                label = {
                    Text(text = "Guitarras",
                    color = Color.Black)
                }
            )
            NavigationBarItem(
                selected = currentScreen ==  Pantalla.Favoritas,
                onClick = {onTabSelected(Pantalla.Favoritas)},
                icon = {
                    if(currentScreen ==  Pantalla.Favoritas) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Person Icon",
                            tint = Color.Black
                        )
                    }
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = "Person Icon",
                        tint = Color.Black
                    )
                },
                label = { Text(text = "Favoritas",
                    color = Color.Black)
                }
            )


        }

    }


@Composable
fun FuncionScaffold(navController: NavController) {
    var currentScreen by rememberSaveable { mutableStateOf(Pantalla.MainMenu) }
    Scaffold(
        modifier = Modifier.background(Color.White),
        topBar = { MyTopAppBar() },
        content = { innerPadding ->
            when (currentScreen) {
                Pantalla.MainMenu -> GuitarGridView(innerPadding, navController)
                Pantalla.Favoritas -> FavGuitarGridView(innerPadding, navController)
            }
        },
        bottomBar = { MyBottomNavigation(currentScreen, onTabSelected = { screen -> currentScreen = screen }) },
    )
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GuitarGridView(innerPadding: PaddingValues, navController: NavController) {
    val context = LocalContext.current
    LazyVerticalGrid(
        modifier = Modifier
            .consumeWindowInsets(innerPadding)
            .padding(8.dp)
            .background(Color.White),
        columns = GridCells.Fixed(1),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = innerPadding,
        content = {
            items(listaGuitarras) {
                ItemGuitar(it) {
                    navController.navigate(route = AppScreens.pantallaDatos.route+"/"+it.modelo)
                }
            }
        })
}

@Composable
fun ItemGuitar(guitarra: GuitarraElectrica, onItemSelected: (GuitarraElectrica) -> Unit) {
    var state = remember { mutableStateOf(false) }
    Card(border = BorderStroke(2.dp, Color.Black),
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .width(250.dp)
            .clickable { onItemSelected(guitarra) }) {
        Column() {
            Image(
                painter = painterResource(id = guitarra.imagen),
                contentDescription = "Guitar image",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = guitarra.modelo,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = guitarra.color,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 12.sp
            )
            Row {
                Column(modifier = Modifier.weight(1f)) {
                    val checkedState = remember { mutableStateOf(guitarra.favorito) }
                    guitarra.favorito = checkedState.value
                    var interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
                    Box(
                        modifier = Modifier
                            .padding(12.dp)
                            .size(24.dp)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null,
                                onClick = { checkedState.value = !checkedState.value } )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.checkbox_off_icon),
                            contentDescription = "Checked"
                        )
                        if (checkedState.value) {
                            guitarra.favorito = checkedState.value
                            Image(
                                painter = painterResource(id = R.drawable.checkbox_on_icon),
                                contentDescription = "Checked"
                            )
                        }
                    }
                }
                Column(modifier =
                Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)) {
                    Text(
                        text = guitarra.precio,
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(6.dp),
                        fontSize = 10.sp
                    )
                }
            }
        }
    }
}

@Composable
fun CustomCheckbox(
    isChecked: Boolean
) {
    val checkedState = remember { mutableStateOf(isChecked) }
    Box(
        modifier = Modifier
            .padding(12.dp)
            .size(24.dp)
            .clickable { checkedState.value = !checkedState.value }
    ) {
        Image(
            painter = painterResource(id = R.drawable.checkbox_off_icon),
            contentDescription = "Checked"
        )
        if (checkedState.value) {
            Image(
                painter = painterResource(id = R.drawable.checkbox_on_icon),
                contentDescription = "Checked"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMenu() {
    ProyectoFinalTheme {
        val navController = rememberNavController()
        Principal(navController)
    }
}