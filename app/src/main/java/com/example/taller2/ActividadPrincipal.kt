package com.example.taller2

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

class ActividadPrincipal : AppCompatActivity() {

    private lateinit var nombreEditText: EditText
    private lateinit var nombreTextView: TextView
    private lateinit var guardarNombreBtn: Button
    private lateinit var iniciarTareaBtn: Button
    private lateinit var progresoBar: ProgressBar
    private lateinit var btnIrConfiguracion: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_principal)

        // Referenciar vistas
        nombreEditText = findViewById(R.id.nombreEditText)
        nombreTextView = findViewById(R.id.nombreTextView)
        guardarNombreBtn = findViewById(R.id.guardarNombreBtn)
        iniciarTareaBtn = findViewById(R.id.iniciarTareaBtn)
        progresoBar = findViewById(R.id.progresoBar)
        btnIrConfiguracion = findViewById(R.id.btnIrConfiguracion)

        // Guardar nombre
        guardarNombreBtn.setOnClickListener {
            val nombre = nombreEditText.text.toString()
            if (nombre.isNotEmpty()) {
                nombreTextView.text = nombre
            } else {
                Toast.makeText(this, "Ingrese un nombre", Toast.LENGTH_SHORT).show()
            }
        }


        iniciarTareaBtn.setOnClickListener {
            TareaAsincrona().execute()
        }


        btnIrConfiguracion.setOnClickListener {
            val intent = Intent(this, ConfiguracionActivity::class.java)
            startActivity(intent)
        }
    }


    inner class TareaAsincrona : AsyncTask<Void, Int, Void>() {
        override fun onPreExecute() {
            progresoBar.visibility = ProgressBar.VISIBLE
            progresoBar.progress = 0
        }

        override fun doInBackground(vararg params: Void?): Void? {
            for (i in 1..100) {
                Thread.sleep(50)
                publishProgress(i)
            }
            return null
        }

        override fun onProgressUpdate(vararg values: Int?) {
            progresoBar.progress = values[0] ?: 0
        }

        override fun onPostExecute(result: Void?) {
            progresoBar.visibility = ProgressBar.GONE
            Toast.makeText(this@ActividadPrincipal, "Tarea finalizada", Toast.LENGTH_SHORT).show()
        }
    }
}
