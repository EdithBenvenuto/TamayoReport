package com.example.tamayoreport.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tamayoreport.ListAdapter
import com.example.tamayoreport.R
import com.example.tamayoreport.model.entities.Report

class lista_reportes : AppCompatActivity() {
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
    lateinit var Crea: Button
    lateinit var Busca: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_reportes)
        Crea = findViewById(R.id.Crea)
        Busca = findViewById(R.id.Busca)
        Crea.setOnClickListener(clicCrea())
        Busca.setOnClickListener(clicBusca())
        initRecycler()
    }
    fun clicCrea(): View.OnClickListener?{
        return View.OnClickListener{
            val switchActivityIntent = Intent(applicationContext, CategoriesActivity::class.java)
            startActivity(switchActivityIntent);
        }
    }
    fun clicBusca(): View.OnClickListener?{
        return View.OnClickListener{
            val switchActivityIntent = Intent(applicationContext, lista_reportes::class.java)
            startActivity(switchActivityIntent);
        }
    }
    fun initRecycler(){
        var lista = findViewById<RecyclerView>(R.id.Recycler)
        lista.layoutManager = LinearLayoutManager(this)
        val adapter = ListAdapter(reportesHard,object : ListAdapter.OnItemClickListener {
            override fun onItemClick(item: Report) {
                val category = Bundle()
                category.putString("id", item.id)
                val switchActivityIntent = Intent(applicationContext, VerReporte::class.java)
                switchActivityIntent.putExtras(category)
                startActivity(switchActivityIntent);
            }
        })
        lista.adapter = adapter
    }
}