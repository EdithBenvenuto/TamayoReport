package com.example.tamayoreport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class HomeScreenLoggedActivity : AppCompatActivity() {
    lateinit var newReport: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen_logged)
        newReport=findViewById<Button>(R.id.newReport)
        newReport.setOnClickListener(newReportClickListener())
    }
    private fun newReportClickListener(): View.OnClickListener?{
        return View.OnClickListener{
            val switchActivityIntent = Intent(applicationContext, CategoriesActivity::class.java);
            startActivity(switchActivityIntent);
        }
    }
}