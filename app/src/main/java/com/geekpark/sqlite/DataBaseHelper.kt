package com.geekpark.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/*Permitir poder inicilizar nuestra bd y crear nuestra tabla
extendemos de clase SQLiteOpenHelper, permitir transformar mi clase para crear mi bd y tabla
* */
class DataBaseHelper(context:Context): SQLiteOpenHelper
    (context, AlumnosContract.Companion.Entrada.NOMBRE_TABLA,null,AlumnosContract.Companion.VERSION) {

    companion object{
        val CREATE_ALUMNOS_TABLA = "CREATE TABLE "+AlumnosContract.Companion.Entrada.NOMBRE_TABLA +
                " ("+AlumnosContract.Companion.Entrada.COLUMNA_ID + " TEXT PRIMARY KEY, "+
                AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE + " TEXT )"

        val REMOVE_ALUMNOS_TABLA = "DROP TABLE IF EXISTS "+AlumnosContract.Companion.Entrada.NOMBRE_TABLA
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_ALUMNOS_TABLA)
    }

    //Cade vez que necesitamos actualiza base de datos, o  cambiar versión, no es muy común
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(REMOVE_ALUMNOS_TABLA)
        onCreate(db)
    }
}