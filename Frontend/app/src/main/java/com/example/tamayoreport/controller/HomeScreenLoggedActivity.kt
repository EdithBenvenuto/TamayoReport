package com.example.tamayoreport.controller

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import com.example.tamayoreport.R

class HomeScreenLoggedActivity : AppCompatActivity() {
    lateinit var newReport: Button
    lateinit var busca: Button
    lateinit var instagram:ImageButton
    lateinit var webPage:ImageButton
    lateinit var facebook:ImageButton
    lateinit var logout :Button
    lateinit var sharedPreferences: SharedPreferences
    lateinit var tokenShp:SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home_screen_logged)
        val b = intent.extras
        val userid = b?.getString("userId").toString()
        newReport=findViewById<Button>(R.id.newReport)
        newReport.setOnClickListener(newReportClickListener(userid))
        busca = findViewById(R.id.busca)
        busca.setOnClickListener(clicBusca())
        sharedPreferences = this.getSharedPreferences("tokenPrefs", Context.MODE_PRIVATE)
        logout = findViewById(R.id.logout)
        logout.setOnClickListener(clearSharedPreferences())


        instagram = findViewById(R.id.instagram)
        webPage = findViewById(R.id.webPage)
        facebook = findViewById(R.id.facebook)
        instagram.setOnClickListener(openSocialMedia("https://www.instagram.com/parque.rufinotamayo/"))
        webPage.setOnClickListener(openSocialMedia("https://parquerufinotamayo.com/"))
        facebook.setOnClickListener(openSocialMedia("https://www.facebook.com/parquerufinotamayo"))
    }
    private fun openSocialMedia(url:String): View.OnClickListener? {
        return View.OnClickListener {

            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }


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
    private fun clearSharedPreferences(): View.OnClickListener?{
        return View.OnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            val switchActivityIntent = Intent(applicationContext, MainActivity::class.java)
            startActivity(switchActivityIntent);
        }
    }
}