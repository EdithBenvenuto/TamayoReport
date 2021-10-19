package com.example.tamayoreport.controller

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tamayoreport.ListAdapter
import com.example.tamayoreport.R
import com.example.tamayoreport.Utils
import com.example.tamayoreport.model.entities.Report
import com.example.tamayoreport.model.Model
import com.example.tamayoreport.model.repository.responseinterface.IGetReports

class lista_reportes : AppCompatActivity() {
    lateinit var  sharedPreferences: SharedPreferences
    var  userId :String =" "
    var tipoUsuario: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_reportes)
        sharedPreferences = this.getSharedPreferences("tokenPrefs", Context.MODE_PRIVATE)
        userId = sharedPreferences.getString("shareIdUser", "defaultID").toString()
        tipoUsuario= sharedPreferences.getBoolean("admin", false)

    }

    override fun onResume() {
        super.onResume()
        if(tipoUsuario){
            consultaAdmin()

        }else{
            consultaUsuario()
        }
    }
    fun consultaAdmin(){
        Model(Utils.getToken(this)).getReports(object : IGetReports {
            override fun onSuccess(products: List<Report>?){
                if (products != null) {
                    //reportesHard=products
                    initRecycler(products)
                    val product: Report = products[0]
                    //Log.i("reportes",reports.toString())
                }else{
                    //Log.i("reportes",reports.toString())
                }
            }
            override fun onNoSuccess(code: Int, message: String) {
                Toast.makeText(this@lista_reportes, "Problem detected $code $message", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(t: Throwable) {
                Toast.makeText(this@lista_reportes, "Network or server error occurred", Toast.LENGTH_SHORT).show()
            }
        }
        )

    }
    fun consultaUsuario(){
        Model(Utils.getToken(this)).getReports(object : IGetReports {
            override fun onSuccess(products: List<Report>?){
                if (products != null) {
                    var listaReportes: MutableList<Report> = ArrayList()
                    for (i in products) {

                        if(i.idUsuario == userId){
                            listaReportes.add(i)
                            Log.i("entró a if " + i.idUsuario,userId)
                        }
                    }
                    //reportesHard=products
                    //Toast.makeText(this@lista_reportes, "Tamaño: "+listaReportes.size.toString(), Toast.LENGTH_SHORT).show()
                    initRecycler(listaReportes)
                    //Log.i("reportes",reports.toString())
                }else{
                    Toast.makeText(this@lista_reportes, "No has realizado ningun reporte", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onNoSuccess(code: Int, message: String) {
                Toast.makeText(this@lista_reportes, "Problem detected $code $message", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(t: Throwable) {
                Toast.makeText(this@lista_reportes, "Network or server error occurred", Toast.LENGTH_SHORT).show()
            }
        }
        )
//        Model(Utils.getToken(this)).getUserReports(userId, object : IGetReports {
//            override fun onSuccess(products: List<Report>?){
//                if (products != null) {
//                    //reportesHard=products
//                    initRecycler(products)
//                    val product: Report = products[0]
//                    //Log.i("reportes",reports.toString())
//                    Toast.makeText(this@lista_reportes, product.categoria.toString() + " size: " + products.size.toString(), Toast.LENGTH_LONG).show()
//                }else{
//                    //Log.i("reportes",reports.toString())
//                    Toast.makeText(this@lista_reportes, products.toString(), Toast.LENGTH_LONG).show()
//                }
//            }
//            override fun onNoSuccess(code: Int, message: String) {
//                Toast.makeText(this@lista_reportes, "Problem detected $code $message", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onFailure(t: Throwable) {
//                Toast.makeText(this@lista_reportes, "Network or server error occurred", Toast.LENGTH_SHORT).show()
//            }
//        }
//        )

    }
    fun initRecycler(reportesHard: List<Report>){
        var lista = findViewById<RecyclerView>(R.id.Recycler)
        lista.layoutManager = LinearLayoutManager(this)
        val adapter = ListAdapter(reportesHard,object : ListAdapter.OnItemClickListener {
            override fun onItemClick(item: Report) {
                val reportBundle = Bundle()
                reportBundle.putString("id", item.id)
                reportBundle.putString("categoria", item.categoria)
                reportBundle.putString("foto",item.foto)
                reportBundle.putString("fechaReporte",item.fechaReporte)
                reportBundle.putString("ubicacion",item.ubicacion)
                reportBundle.putString("descripcion",item.descripcion)
                reportBundle.putString("estado",item.estado)
                if(tipoUsuario){
                    val switchActivityIntent = Intent(applicationContext, VerReporte_Admin::class.java)
                    switchActivityIntent.putExtras(reportBundle)
                    startActivity(switchActivityIntent);
                }
                else {
                    val switchActivityIntent = Intent(applicationContext, VerReporte::class.java)
                    switchActivityIntent.putExtras(reportBundle)
                    startActivity(switchActivityIntent);
                }
            }
        })
        lista.adapter = adapter
    }
}