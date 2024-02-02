package com.example.proyectofinal

import androidx.annotation.DrawableRes

data class GuitarraElectrica(val modelo: String,
                             val color: String,
                             val nTrastes: Int,
                             val precio: String,
                             @DrawableRes var imagen: Int,
                             var favorito: Boolean = false,
                             var tipo: String,
                             var puntuacion: Int = 0
    )

var listaGuitarras = mutableListOf(
    GuitarraElectrica("ESP LTD EX-400", "Blanco", 22, "1000€", R.drawable.ltdex400, tipo = "Electrica"),
    GuitarraElectrica("Harley Benton CST-24", "Negro", 24, "250€",R.drawable.hbentoncst24, tipo = "Electrica"),
    GuitarraElectrica("Ibanez TOD10N", "Negro mate", 24, "700€", R.drawable.ibaneztod10n, tipo = "Acustica"),
    GuitarraElectrica("PRS SE Mikael Akerfeldt", "Madera roja", 24, "800€", R.drawable.opeth, tipo = "Electrica"),
    GuitarraElectrica("Ibanez PF-10-BK", "Negro", 20, "400€", R.drawable.ibanezpf10bk, tipo = "Acustica"),
    GuitarraElectrica("ESP LTD KH-602", "Morado brillante", 24, "1000€", R.drawable.ltdkh602, tipo = "Electrica"),
    GuitarraElectrica("Music Man John Petrucci 6", "Morado", 24, "1000€", R.drawable.musicmanjp6, tipo = "Electrica"),
    GuitarraElectrica("Squier SA-105CE", "Madera clara", 20, "150€", R.drawable.squiersa105ce, tipo = "Acustica"),
    GuitarraElectrica("Gibson Flying V", "Madera natural", 22, "1500€", R.drawable.gibsonflyingv, tipo = "Electrica"),
    GuitarraElectrica("José Gómez C60", "Madera clara", 20, "365€", R.drawable.josegomezc60, tipo = "Acustica")
    )