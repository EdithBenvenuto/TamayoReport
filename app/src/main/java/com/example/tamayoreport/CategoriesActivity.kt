package com.example.tamayoreport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast

class CategoriesActivity : AppCompatActivity() {
    lateinit var luminariasLayout: RelativeLayout
    lateinit var basuraLayout: RelativeLayout
    lateinit var perroSinCorreaLayout: RelativeLayout
    lateinit var hecesPerroLayout: RelativeLayout
    lateinit var ramasLayout: RelativeLayout
    lateinit var hierbaCrecidaLayout: RelativeLayout
    lateinit var desperfInstLayout: RelativeLayout
    lateinit var malUsoLayout: RelativeLayout
    lateinit var otrosLayout: RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
        luminariasLayout=findViewById<RelativeLayout>(R.id.luminariasLayout)
        basuraLayout=findViewById<RelativeLayout>(R.id.basuraLayout)
        hecesPerroLayout=findViewById<RelativeLayout>(R.id.hecesPerroLayout)
        ramasLayout=findViewById<RelativeLayout>(R.id.ramasLayout)
        perroSinCorreaLayout=findViewById<RelativeLayout>(R.id.perroSinCorreaLayout)
        hierbaCrecidaLayout=findViewById<RelativeLayout>(R.id.hierbaCrecidaLayout)
        desperfInstLayout=findViewById<RelativeLayout>(R.id.desperfInstLayout)
        malUsoLayout=findViewById<RelativeLayout>(R.id.malUsoLayout)
        otrosLayout=findViewById<RelativeLayout>(R.id.otrosLayout)

        luminariasLayout.setOnClickListener(layoutClickListener("Luminarias"))
        basuraLayout.setOnClickListener(layoutClickListener("Basura"))
        hecesPerroLayout.setOnClickListener(layoutClickListener("Heces de Perro"))
        ramasLayout.setOnClickListener(layoutClickListener("Ramas Obstruyendo el Paso"))
        perroSinCorreaLayout.setOnClickListener(layoutClickListener("Perro sin correa"))
        hierbaCrecidaLayout.setOnClickListener(layoutClickListener("Hierba Crecida"))
        desperfInstLayout.setOnClickListener(layoutClickListener("Desperfecto en Instalaciones"))
        malUsoLayout.setOnClickListener(layoutClickListener("Mal uso de instalaciones o faltas al reglamento"))
        otrosLayout.setOnClickListener(layoutClickListener("Otros"))
    }
    private fun layoutClickListener(s:String): View.OnClickListener?{
        return View.OnClickListener{
            //Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
            val category = Bundle()
            category.putString("key", s)
            val switchActivityIntent = Intent(applicationContext, Reporte::class.java)
            switchActivityIntent.putExtras(category)
            startActivity(switchActivityIntent);
        }
    }
}