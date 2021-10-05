package com.example.tamayoreport

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class Reporte : AppCompatActivity() {
    lateinit var reportCategory:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reporte)
        reportCategory=findViewById(R.id.categoriaReporte)
        val b = intent.extras
        val value = b?.getString("key")
//        reportCategory.apply{
//            text=value
//        }
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show()
    }

}