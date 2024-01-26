package com.example.proyectofinal

import androidx.annotation.DrawableRes

data class GuitarraElectrica(val modelo: String, val color: String, val nTrastes: Int, val precio: String, @DrawableRes var imagen: Int, var favorito: Boolean = false)

