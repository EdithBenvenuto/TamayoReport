package com.example.tamayoreport.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import com.example.tamayoreport.R

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
        val b = intent.extras
        val userid = b?.getString("userId").toString()

        luminariasLayout=findViewById<RelativeLayout>(R.id.luminariasLayout)
        basuraLayout=findViewById<RelativeLayout>(R.id.basuraLayout)
        hecesPerroLayout=findViewById<RelativeLayout>(R.id.hecesPerroLayout)
        ramasLayout=findViewById<RelativeLayout>(R.id.ramasLayout)
        perroSinCorreaLayout=findViewById<RelativeLayout>(R.id.perroSinCorreaLayout)
        hierbaCrecidaLayout=findViewById<RelativeLayout>(R.id.hierbaCrecidaLayout)
        desperfInstLayout=findViewById<RelativeLayout>(R.id.desperfInstLayout)
        malUsoLayout=findViewById<RelativeLayout>(R.id.malUsoLayout)
        otrosLayout=findViewById<RelativeLayout>(R.id.otrosLayout)

        luminariasLayout.setOnClickListener(layoutClickListener("Luminarias", userid))
        basuraLayout.setOnClickListener(layoutClickListener("Basura",userid))
        hecesPerroLayout.setOnClickListener(layoutClickListener("Heces de Perro",userid))
        ramasLayout.setOnClickListener(layoutClickListener("Ramas Obstruyendo el Paso",userid))
        perroSinCorreaLayout.setOnClickListener(layoutClickListener("Perro sin correa",userid))
        hierbaCrecidaLayout.setOnClickListener(layoutClickListener("Hierba Crecida",userid))
        desperfInstLayout.setOnClickListener(layoutClickListener("Desperfecto en Instalaciones",userid))
        malUsoLayout.setOnClickListener(layoutClickListener("Mal uso de instalaciones o faltas al reglamento",userid))
        otrosLayout.setOnClickListener(layoutClickListener("Otros",userid))
    }
    private fun layoutClickListener(s:String, userId: String): View.OnClickListener?{
        return View.OnClickListener{
            //Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
            val category = Bundle()
            category.putString("key", s)
            category.putString("userId",userId)
            val switchActivityIntent = Intent(applicationContext, Reporte::class.java)
            switchActivityIntent.putExtras(category)
            startActivity(switchActivityIntent);
        }
    }
}