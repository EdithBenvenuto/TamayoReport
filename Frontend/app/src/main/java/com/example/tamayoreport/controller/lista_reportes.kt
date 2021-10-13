package com.example.tamayoreport.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tamayoreport.ListAdapter
import com.example.tamayoreport.R
import com.example.tamayoreport.Utils
import com.example.tamayoreport.model.entities.Report
import com.example.tamayoreport.model.Model
import com.example.tamayoreport.model.repository.responseinterface.IAddReport
import com.example.tamayoreport.model.repository.responseinterface.IGetReports
import com.google.android.datatransport.cct.internal.LogResponse.fromJson
import com.google.gson.Gson
import org.json.JSONArray

class lista_reportes : AppCompatActivity() {

    var tipoUsuario: String = "admin"
    lateinit var Crea: Button
    lateinit var Busca: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_reportes)
        Crea = findViewById(R.id.Crea)
        Busca = findViewById(R.id.Busca)
        Crea.setOnClickListener(clicCrea())
        Busca.setOnClickListener(clicBusca())

        Model(Utils.getToken(this)).getReports(object : IGetReports {
            override fun onSuccess(products: List<Report>?){
                if (products != null) {

                    initRecycler(products)
                    //val product: Report = products[0]
                    //Log.i("reportes",reports.toString())
                    //Toast.makeText(this@lista_reportes, product.categoria.toString() + " size: " + products.size.toString(), Toast.LENGTH_LONG).show()
                }else{
                    //Log.i("reportes",reports.toString())
                    //Toast.makeText(this@lista_reportes, products.toString(), Toast.LENGTH_LONG).show()
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
    fun initRecycler(reportesHard : List<Report>){
        var lista = findViewById<RecyclerView>(R.id.Recycler)
        lista.layoutManager = LinearLayoutManager(this)
        val adapter = ListAdapter(reportesHard,object : ListAdapter.OnItemClickListener {
            override fun onItemClick(item: Report) {
                val category = Bundle()
                category.putString("id", item.id)
                if(tipoUsuario=="admin"){
                    val switchActivityIntent = Intent(applicationContext, VerReporte_Admin::class.java)
                    switchActivityIntent.putExtras(category)
                    startActivity(switchActivityIntent);
                }
                else {
                    val switchActivityIntent = Intent(applicationContext, VerReporte::class.java)
                    switchActivityIntent.putExtras(category)
                    startActivity(switchActivityIntent);
                }
            }
        })
        lista.adapter = adapter
    }
}