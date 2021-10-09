package com.example.tamayoreport.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.tamayoreport.R

class PostReporteActivity : AppCompatActivity() {
    lateinit var otroReporte: Button
    lateinit var backToWelcome: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_reporte)
        otroReporte=findViewById<Button>(R.id.otroReporte)
        backToWelcome=findViewById<Button>(R.id.backToWelcome)
        otroReporte.setOnClickListener(otroReporteClickListener())
        backToWelcome.setOnClickListener(backToWelcomeClickListener())
    }
    private fun otroReporteClickListener(): View.OnClickListener?{
        return View.OnClickListener{
            val switchActivityIntent = Intent(applicationContext, CategoriesActivity::class.java)
            startActivity(switchActivityIntent);
        }

    }
    private fun backToWelcomeClickListener(): View.OnClickListener?{
        return View.OnClickListener{
            val switchActivityIntent = Intent(applicationContext, HomeScreenLoggedActivity::class.java)
            startActivity(switchActivityIntent);
        }

    }
}