package com.example.proyectofinal

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlin.math.roundToInt

@Composable
fun DatosGuitarra(navController: NavController, guitarra: String?) {
    val guitar = listaGuitarras.first { it.modelo == guitarra }
    var flotador = 0f
    if (guitar.puntuacion != null) {
        flotador = guitar.puntuacion!!
    }
    var sliderValue = remember { mutableStateOf(flotador/10) }
    Box(
        modifier = Modifier
            .fillMaxSize(1f)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    ) {
        val context  = LocalContext.current
        IconButton(
            onClick = { navController.navigate(AppScreens.pantallaPrincipal.route) },
            modifier = Modifier.align(Alignment.TopEnd)

        ) {
            Icon(imageVector = Icons.Default.Close, contentDescription = "Cerrar", tint = MaterialTheme.colorScheme.onPrimaryContainer)
        }

        Column() {
            Image(
                painter = painterResource(id = guitar.imagen),
                contentDescription = "Guitar image",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                contentScale = ContentScale.Crop,
            )
            Text(
                fontWeight = FontWeight.Bold,
                text = guitar.modelo,
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp),
                fontSize = 25.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = "· Color: ${guitar.color}",
                modifier = Modifier.padding(start = 10.dp),
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = "· Número de trastes: ${guitar.nTrastes}",
                modifier = Modifier.padding(start = 10.dp),
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = "· Precio: ${guitar.precio}",
                modifier = Modifier.padding(start = 10.dp),
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Text(
                text = "Puntuación: ${(sliderValue.value * 10).roundToInt()}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(start = 10.dp, top = 16.dp)
                    .align(Alignment.CenterHorizontally),
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold
            )
            Slider(
                steps = 10,
                value = sliderValue.value,
                onValueChange = { sliderValue.value = it },
                modifier = Modifier.padding(horizontal = 32.dp)
            )
            Button(
                onClick = {
                    guitar.puntuacion = sliderValue.value * 10
                    Toast.makeText(context,
                        "Puntuación asignada",
                        Toast.LENGTH_LONG).show()
                          },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Aplicar puntuación", color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DatosPreview(){
    val navController = rememberNavController()
    DatosGuitarra(navController, "ESP LTD EX-400")
}