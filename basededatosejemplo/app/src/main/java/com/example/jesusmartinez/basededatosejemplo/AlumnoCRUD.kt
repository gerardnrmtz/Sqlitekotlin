package com.example.jesusmartinez.basededatosejemplo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class AlumnoCRUD(context: Context) {

    private var dataBaseHelper: DataBaseHelper? = null

    init {
        dataBaseHelper = DataBaseHelper(context)
    }

    fun newAlumno(item: Alumno) {
        val db: SQLiteDatabase = dataBaseHelper?.writableDatabase!!
        //mapeo
        val values = ContentValues()
        values.put(AlumnosContract.Companion.Entrada.COLUMNA_ID, item.Id)
        values.put(AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE,item.Nombre)
        //insertar una fila ala tabla
        val newRowId = db.insert(AlumnosContract.Companion.Entrada.NOMBRE_TABLA, null, values)

        db.close()
    }

    fun getAlumnos(): ArrayList<Alumno> {
        val items: ArrayList<Alumno> = ArrayList()
        //Abrir en modo lectura
        val db: SQLiteDatabase = dataBaseHelper?.readableDatabase!!

        //Especificar las columnas a consultar
        val columnas =
            arrayOf(AlumnosContract.Companion.Entrada.COLUMNA_ID,AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE)

        //Crear un cursos para recorer la tabla
        val c: Cursor = db.query(
            AlumnosContract.Companion.Entrada.NOMBRE_TABLA,
            columnas,
            null,
            null,
            null,
            null,
            null
        )
        //Hacer el recorrido del cursos en la tabla

        while (c.moveToNext()) {
            items.add(
                Alumno(
                    c.getString(c.getColumnIndexOrThrow(AlumnosContract.Companion.Entrada.COLUMNA_ID)),
                    c.getString(c.getColumnIndexOrThrow(AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE))
                )
            )
        }
        //Cerrar db
        db.close()

        return items
    }

    fun getAlumno(id: String): Alumno {
        var item: Alumno? = null

        val db: SQLiteDatabase = dataBaseHelper?.readableDatabase!!

        val columnas =
            arrayOf(AlumnosContract.Companion.Entrada.COLUMNA_ID,AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE)

        val c: Cursor = db?.query(
            AlumnosContract.Companion.Entrada.NOMBRE_TABLA,
            columnas,
            "id = ?",
            arrayOf(id),
            null,
            null,
            null
        )
        while (c.moveToNext()) {
            item = Alumno(
                c.getString(c.getColumnIndexOrThrow(AlumnosContract.Companion.Entrada.COLUMNA_ID)),
                c.getString(c.getColumnIndexOrThrow(AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE))
            )
        }
        db.close()
        return item!!

    }

    fun updateAlumno(item: Alumno) {
        val db: SQLiteDatabase = dataBaseHelper?.writableDatabase!!

        //mapear los datos
        val values = ContentValues()
        values.put(AlumnosContract.Companion.Entrada.COLUMNA_ID, item.Id)
        values.put(AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE, item.Nombre)

        db?.update(
            AlumnosContract.Companion.Entrada.NOMBRE_TABLA,
            values,
            "id = ?",
            arrayOf(item.Id)
        )

        db?.close()
    }

    fun deleteAlumno(item: Alumno) {
        val db: SQLiteDatabase = dataBaseHelper?.writableDatabase!!

        db?.delete(
            AlumnosContract.Companion.Entrada.NOMBRE_TABLA,
            "id = ?",
            arrayOf(item.Id)
        )
        db?.close()
    }

}