package com.example.tamayoreport

import android.graphics.Color
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tamayoreport.model.entities.Report
import com.squareup.picasso.Picasso

class ListAdapter(val datos:List<Report>):RecyclerView.Adapter<ListAdapter.datosHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.datosHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return datosHolder(layoutInflater.inflate(R.layout.fragment_item_report,parent,false))
    }

    override fun onBindViewHolder(holder: ListAdapter.datosHolder, position: Int) {
        holder.render(datos[position])
    }

    override fun getItemCount(): Int {
        return datos.size
    }
    class datosHolder(val view: View):RecyclerView.ViewHolder(view){
        fun render(report: Report){
            var texto = view.findViewById<TextView>(R.id.res)
            var imagen = view.findViewById<ImageView>(R.id.img)
            var fecha = view.findViewById<TextView>(R.id.fecha)
            var fondo = view.findViewById<LinearLayout>(R.id.back)
            texto.text = report.description
            fecha.text = report.date
            when(report.state){
                "reportado" -> fondo.setBackgroundColor(Color.parseColor("#F75437"))
                "proceso" -> fondo.setBackgroundColor(Color.parseColor("#F7DD37"))
                "terminado" -> fondo.setBackgroundColor(Color.parseColor("#37F780"))
            }
            Picasso.get().load(report.photo).into(imagen)
        }
    }
}