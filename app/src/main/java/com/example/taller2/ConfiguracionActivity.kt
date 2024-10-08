package com.example.taller2

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.RelativeLayout

class ConfiguracionActivity : AppCompatActivity() {

    private lateinit var cambiarColorBtn: Button
    private lateinit var volverInicioBtn: Button
    private lateinit var layout: RelativeLayout
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracion)

        layout = findViewById(R.id.configLayout1)
        cambiarColorBtn = findViewById(R.id.cambiarColorBtn)
        volverInicioBtn = findViewById(R.id.volverInicioBtn)

        sharedPreferences = getSharedPreferences("config", MODE_PRIVATE)

        cambiarColorBtn.setOnClickListener {
            val color = Color.rgb((0..255).random(), (0..255).random(), (0..255).random())
            layout.setBackgroundColor(color)
            sharedPreferences.edit().putInt("backgroundColor", color).apply()
        }

        volverInicioBtn.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        val color = sharedPreferences.getInt("backgroundColor", Color.WHITE)
        layout.setBackgroundColor(color)
    }
}

