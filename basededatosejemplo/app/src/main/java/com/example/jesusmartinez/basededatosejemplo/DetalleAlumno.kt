package com.example.jesusmartinez.basededatosejemplo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_detalle_alumno.*

class DetalleAlumno : AppCompatActivity() {

    var crud: AlumnoCRUD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_alumno)

        val id=findViewById<EditText>(R.id.etId)
        val nombre = findViewById<EditText>(R.id.etNombre)


        val bAdd = findViewById<Button>(R.id.bAdd)
        val bActualizar = findViewById<Button>(R.id.bActualizar)

        var index = intent.getStringExtra("ID")

        crud = AlumnoCRUD(this)
        val alumno = crud?.getAlumno(index.toString())

        id.setText(alumno!!.Id,TextView.BufferType.EDITABLE)

        nombre.setText(alumno!!.Nombre,TextView.BufferType.EDITABLE)

        bActualizar.setOnClickListener {
            crud?.updateAlumno(Alumno(id.text.toString(),nombre.text.toString()))
            startActivity(Intent(this,MainActivity::class.java))
        }
        bEliminar.setOnClickListener {
            crud?.deleteAlumno(Alumno(id.text.toString(),nombre.text.toString()))
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}
