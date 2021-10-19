package com.example.tamayoreport.controller

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.tamayoreport.R
import com.example.tamayoreport.Utils
import com.example.tamayoreport.Utils.Companion.BASE_URL
import com.example.tamayoreport.model.Model
import com.example.tamayoreport.model.entities.Report
import com.example.tamayoreport.model.repository.RemoteRepository
import com.example.tamayoreport.model.repository.responseinterface.IAddReport
import com.example.tamayoreport.model.repository.responseinterface.IDeleteProduct
import com.example.tamayoreport.model.repository.responseinterface.IUpdateReport
import com.squareup.picasso.Picasso

class VerReporte_Admin : AppCompatActivity() {

    lateinit var titulo: TextView
    lateinit var descripcion: TextView
    lateinit var location: TextView
    lateinit var imagen: ImageView
    lateinit var id: String
    lateinit var foto : String
    lateinit var categoria:String
    lateinit var selecion: RadioGroup
    lateinit var boton: Button
    lateinit var nuevoEstado :String
    lateinit var updateReport :Report
    lateinit var popReport : Button
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_reporte_admin)
        val b = intent.extras
        id = b?.getString("id").toString()
        foto = b?.getString("foto").toString()
        categoria = b?.getString("categoria").toString()

        titulo = findViewById(R.id.titulo)
        descripcion = findViewById(R.id.descripcion)
        location = findViewById(R.id.location)
        imagen = findViewById(R.id.imageView)
        selecion = findViewById(R.id.grupo)
        boton = findViewById(R.id.botonEstado)
        popReport = findViewById(R.id.borrarReporte)


        var radioButton: RadioButton = findViewById(R.id.RB1)
        when(b?.getString("estado").toString()){
            "Recibido" -> radioButton = findViewById(R.id.RB1)
            "En proceso" -> radioButton = findViewById(R.id.RB2)
            "Resuelto" -> radioButton = findViewById(R.id.RB3)
        }

        radioButton.isChecked = true
        titulo.text = "Reporte de "+ b?.getString("categoria").toString()
        descripcion.text = b?.getString("descripcion").toString()
        location.text = b?.getString("ubicacion").toString() + ", Time: " + b?.getString("fechaReporte").toString()


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


        //Picasso.get().load(foto).into(imagen)
        boton.setOnClickListener(){
            when (selecion.checkedRadioButtonId) {
                R.id.RB1 ->  nuevoEstado = "Recibido" //Toast.makeText(applicationContext, "Cambia estado a 'Recibido'", Toast.LENGTH_SHORT).show()
                R.id.RB2 -> nuevoEstado = "En proceso" //Toast.makeText(applicationContext, "Cambia estado a 'En proceso'", Toast.LENGTH_SHORT).show()
                R.id.RB3 -> nuevoEstado = "Resuelto"//Toast.makeText(applicationContext, "Cambia estado a 'Resuelto'", Toast.LENGTH_SHORT).show()
            }
            updateReport = Report(id," "," "," ", " ", " ", " ", nuevoEstado)
            Model(Utils.getToken(this)).updateReport(updateReport, object : IUpdateReport {
                override fun onSuccess(product: Report?){
                    Toast.makeText(this@VerReporte_Admin, "Datos enviados", Toast.LENGTH_SHORT).show()
                    finish()
                    //val switchActivityIntent = Intent(applicationContext, lista_reportes::class.java)
                    //switchActivityIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    //startActivity(switchActivityIntent);

                }
                override fun onNoSuccess(code: Int, message: String) {
                    Toast.makeText(this@VerReporte_Admin, "Problem detected $code $message", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(t: Throwable) {
                    Toast.makeText(this@VerReporte_Admin, "Network or server error occurred", Toast.LENGTH_SHORT).show()
                }
            }
            )
        }
        popReport.setOnClickListener(){
            Model(Utils.getToken(this)).deleteProduct(id, object : IDeleteProduct{
                override fun onSuccess(product : Report?){
                    Toast.makeText( this@VerReporte_Admin,"Report Deleted", Toast.LENGTH_SHORT).show()
                    finish()
                }
                override fun onNoSuccess(code: Int, message: String) {
                    Toast.makeText(this@VerReporte_Admin, "Problem detected $code $message", Toast.LENGTH_SHORT).show()
                }
                override fun onFailure(t: Throwable) {
                    Toast.makeText(this@VerReporte_Admin, "Network or server error occurred", Toast.LENGTH_SHORT).show()
                }


            })
        }
    }
}