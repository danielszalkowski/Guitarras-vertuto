package com.example.proyectofinal

import android.graphics.ColorFilter
import android.graphics.Paint.Align
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.proyectofinal.ui.theme.ProyectoFinalTheme
import kotlinx.coroutines.launch
import java.security.Principal

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoFinalTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Principal()
                }
            }
        }
    }
}

@Composable
fun Principal() {
    FuncionScaffold()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar() {
    TopAppBar(
        title = { Text("Guitarras Eléctricas")},
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
fun MyBottomNavigation() {
    var index by rememberSaveable { mutableIntStateOf(0) }
    NavigationBar(
        containerColor = Color.LightGray,
        contentColor = Color.Black
    ) {
        NavigationBarItem(
            selected = index == 0,
            onClick = { index = 0  },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home Icon",
                    tint = Color.Black
                )
            },
            label = { Text(text ="Home",
                color = Color.Black)}
        )
        NavigationBarItem(
            selected = index == 1,
            onClick = { index = 1 },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Person Icon",
                    tint = Color.Black
                )
            },
            label = { Text(text = "Person",
                color = Color.Black)
            }
        )
    }
}

@Composable
fun FuncionScaffold() {
    Scaffold(
        topBar = { MyTopAppBar() },
        content = { innerPadding ->
            GuitarGridView(innerPadding)
        },
        bottomBar = { MyBottomNavigation() },
    )
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GuitarGridView(innerPadding: PaddingValues) {
    val context = LocalContext.current
    LazyVerticalGrid(
        modifier = Modifier
            .consumeWindowInsets(innerPadding)
            .padding(8.dp),
        columns = GridCells.Fixed(1),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = innerPadding,
        content = {
            items(getGuitars()) {
                ItemHero(it) {
                    Toast.makeText(context, it.modelo, Toast.LENGTH_SHORT).show()
                }
            }
        })
}
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MyContent(innerPadding: PaddingValues) {
    val colors = listOf(Color(0xFFffd7d7.toInt()),
        Color(0xFFffe9d6.toInt()),
        Color(0xFFfffbd0.toInt()),
        Color(0xFFe3ffd9.toInt()),
        Color(0xFFd0fff8.toInt()))
    LazyColumn(
        modifier = Modifier.consumeWindowInsets(innerPadding),
        contentPadding = innerPadding
    ) {
        items(100) { count ->
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(colors[count % colors.size])
            ) { Text(text = "Fila $count") }
        }
    }
}

@Composable
fun ItemHero(guitarra: GuitarraElectrica, onItemSelected: (GuitarraElectrica) -> Unit) {
    var state by rememberSaveable { mutableStateOf(false) }
    Card(border = BorderStroke(2.dp, Color.Black),
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .width(250.dp)
            .clickable { onItemSelected(guitarra) }) {
        Column() {
            Image(
                painter = painterResource(id = guitarra.imagen),
                contentDescription = "SuperHero Avatar",
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
                Text(
                    text = guitarra.precio,
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(6.dp),
                    fontSize = 10.sp
                )
                Checkbox(
                    checked = guitarra.favorito,
                    onCheckedChange = { guitarra.favorito = !guitarra.favorito },
                    enabled = true,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .offset(0.dp, -25.dp)
                )

        }
    }
}

fun getGuitars(): List<GuitarraElectrica> {
    return listOf(
        GuitarraElectrica("ESP LTD EX-400", "Blanco", 22, "1000€", R.drawable.ltdex400),
        GuitarraElectrica("Harley Benton CST-24", "Negro", 24, "250€",R.drawable.hbentoncst24),
        GuitarraElectrica("PRS SE Mikael Akerfeldt", "Madera", 24, "800€", R.drawable.opeth),
        GuitarraElectrica("ESP LTD KH-602", "Morado brillante", 24, "1000€", R.drawable.ltdkh602)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProyectoFinalTheme {
        Principal()
    }
}