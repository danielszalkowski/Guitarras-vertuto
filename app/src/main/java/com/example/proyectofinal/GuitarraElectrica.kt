package com.example.proyectofinal

import androidx.annotation.DrawableRes

data class GuitarraElectrica(val modelo: String, val color: String, val nTrastes: Int, val precio: String, @DrawableRes var imagen: Int, var favorito: Boolean = false)

var listaGuitarras = mutableListOf(
    GuitarraElectrica("ESP LTD EX-400", "Blanco", 22, "1000€", R.drawable.ltdex400),
    GuitarraElectrica("Harley Benton CST-24", "Negro", 24, "250€",R.drawable.hbentoncst24),
    GuitarraElectrica("PRS SE Mikael Akerfeldt", "Madera", 24, "800€", R.drawable.opeth),
    GuitarraElectrica("ESP LTD KH-602", "Morado brillante", 24, "1000€", R.drawable.ltdkh602)
)