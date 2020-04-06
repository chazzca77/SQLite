package com.geekpark.sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_nuevo_alumno.*

class NuevoAlumno : AppCompatActivity() {

    var crud:AlumnoCRUD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_alumno)

        crud = AlumnoCRUD(this)


        //Mandar a llamr nuestra libreria de vrud,
        btnGuardar.setOnClickListener {
            crud?.newAlumno(Alumno(edtId.text.toString(),edtNombre.text.toString()))
            startActivity(Intent(this,MainActivity::class.java))
        }

    }
}
