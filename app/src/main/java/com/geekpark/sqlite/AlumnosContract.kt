package com.geekpark.sqlite

import android.provider.BaseColumns

//Columnas que tendrá nuestra bd
class AlumnosContract {

    companion object{
        val VERSION = 1
        //Poder mapear variables en columnas
        class Entrada:BaseColumns{
            companion object{
                val NOMBRE_TABLA = "alumnos"

                val COLUMNA_ID = "id"
                val COLUMNA_NOMBRE = "nombre"
            }
        }
    }

}