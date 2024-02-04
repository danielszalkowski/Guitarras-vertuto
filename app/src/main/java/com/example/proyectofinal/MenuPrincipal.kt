package com.example.proyectofinal

import FavGuitarGridView
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.compose.ProyectoFinalTheme
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun Principal(navController: NavController) {
    FuncionScaffold(navController)
}

@Composable
fun FuncionScaffold(navController: NavController) {
    var currentScreen by rememberSaveable { mutableStateOf(Pantalla.MainMenu) }
    val scope = rememberCoroutineScope()
    var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                MyNavigationDrawer(navController) { scope.launch { drawerState.close() } }
            }
        },
        gesturesEnabled = true
    ) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.background(MaterialTheme.colorScheme.primary),
            topBar =  { MyTopAppBar(navController) { scope.launch { drawerState.open() } } },
            content = { innerPadding ->
                when (currentScreen) {
                    Pantalla.MainMenu -> GuitarGridView(innerPadding, navController)
                    Pantalla.Favoritas -> FavGuitarGridView(innerPadding, navController)
                }
            },
            bottomBar = {
                MyBottomNavigation(
                    currentScreen,
                    onTabSelected = { screen -> currentScreen = screen })
            },
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(navController: NavController, onClickDrawer: () -> Unit) {
    TopAppBar(
        title = { Text("Guitarras") },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer
            , titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer),
        navigationIcon = {
            IconButton(onClick = { onClickDrawer() }) { Icon(Icons.Filled.Menu, contentDescription = "Desc", tint = MaterialTheme.colorScheme.onPrimaryContainer) }
        },
        actions = {
            IconButton(onClick = { navController.navigate(route = AppScreens.pantallaBuscar.route) }) { Icon(Icons.Filled.Search, contentDescription = "Desc", tint = MaterialTheme.colorScheme.onPrimaryContainer) }
        },
    )
}

@Composable
fun MyBottomNavigation(currentScreen: Pantalla, onTabSelected: (Pantalla) -> Unit) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) {
            NavigationBarItem(
                alwaysShowLabel = false,
                selected = currentScreen ==  Pantalla.MainMenu,
                onClick = {onTabSelected(Pantalla.MainMenu)},
                icon = { if(currentScreen ==  Pantalla.MainMenu) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Home Icon",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                } else {
                    Icon(
                        imageVector = Icons.Outlined.Home,
                        contentDescription = "Home Icon",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

                },
                label = {
                    Text(text = "Inicio",
                    color = MaterialTheme.colorScheme.onPrimaryContainer)
                }
            )
            NavigationBarItem(
                alwaysShowLabel = false,
                selected = currentScreen ==  Pantalla.Favoritas,
                onClick = {onTabSelected(Pantalla.Favoritas)},
                icon = {
                    if(currentScreen ==  Pantalla.Favoritas) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Fav Icon",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = "Fav Icon",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                },
                label = { Text(text = "Favoritas",
                    color = MaterialTheme.colorScheme.onPrimaryContainer)
                }
            )


        }

    }




@Composable
fun MyNavigationDrawer(navController: NavController, onCloseDrawer: () -> Unit) {
    Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "Guitarras Eléctricas",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .clickable {
                        onCloseDrawer()
                        navController.navigate(AppScreens.pantallaElectricas.route)
                    },
                fontSize = 18.sp,
                color = (MaterialTheme.colorScheme.onPrimaryContainer)
            )
            Text(
                text = "Guitarras Acústicas",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .clickable {
                        onCloseDrawer()
                        navController.navigate(AppScreens.pantallaAcusticas.route)
                               },
                fontSize = 18.sp,
                color = (MaterialTheme.colorScheme.onPrimaryContainer)
            )
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GuitarGridView(innerPadding: PaddingValues, navController: NavController) {
    LazyVerticalGrid(
        modifier = Modifier
            .consumeWindowInsets(innerPadding)
            .padding(8.dp),
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
    Card(border = BorderStroke(1.dp, Color.Black),
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .width(250.dp)
            .clickable { onItemSelected(guitarra) }) {
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer)) {
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
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                text = guitarra.color,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
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
                                onClick = { checkedState.value = !checkedState.value })
                    ) {
                        if (isSystemInDarkTheme()){
                            Image(
                                painter = painterResource(id = R.drawable.checkbox_off_icon_dark),
                                contentDescription = "Checked"
                            )
                        } else {
                            Image(
                                painter = painterResource(id = R.drawable.checkbox_off_icon_light),
                                contentDescription = "Checked"
                            )
                        }
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
                    var texto = "Sin puntuar"
                    if (guitarra.puntuacion != null) {
                        texto = "${guitarra.puntuacion?.roundToInt()}/10"
                    }
                    Text(
                        text = texto,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(6.dp),
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
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
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
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