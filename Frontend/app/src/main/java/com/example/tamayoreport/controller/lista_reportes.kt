package com.example.tamayoreport.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tamayoreport.ListAdapter
import com.example.tamayoreport.R
import com.example.tamayoreport.model.entities.Report

class lista_reportes : AppCompatActivity() {
    var reportesHard: List<Report> = listOf(
        Report("1","f","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png","ayer","rio","perrito miando","reportado"),
        Report("1","f","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/13.png","ayer","rio","la mama","terminado"),
        Report("1","f","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/52.png","ayer","rio","de la mama","proceso"),
        Report("1","f","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/282.png","ayer","rio","mientras siga viendo","terminado"),
        Report("1","f","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/342.png","ayer","rio","tu cara en la cara","proceso"),
        Report("1","f","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/582.png","ayer","rio","de la luna","terminado"),
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_reportes)
        initRecycler()
    }
    fun initRecycler(){
        var lista = findViewById<RecyclerView>(R.id.Recycler)
        lista.layoutManager = LinearLayoutManager(this)
        val adapter = ListAdapter(reportesHard)
        lista.adapter = adapter
    }
}