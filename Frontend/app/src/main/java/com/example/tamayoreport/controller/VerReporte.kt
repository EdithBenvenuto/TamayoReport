package com.example.tamayoreport.controller

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_reporte)
        val b = intent.extras
        id = b?.getString("id").toString()
        /*
        categoria = b?.getString("categoria")
        */
        foto = b?.getString("foto").toString()
        /*
        fechaReporte = b?.getString("fechaReporte")
        ubicacion = b?.getString("ubicacion")
        descripcionBundle = b?.getString("descripcion")
        estado = b?.getString("estado")
        */
        //Simular tomar dato de reporte especifico
        titulo = findViewById(R.id.titulo)
        descripcion = findViewById(R.id.descripcion)
        location = findViewById(R.id.location)
        imagen = findViewById(R.id.imageView)
        titulo.text = "Reporte de "+ b?.getString("categoria").toString()
        descripcion.text = b?.getString("descripcion").toString()
        location.text = b?.getString("ubicacion").toString()



        Toast.makeText(this@VerReporte,foto , Toast.LENGTH_SHORT).show()
        val picasso = RemoteRepository.getPicassoInstance(this, Utils.getToken(this))
        val urlForImage = foto//"${Utils.BASE_URL}reportPhotos/$foto"
        picasso.load(urlForImage).into(imagen);

        //Picasso.get().load(foto).into(imagen)
    }
}