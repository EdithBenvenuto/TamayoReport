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
    lateinit var newAdmin: Button
    lateinit var instagram:ImageButton
    lateinit var webPage:ImageButton
    lateinit var facebook:ImageButton
    lateinit var logout :Button
    lateinit var sharedPreferences: SharedPreferences
    lateinit var tokenShp:SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home_screen_logged)
        newAdmin = findViewById<Button>(R.id.registrarAdmin)
        newAdmin.setOnClickListener(newAdminListener())

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
    private fun newAdminListener(): View.OnClickListener?{
        return View.OnClickListener{
            val switchActivityIntent = Intent(applicationContext, RegistroActivity::class.java);
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