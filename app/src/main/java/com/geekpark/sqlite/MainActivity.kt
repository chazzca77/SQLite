package com.geekpark.sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //base de datos nativa, como funciona por dentro,
    //Conocer hay diferentes formas de implementar bases de datos, nativa SQLite, estandar , y solo es de manera local,
    //al eliminar la app eliminamos la base de datos, hay otras librerias,
    //4 clases principales, el contrato(estructura de bd y sus tablas), para modificar columna lo hacemos at raves de l esquema, el contenido se queda en el esquema
    //manera de centrallzar la información
    /*Database helper: una clase que nos permite realizar las operaciones de creación y modificaciín de bd, queri¿es y sentemncias, primary key, tipo de datos
    *AlumnosCRUD, concentar las operación es una clase especifica, agregar, actualizar,elimnar, consultar,etc, aqui se hace use de dbhelper
    * Alumnos, modelo de datos, mapear columnas de tabla
    * */

    //Implementacion detalle por ID    Alumno
    //Creamos nuestro modelo   Alumnos Contract
    //Esquema de la bd, Contrato   Defnir tablas de bd
    //DB Helper   DatabaseHelper
    //INSERT  AlumnoCrud
    //SELECT
    //SELECT WHERE
    //UPDATE
    //DELETE
    //Diseño de vistas
    //Crear vista alnuevo
    var adaptador:AdaptadorCustom? = null
    var recyclerLista: RecyclerView? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    var alumnos:ArrayList<Alumno>? = null
    var crud:AlumnoCRUD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerLista = findViewById(R.id.lista)

        lista?.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerLista?.layoutManager = layoutManager


        crud = AlumnoCRUD(this)
        alumnos = crud?.getAlumnos()


        floatingActionButton.setOnClickListener {
            startActivity(Intent(this,NuevoAlumno::class.java))
        }

        adaptador = AdaptadorCustom(this,alumnos!!,object :ClickListenerRecycler{
            override fun onClick(vista: View, index: Int) {

                Toast.makeText(applicationContext,"asdas",Toast.LENGTH_SHORT).show()

                var intent = Intent(applicationContext,DetalleAlumno::class.java)
                intent.putExtra("ID",alumnos?.get(index)?.id)
                startActivity(intent)

            }


        })

        recyclerLista?.adapter = adaptador

    }
}
