package com.example.tamayoreport.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.tamayoreport.R

class HomeScreenLoggedActivity : AppCompatActivity() {
    lateinit var newReport: Button
    lateinit var busca: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home_screen_logged)
        val b = intent.extras
        val userid = b?.getString("userId").toString()
        newReport=findViewById<Button>(R.id.newReport)
        newReport.setOnClickListener(newReportClickListener(userid))
        busca = findViewById(R.id.busca)
        busca.setOnClickListener(clicBusca())
    }
    private fun newReportClickListener(userId : String): View.OnClickListener?{
        return View.OnClickListener{
            val userid = Bundle()
            userid.putString("userId", userId);
            val switchActivityIntent = Intent(applicationContext, CategoriesActivity::class.java);
            switchActivityIntent.putExtras(userid);
            startActivity(switchActivityIntent);
        }
    }
    private fun clicBusca(): View.OnClickListener?{
        return View.OnClickListener{
            val switchActivityIntent = Intent(applicationContext, lista_reportes::class.java)
            startActivity(switchActivityIntent);
        }
    }
}