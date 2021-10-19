package com.example.tamayoreport.controller

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.tamayoreport.R
import com.example.tamayoreport.Utils
import com.example.tamayoreport.model.entities.Report
import com.example.tamayoreport.model.repository.RemoteRepository
import com.squareup.picasso.Picasso

class VerReporte : AppCompatActivity() {

    lateinit var titulo: TextView
    lateinit var descripcion: TextView
    lateinit var location: TextView
    lateinit var imagen: ImageView
    lateinit var id: String
    lateinit var foto :String
    lateinit var report: Report
    lateinit var categoria:String
    lateinit var estadoReporte:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_reporte)
        val b = intent.extras
        id = b?.getString("id").toString()

        categoria = b?.getString("categoria").toString()

        foto = b?.getString("foto").toString()
        /*
        fechaReporte = b?.getString("fechaReporte")
        ubicacion = b?.getString("ubicacion")
        descripcionBundle = b?.getString("descripcion")
        */


        //Simular tomar dato de reporte especifico
        titulo = findViewById(R.id.titulo)
        descripcion = findViewById(R.id.descripcion)
        location = findViewById(R.id.location)
        imagen = findViewById(R.id.imageView)
        estadoReporte = findViewById(R.id.estadoReporte)
        titulo.text = "Reporte de "+ b?.getString("categoria").toString()
        descripcion.text = b?.getString("descripcion").toString()
        location.text = b?.getString("ubicacion").toString()
        estadoReporte.text =  b?.getString("estado").toString()

        when (estadoReporte.text.toString()) {
            "Recibido" -> estadoReporte.setTextColor(Color.parseColor("#DD3E4D"))
            "En proceso" -> estadoReporte.setTextColor(Color.parseColor("#F7A22F"))
            "Resuelto" -> estadoReporte.setTextColor(Color.parseColor("#5EC674"))
        }

        if(foto == "null"){
            when(categoria){
                "Luminarias" -> imagen.setImageResource(R.drawable.luminarias)
                "Basura" -> imagen.setImageResource(R.drawable.basura)
                "Heces de Perro" -> imagen.setImageResource(R.drawable.hecesperro)
                "Ramas Obstruyendo el Paso" -> imagen.setImageResource(R.drawable.ramasobstruyendo)
                "Perro sin correa" -> imagen.setImageResource(R.drawable.perrosincorrea)
                "Hierba Crecida" -> imagen.setImageResource(R.drawable.hierbacrecida)
                "Desperfecto en Instalaciones" -> imagen.setImageResource(R.drawable.desperfectoinstralaciones)
                "Mal uso de instalaciones o faltas al reglamento" -> imagen.setImageResource(R.drawable.malusoinstalaciones)
                "Otros" -> imagen.setImageResource(R.drawable.otros)
            }
        }else {
            val picasso = RemoteRepository.getPicassoInstance(this, Utils.getToken(this))
            val urlForImage = "${Utils.BASE_URL}reports/reportPhotos/$foto"
            //val urlForImage = "http://10.0.2.2:3000/reportPhotos/$foto"
            picasso.load(urlForImage).into(imagen);

            //Picasso.get().load(foto).into(imagen)
        }
    }
}