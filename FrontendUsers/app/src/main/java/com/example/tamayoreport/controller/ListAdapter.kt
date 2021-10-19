package com.example.tamayoreport

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tamayoreport.model.entities.Report

class ListAdapter(
    val datos: List<Report>,
    private val clickListener: OnItemClickListener
) : RecyclerView.Adapter<ListAdapter.datosHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.datosHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return datosHolder(layoutInflater.inflate(R.layout.fragment_item_report, parent, false))
    }

    interface OnItemClickListener {
        fun onItemClick(item: Report)
    }

    override fun onBindViewHolder(holder: ListAdapter.datosHolder, position: Int) {
        holder.render(datos[position])

        holder.itemView.setOnClickListener {
            clickListener.onItemClick(datos[position])
        }
    }

    override fun getItemCount(): Int {
        return datos.size
    }

    class datosHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun render(report: Report) {
            var texto = view.findViewById<TextView>(R.id.res)
            var imagen = view.findViewById<ImageView>(R.id.img)
            var fecha = view.findViewById<TextView>(R.id.fecha)
            var fondo = view.findViewById<LinearLayout>(R.id.back)
            if(report.descripcion.length>25) {
                texto.text = report.descripcion.substring(0,25)+"..."
            }
            else{
                texto.text = report.descripcion
            }
            fecha.text = report.fechaReporte
            when (report.estado) {
                "Recibido" -> texto.setTextColor(Color.parseColor("#DD3E4D"))
                "En proceso" -> texto.setTextColor(Color.parseColor("#F7A22F"))
                "Resuelto" -> texto.setTextColor(Color.parseColor("#5EC674"))

        }
            when (report.categoria) {
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
        }
    }
}