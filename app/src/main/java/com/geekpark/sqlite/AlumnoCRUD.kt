package com.geekpark.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class AlumnoCRUD(context: Context) {

    //Nuestro objeto de la clase credada
    private var helper:DataBaseHelper? = null

    init {
        helper = DataBaseHelper(context)
    }

    fun newAlumno(item:Alumno){
        //Nos permite escribir sobre una base de datos (habilitar)
        val db:SQLiteDatabase = helper?.writableDatabase!!

        //Mapeo de columnos con valores a insertar
        val values = ContentValues()
        values.put(AlumnosContract.Companion.Entrada.COLUMNA_ID,item.id)
        values.put(AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE,item.nombre)

        //Insertar nueva fila en la tabla
        val newRowId = db.insert(AlumnosContract.Companion.Entrada.NOMBRE_TABLA,null,values)
        db.close()
    }

    fun getAlumnos(): ArrayList<Alumno>{
        val items : ArrayList<Alumno> = ArrayList()

        //Abrir db en modo lectura
        val db:SQLiteDatabase = helper?.readableDatabase!!

        //Especificar columnas que quiero consultar
        var columnas = arrayOf(AlumnosContract.Companion.Entrada.COLUMNA_ID,AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE)
        //Crear un cursor para recorrer la tabla
        val c:Cursor = db.query(AlumnosContract.Companion.Entrada.NOMBRE_TABLA,
                        columnas,
                        null,null,null,null,null)

        //Hacer el recorrido del cursor en la tabla
        while (c.moveToNext()){
            items.add(
                Alumno(
                    c.getString(c.getColumnIndexOrThrow(AlumnosContract.Companion.Entrada.COLUMNA_ID)),
                    c.getString(c.getColumnIndexOrThrow(AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE))
                    ))
        }

        //Cerrar database
        db.close()
        return items
    }

    fun getAlumno(id:String):Alumno{
        var item:Alumno? = null

        val db:SQLiteDatabase = helper?.readableDatabase!!
        var columnas = arrayOf(AlumnosContract.Companion.Entrada.COLUMNA_ID,AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE)
        val c:Cursor = db.query(AlumnosContract.Companion.Entrada.NOMBRE_TABLA,
            columnas,
            " id=?", arrayOf(id),null,null,null)

        while (c.moveToNext()){
            item = Alumno(
                    c.getString(c.getColumnIndexOrThrow(AlumnosContract.Companion.Entrada.COLUMNA_ID)),
                    c.getString(c.getColumnIndexOrThrow(AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE))
                )
        }

        c.close()
        return item!!
    }

    fun updateAlumno(item:Alumno){
        val db:SQLiteDatabase = helper?.readableDatabase!!
        val values = ContentValues()
        values.put(AlumnosContract.Companion.Entrada.COLUMNA_ID,item.id)
        values.put(AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE,item.nombre)
        db.update(AlumnosContract.Companion.Entrada.NOMBRE_TABLA,
                values,
                " id=?",
                arrayOf(item.id))

        db.close()
    }

    fun deleteAlumno(item:Alumno){
        val db:SQLiteDatabase = helper?.readableDatabase!!
        db.delete(AlumnosContract.Companion.Entrada.NOMBRE_TABLA,
            " id=?",
            arrayOf(item.id))

        db.close()
    }



}