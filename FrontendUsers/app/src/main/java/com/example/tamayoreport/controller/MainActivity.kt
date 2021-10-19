package com.example.tamayoreport.controller

import android.content.Intent
import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import com.example.tamayoreport.R


class MainActivity : AppCompatActivity() {
    lateinit var iniciarSesion:Button
    lateinit var registro:Button
    lateinit var instagram:ImageButton
    lateinit var webPage:ImageButton
    lateinit var facebook:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniciarSesion=findViewById<Button>(R.id.inicioSesion)
        registro=findViewById<Button>(R.id.registrate)
        iniciarSesion.setOnClickListener(loginClickListener())
        registro.setOnClickListener(registerClickListener())

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



    private fun loginClickListener():View.OnClickListener?{
        return View.OnClickListener{
            val switchActivityIntent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(switchActivityIntent);
        }
    }

    private fun registerClickListener():View.OnClickListener?{
        return View.OnClickListener{
            val switchActivityIntent = Intent(applicationContext, RegistroActivity::class.java);
            startActivity(switchActivityIntent);
        }
    }

}