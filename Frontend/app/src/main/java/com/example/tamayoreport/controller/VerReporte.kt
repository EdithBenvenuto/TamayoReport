package com.example.tamayoreport.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.tamayoreport.R
import com.example.tamayoreport.model.entities.Report
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class VerReporte : AppCompatActivity() {
    var reportesHard: List<Report> = listOf(
        Report("1","Heces de Perro","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png","11/10","41N 32.3E","perrito miando estoy tratando de sobrebordar todo el pinche texto","reportado"),
        Report("2","Basura","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/13.png","05/23","41N 32.3E","la mama","terminado"),
        Report("3","Perro sin correa","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/52.png","03/03","41N 32.3E","de la mama","proceso"),
        Report("4","Ramas Obstruyendo el Paso","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/282.png","02/14","41N 32.3E","mientras siga viendo","terminado"),
        Report("5","Perro sin correa","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/342.png","31/03","41N 32.3E","tu cara en la cara","proceso"),
        Report("6","Luminarias","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/582.png","06/07","41N 32.3E","de la luna","terminado"),
        Report("7","Mal uso de instalaciones o faltas al reglamento","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png","11/10","41N 32.3E","perrito miando","reportado"),
        Report("8","Otros","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/13.png","05/23","41N 32.3E","la mama","terminado"),
        Report("9","Desperfecto en Instalaciones","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/52.png","03/03","41N 32.3E","de la mama","proceso"),
        Report("10","Hierba Crecida","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/282.png","02/14","41N 32.3E","mientras siga viendo","terminado"),
        Report("11","Ramas Obstruyendo el Paso","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/342.png","31/03","41N 32.3E","tu cara en la cara","proceso"),
        Report("12","Luminarias","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/582.png","06/07","41N 32.3E","de la luna","terminado"),
    )   //Simulacion conexion con backend
    lateinit var titulo: TextView
    lateinit var descripcion: TextView
    lateinit var location: TextView
    lateinit var imagen: ImageView
    lateinit var id: String
    lateinit var report: Report
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_reporte)
        val b = intent.extras
        id = b?.getString("id").toString()
        report = reportesHard[id.toInt()-1] //Simular tomar dato de reporte especifico
        titulo = findViewById(R.id.titulo)
        descripcion = findViewById(R.id.descripcion)
        location = findViewById(R.id.location)
        imagen = findViewById(R.id.imageView)
        titulo.text = "Reporte de "+report.category
        descripcion.text = report.description
        location.text = "Location: " + report.ubication
        Picasso.get().load(report.photo).into(imagen)
    }
}