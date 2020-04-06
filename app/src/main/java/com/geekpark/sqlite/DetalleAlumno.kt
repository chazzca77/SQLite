package com.geekpark.sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_detalle_alumno.*

class DetalleAlumno : AppCompatActivity() {

    var crud:AlumnoCRUD? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_alumno)

        crud = AlumnoCRUD(this)

        val id = intent.getStringExtra("ID")

        val alumno = crud?.getAlumno(id)

        edtId.setText(alumno?.id)
        edtNombre.setText(alumno?.nombre)

        btnActualizar.setOnClickListener {

            crud?.updateAlumno(Alumno(edtId.text.toString(),edtNombre.text.toString()))
            startActivity(Intent(this,MainActivity::class.java))

        }

        //No deberia poder cambiar matricual xq es prmary key
        btnEliminar.setOnClickListener {
            crud?.deleteAlumno(Alumno(edtId.text.toString(),edtNombre.text.toString()))
            startActivity(Intent(this,MainActivity::class.java))

        }

    }
}
