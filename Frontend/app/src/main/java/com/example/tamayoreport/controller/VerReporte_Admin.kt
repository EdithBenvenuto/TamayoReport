package com.example.tamayoreport.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.tamayoreport.R
import com.example.tamayoreport.model.entities.Report
import com.squareup.picasso.Picasso

class VerReporte_Admin : AppCompatActivity() {
    var reportesHard: List<Report> = listOf(
        Report("1","Heces de Perro","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png","11/10","41N 32.3E","perrito miando estoy tratando de sobrebordar todo el pinche texto","Recibido"),
        Report("2","Basura","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/13.png","05/23","41N 32.3E","la mama","Resuelto"),
        Report("3","Perro sin correa","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/52.png","03/03","41N 32.3E","de la mama","En proceso"),
        Report("4","Ramas Obstruyendo el Paso","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/282.png","02/14","41N 32.3E","mientras siga viendo","Resuelto"),
        Report("5","Perro sin correa","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/342.png","31/03","41N 32.3E","tu cara en la cara","En proceso"),
        Report("6","Luminarias","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/582.png","06/07","41N 32.3E","de la luna","Resuelto"),
        Report("7","Mal uso de instalaciones o faltas al reglamento","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png","11/10","41N 32.3E","perrito miando","Recibido"),
        Report("8","Otros","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/13.png","05/23","41N 32.3E","la mama","Resuelto"),
        Report("9","Desperfecto en Instalaciones","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/52.png","03/03","41N 32.3E","de la mama","En proceso"),
        Report("10","Hierba Crecida","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/282.png","02/14","41N 32.3E","mientras siga viendo","Resuelto"),
        Report("11","Ramas Obstruyendo el Paso","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/342.png","31/03","41N 32.3E","tu cara en la cara","En proceso"),
        Report("12","Luminarias","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/582.png","06/07","41N 32.3E","de la luna","Resuelto"),
    )   //Simulacion conexion con backend
    lateinit var titulo: TextView
    lateinit var descripcion: TextView
    lateinit var location: TextView
    lateinit var imagen: ImageView
    lateinit var id: String
    lateinit var report: Report
    lateinit var selecion: RadioGroup
    lateinit var boton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_reporte_admin)
        val b = intent.extras
        id = b?.getString("id").toString()
        report = reportesHard[id.toInt()-1] //Simular tomar dato de reporte especifico
        titulo = findViewById(R.id.titulo)
        descripcion = findViewById(R.id.descripcion)
        location = findViewById(R.id.location)
        imagen = findViewById(R.id.imageView)
        selecion = findViewById(R.id.grupo)
        boton = findViewById(R.id.botonEstado)
        var radioButton: RadioButton = findViewById(R.id.RB1)
        when(report.state){
            "Recibido" -> radioButton = findViewById(R.id.RB1)
            "En proceso" -> radioButton = findViewById(R.id.RB2)
            "Resuelto" -> radioButton = findViewById(R.id.RB3)
        }
        radioButton.isChecked = true
        titulo.text = "Reporte de "+report.category
        descripcion.text = report.description
        location.text = "Location: " + report.ubication
        Picasso.get().load(report.photo).into(imagen)
        boton.setOnClickListener(){
            when (selecion.checkedRadioButtonId) {
                R.id.RB1 -> Toast.makeText(applicationContext, "Cambia estado a 'Recibido'", Toast.LENGTH_SHORT).show()
                R.id.RB2 -> Toast.makeText(applicationContext, "Cambia estado a 'En proceso'", Toast.LENGTH_SHORT).show()
                R.id.RB3 -> Toast.makeText(applicationContext, "Cambia estado a 'Resuelto'", Toast.LENGTH_SHORT).show()
            }
        }
    }
}